package com.example.net.net.api;

import com.example.net.net.bean.result.ResultBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pby on 2018/2/12.
 */

public interface MessageApi {
    @POST("messageServlet.jsp?action=getMessageNumber")
    Call<ResultBean> getMessageNumber(@Query("email") String email);

    @POST("messageServlet.jsp?action=getDiscuss")
    Call<ResultBean> getDiscuss(@Query("email") String email);

    @POST("messageServlet.jsp?action=loadMoreDiscuss")
    Call<ResultBean> loadMoreDiscuss(@Query("email") String email, @Query("pos") int pos);


    @POST("messageServlet.jsp?action=getAppreciate")
    Call<ResultBean> getAppreciate(@Query("email") String email);

    @POST("messageServlet.jsp?action=loadMoreAppreciate")
    Call<ResultBean> loadMoreAppreciate(@Query("email") String email, @Query("pos") int pos);

    @POST("messageServlet.jsp?action=getReply")
    Call<ResultBean> getReply(@Query("email") String email);

    @POST("messageServlet.jsp?action=loadMoreReply")
    Call<ResultBean> loadMoreReply(@Query("email") String email, @Query("pos") int pos);


    @POST("messageServlet.jsp?action=readDiscuss")
    Call<ResultBean> readDiscuss(@Query("email") String email);

    @POST("messageServlet.jsp?action=readAppreciate")
    Call<ResultBean> readAppreciate(@Query("email") String email);

    @POST("messageServlet.jsp?action=readFollow")
    Call<ResultBean> readFollow(@Query("email") String email);

    @POST("messageServlet.jsp?action=readReply")
    Call<ResultBean> readReply(@Query("email") String email);
}
