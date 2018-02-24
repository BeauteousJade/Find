package com.example.net.net.api;

import com.example.net.net.bean.result.ResultBean;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by pby on 2018/2/8.
 */

public interface FileApi {
    @POST("fileServlet.jsp?action=upload")
    Call<ResultBean> upLoad(@Body MultipartBody body, @Query("email") String email);

    @POST("fileServlet.jsp?action=delete")
    Call<ResultBean> delete(@Body List<String> url);
}
