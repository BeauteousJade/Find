package com.example.net.net.api;


import com.example.net.net.bean.result.ResultBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pby on 2018/2/24.
 */

public interface CollectionApi {
    @POST("collectionServlet.jsp?action=validateCollection")
    Call<ResultBean> validateCollection(@Query("email") String email, @Query("noteId") String noteId);

    @POST("collectionServlet.jsp?action=collection")
    Call<ResultBean> collection(@Query("email") String email, @Query("noteId") String noteId);

    @POST("collectionServlet.jsp?action=cancelCollection")
    Call<ResultBean> cancelCollection(@Query("email") String email, @Query("noteId") String noteId);
}
