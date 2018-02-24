package com.example.net.net.api;


import com.example.net.net.bean.User;
import com.example.net.net.bean.result.ResultBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by apple on 2018/1/21.
 */

public interface UserApi {

    @POST("userServlet.jsp")
    Call<ResultBean> login(@Query("action") String action, @Body User user);

    @POST("userServlet.jsp")
    Call<ResultBean> login(@Query("action") String action, @Query("email") String email, @Query("pwd") String pwd);

    @POST("userServlet.jsp")
    Call<ResultBean> register(@Query("action") String action, @Query("code") String code, @Body User user);

    @POST("userServlet.jsp")
    Call<ResultBean> modifyPassword(@Query("action") String action, @Query("code") String code, @Body User user);

    @POST("userServlet.jsp?action=getUserInfo")
    Call<ResultBean> getUserInfo(@Query("email") String email);

    @POST("userServlet.jsp?action=getNumberCount")
    Call<ResultBean> getNumberCount(@Query("email") String email);

    @POST("userServlet.jsp?action=getFans")
    Call<ResultBean> getFans(@Query("email") String email);

    @POST("userServlet.jsp?action=loadMoreFans")
    Call<ResultBean> loadMoreFans(@Query("email") String email, @Query("pos") int pos);


    @POST("userServlet.jsp?action=getFollow")
    Call<ResultBean> getFollow(@Query("email") String email);

    @POST("userServlet.jsp?action=loadMoreFollow")
    Call<ResultBean> loadMoreFollow(@Query("email") String email, @Query("pos") int pos);


    @POST("userServlet.jsp?action=getDiscuss")
    Call<ResultBean> getDiscuss(@Query("email") String email, @Query("pos") int pos);

    @POST("userServlet.jsp?action=updateHead")
    Call<ResultBean> updateHead(@Query("email") String email, @Query("head") String head);

    @POST("userServlet.jsp?action=getHistoryHead")
    Call<ResultBean> getHistoryHead(@Query("email") String email);


    @POST("userServlet.jsp?action=setHead")
    Call<ResultBean> setHead(@Query("email") String email, @Query("head") String head);

    @POST("userServlet.jsp?action=deleteHistoryHead")
    Call<ResultBean> deleteHistoryHead(@Query("email") String email, @Query("head") String head);

    @POST("userServlet.jsp?action=setName")
    Call<ResultBean> setName(@Query("email") String email, @Query("name") String name);

    @POST("userServlet.jsp?action=setAutograph")
    Call<ResultBean> setAutograph(@Query("email") String email, @Query("autograph") String autograph);


    @POST("userServlet.jsp?action=resetPwd")
    Call<ResultBean> resetPwd(@Query("email") String email, @Query("old") String oldPwd, @Query("new") String newPwd);
}
