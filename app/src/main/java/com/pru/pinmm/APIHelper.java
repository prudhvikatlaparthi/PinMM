package com.pru.pinmm;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIHelper {
    private static volatile Retrofit instance;

    private static Retrofit getInstanceForStaticCall() {
        synchronized (APIHelper.class) {
            if (instance == null) {
                instance = new Retrofit.Builder()
                        .baseUrl(APIRepository.kBASEURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }
        return instance;
    }

    static APIRepository getRepository() {
        return APIHelper.getInstanceForStaticCall().create(APIRepository.class);
    }
}
