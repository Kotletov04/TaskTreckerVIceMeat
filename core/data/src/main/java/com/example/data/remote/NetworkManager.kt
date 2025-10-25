package com.example.data.remote

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NetworkManager @Inject constructor(private val context: Context) {


    @RequiresApi(Build.VERSION_CODES.R)
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun getCurrentConnectivity(): String {
        CoroutineScope(Dispatchers.IO).launch {
            val connectivityManager = context.getSystemService(ConnectivityManager::class.java)
            val current = connectivityManager.activeNetwork
            val test = connectivityManager.getNetworkCapabilities(current)
            Log.d("TEEEEEEEEEEEEEEEST", test?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI).toString())
            Log.d("TEEEEEEEEEEEEEEEST", test?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR).toString())
            Log.d("TEEEEEEEEEEEEEEEST", test?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET).toString())
            Log.d("TEEEEEEEEEEEEEEEST", test?.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH).toString())

        }

        return "connectivityManager.activeNetwork.toString()"
    }


}