package com.example.net.net.api;




import com.example.net.net.bean.result.ResultBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by apple on 2018/1/21.
 */

public interface EmailApi {
    @POST("emailServlet.jsp")
    Call<ResultBean> sendEmail(@Query("email") String email);

}
