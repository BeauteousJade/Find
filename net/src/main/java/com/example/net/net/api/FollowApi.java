package com.example.net.net.api;

import com.example.net.net.bean.result.ResultBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pby on 2018/2/12.
 */

public interface FollowApi {
    @POST("followServlet.jsp?action=validateFollow")
    Call<ResultBean> validateFollow(@Query("email") String email, @Query("followEmail") String followEmail);

    @POST("followServlet.jsp?action=follow")
    Call<ResultBean> follow(@Query("email") String email, @Query("followEmail") String followEmail);

    @POST("followServlet.jsp?action=cancelFollow")
    Call<ResultBean> cancelFollow(@Query("email") String email, @Query("followEmail") String followEmail);

}
