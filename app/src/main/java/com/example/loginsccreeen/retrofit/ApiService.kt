package com.example.loginsccreeen.retrofit

import android.content.Context
import android.util.Log
import com.example.loginsccreeen.NetworkInterceptor.ConnectivityInterceptor
import com.example.loginsccreeen.retrofit.DataClass.AppverersionResponse
import com.example.loginsccreeen.retrofit.DataClass.LoginResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ApiService {

    // Login
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("login")
    fun Login(@Field("name") username: String, @Field("password") password: String): Call<LoginResponse>


    @Headers("Accept: application/json")
    @GET("app_version")
    fun appVersion(): Call<AppverersionResponse>

    /**
     * Companion object to create the ApiService
     */
    companion object Factory {
        fun create(url: String, context: Context): ApiService {
            Log.e("API service ","API Service "+url + " context " + context)
            val client = OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(ConnectivityInterceptor(context))
                .build()
            //val gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}