package com.pru.pinmm.remote;

import android.util.Log;

import com.pru.pinmm.BuildConfig;
import com.pru.pinmm.MyApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIHelper {
    private static volatile Retrofit instance;

    private static Retrofit getInstanceForStaticCall() {
        synchronized (APIHelper.class) {
            if (instance == null) {
                instance = new Retrofit.Builder()
                        .baseUrl(MyApplication.getMyPreferences().getKeyBASEURL())
                        .client(getClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }
        return instance;
    }

    private static OkHttpClient getClient() {
        Interceptor interceptor = chain -> {
            Request request = chain.request().newBuilder()
                    .build();
            return chain.proceed(request);
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .callTimeout(5, TimeUnit.MINUTES);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> {
                Log.i("OkHttp", "log: " + message);
            });
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
            builder.addNetworkInterceptor(httpLoggingInterceptor);
        }

        builder.addInterceptor(interceptor);
        return builder.build();
    }

    public static APIRepository getRepository() {
        return APIHelper.getInstanceForStaticCall().create(APIRepository.class);
    }

    public static void resetRepository(){
        instance = null;
    }
}
