package com.example.loginsccreeen.NetworkInterceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkUtil{
    companion object {
        fun isOnline(context: Context): Boolean {
            val cm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo: NetworkInfo? = cm.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}