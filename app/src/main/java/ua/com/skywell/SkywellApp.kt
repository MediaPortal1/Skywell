package ua.com.skywell

import android.app.Application
import android.content.Context
import com.vk.sdk.VKSdk


class SkywellApp : Application() {
    override fun onCreate() {
        super.onCreate()
        VKSdk.initialize(applicationContext)
    }
}