package com.example.net.net.api;


import com.example.net.net.bean.Note;
import com.example.net.net.bean.result.ResultBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pby on 2018/2/8.
 */

public interface NoteApi {
    @POST("noteServlet.jsp?action=insert")
    Call<ResultBean> insertNote(@Body Note note);

    @POST("noteServlet.jsp?action=getAllNote")
    Call<ResultBean> getAllNote(@Query("email") String email);

    @POST("noteServlet.jsp?action=loadMoreAllNote")
    Call<ResultBean> loadMoreAllNote(@Query("email") String email, @Query("pos") int pos);


    @POST("noteServlet.jsp?action=appreciate")
    Call<ResultBean> appreciate(@Query("noteId") String id, @Query("email") String email);

    @POST("noteServlet.jsp?action=cancelAppreciate")
    Call<ResultBean> cancelAppreciate(@Query("noteId") String id, @Query("email") String email);

    @POST("noteServlet.jsp?action=refreshAll")
    Call<ResultBean> refreshAll(@Query("email") String email, @Query("areaName") String areaName);

    @POST("noteServlet.jsp?action=refreshHot")
    Call<ResultBean> refreshHot(@Query("email") String email, @Query("areaName") String areaName);

    @POST("noteServlet.jsp?action=loadMoreAll")
    Call<ResultBean> loadMoreAll(@Query("email") String email, @Query("areaName") String areaName, @Query("pos") int position);

    @POST("noteServlet.jsp?action=loadMoreHot")
    Call<ResultBean> loadMoreHot(@Query("email") String email, @Query("areaName") String areaName, @Query("pos") int position);

    @POST("noteServlet.jsp?action=addReadNumber")
    Call<ResultBean> addReadNumber(@Query("noteId") String id);


    @POST("noteServlet.jsp?action=getRandomNote")
    Call<ResultBean> getRandomNote(@Query("email") String email);

    @POST("noteServlet.jsp?action=getNote")
    Call<ResultBean> getNote(@Query("email") String email, @Query("noteId") String id);

    @POST("noteServlet.jsp?action=getRecNote")
    Call<ResultBean> getRecNote(@Query("email") String email);

    @POST("noteServlet.jsp?action=getNewestNote")
    Call<ResultBean> getNewestNote(@Query("email") String email);

    @POST("noteServlet.jsp?action=getFollowNote")
    Call<ResultBean> getFollowNote(@Query("email") String email);


    @POST("noteServlet.jsp?action=loadMoreRecNote")
    Call<ResultBean> loadMoreRecNote(@Query("email") String email, @Query("pos") int pos);

    @POST("noteServlet.jsp?action=loadMoreNewestNote")
    Call<ResultBean> loadMoreNewestNote(@Query("email") String email, @Query("pos") int position);

    @POST("noteServlet.jsp?action=loadMoreFollowNote")
    Call<ResultBean> loadMoreFollowNote(@Query("email") String email, @Query("pos") int pos);

    @POST("noteServlet.jsp?action=getAppreciateNote")
    Call<ResultBean> getAppreciateNote(@Query("email") String email);

    @POST("noteServlet.jsp?action=loadMoreAppreciateNote")
    Call<ResultBean> loadMoreAppreciateNote(@Query("email") String email, @Query("pos") int pos);


    @POST("noteServlet.jsp?action=getRecordNote")
    Call<ResultBean> getRecordNote(@Query("email") String email);

    @POST("noteServlet.jsp?action=loadMoreRecordNote")
    Call<ResultBean> loadMoreRecordNote(@Query("email") String email, @Query("pos") int pos);

    @POST("noteServlet.jsp?action=getCollectionNote")
    Call<ResultBean> getCollectionNote(@Query("email") String email, @Query("pos") int pos);
}
