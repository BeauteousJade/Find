package com.example.net.net.api;

import com.example.net.net.bean.result.ResultBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pby on 2018/2/12.
 */

public interface ReadApi {
    @POST("readServlet.jsp?action=addRead")
    Call<ResultBean> addRead(@Query("email") String email, @Query("noteId") String noteId);
}
