package com.example.net.net.api;

import com.example.net.net.bean.Discuss;
import com.example.net.net.bean.result.ResultBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pby on 2018/2/9.
 */

public interface DiscussApi {
    @POST("discussServlet.jsp?action=refresh")
    Call<ResultBean> refresh(@Query("noteId") String noteId);

    @POST("discussServlet.jsp?action=loadMore")
    Call<ResultBean> loadMore(@Query("noteId") String noteId, @Query("pos") int position);

    @POST("discussServlet.jsp?action=insert")
    Call<ResultBean> insert(@Body Discuss discuss);
}
