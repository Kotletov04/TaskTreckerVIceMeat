package com.example.tasktreckervicemeat.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val connectionIsAvailable = checkNetwork(context = context)
        if (connectionIsAvailable) {
            println("Connection")
        } else {
            println("ANTI Connection")
        }
    }


    private fun checkNetwork(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}