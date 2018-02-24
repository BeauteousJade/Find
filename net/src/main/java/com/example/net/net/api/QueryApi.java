package com.example.net.net.api;



import com.example.net.net.bean.result.ResultBean;

import retrofit2.Call;
import retrofit2.http.Query;

/**
 * Created by apple on 2018/1/21.
 */

public interface QueryApi {
    Call<ResultBean> query(@Query("action") String action);

}
