package com.pru.pinmm.remote

import android.util.Log
import com.pru.pinmm.BuildConfig
import com.pru.pinmm.MyApplication
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIHelper {
    @Volatile
    private var instance: Retrofit? = null
    private val instanceForStaticCall: Retrofit?
        get() {
            synchronized(APIHelper::class.java) {
                if (instance == null) {
                    instance = Retrofit.Builder()
                        .baseUrl(MyApplication.getMyPreferences().keyBaseUrl.ifEmpty { APIRepository.kBASEURL })
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return instance
        }
    private val client: OkHttpClient
        get() {
            val interceptor = Interceptor { chain: Interceptor.Chain ->
                val request = chain.request().newBuilder()
                    .build()
                chain.proceed(request)
            }
            val builder: OkHttpClient.Builder = OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .callTimeout(5, TimeUnit.MINUTES)
            if (BuildConfig.DEBUG) {

                val httpLoggingInterceptor =
                    HttpLoggingInterceptor(logger = object : HttpLoggingInterceptor.Logger {
                        override fun log(message: String) {
                            Log.i("OkHttp", "log: $message")
                        }
                    })
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addNetworkInterceptor(httpLoggingInterceptor)
            }
            builder.addInterceptor(interceptor)
            return builder.build()
        }

    @JvmStatic
    val repository: APIRepository
        get() = instanceForStaticCall!!.create(APIRepository::class.java)

    fun resetRepository() {
        instance = null
    }
}