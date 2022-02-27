package com.pru.pinmm

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.pru.pinmm.preferences.MyPreferences

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        private var myPreferences: MyPreferences? = null
        private var application: MyApplication? = null
        private var sharedPreferences: SharedPreferences? = null
        val context: Context
            get() = application!!.applicationContext

        @JvmStatic
        fun getMyPreferences(): MyPreferences {
            synchronized(MyApplication::class.java) {
                if (sharedPreferences == null) {
                    sharedPreferences = context.getSharedPreferences(
                        BuildConfig.APPLICATION_ID + "_preferences",
                        MODE_PRIVATE
                    )
                    myPreferences = MyPreferences(sharedPreferences!!)
                }
                return myPreferences!!
            }
        }
    }
}