package com.example.net.net.api;


import com.example.net.net.bean.result.ResultBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pby on 2018/1/22.
 */

public interface AreaApi {
    @POST("areaServlet.jsp")
    Call<ResultBean> getAllArea(@Query("action") String action);

}
