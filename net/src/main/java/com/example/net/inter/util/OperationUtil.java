package com.example.net.inter.util;



import com.example.net.constant.ConstantVariable;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by apple on 2018/1/21.
 */

public class OperationUtil {
    private static final OkHttpClient client = new OkHttpClient.Builder().
            connectTimeout(30, TimeUnit.SECONDS).
            readTimeout(30, TimeUnit.SECONDS).
            writeTimeout(30, TimeUnit.SECONDS).
            build();

    public static <T> T generateApi(Class<T> clazz){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstantVariable.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(clazz);
    }
}
