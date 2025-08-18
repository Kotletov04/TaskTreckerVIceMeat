package com.example.tasktreckervicemeat.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton


@HiltAndroidApp
class App: Application()