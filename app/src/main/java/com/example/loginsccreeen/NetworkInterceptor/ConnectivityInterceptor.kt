package com.example.loginsccreeen.NetworkInterceptor

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor (private val mContext: Context): Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        if (!NetworkUtil.isOnline(mContext)) {
            throw NoConnectivityException()
        }
        val original = chain!!.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("device_type", "android")
//                .addQueryParameter("device_id", Constants.getDeviceId(mContext))
            .build()

//        KLog.e("params ", url.toString())

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)

//        val builder: Request.Builder = chain!!.request().newBuilder()
//        return chain.proceed(builder.build())
    }

}