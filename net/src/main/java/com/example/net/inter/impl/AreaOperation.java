package com.example.net.inter.impl;




import com.example.net.inter.base.BaseOperation;
import com.example.net.inter.util.OperationUtil;
import com.example.net.net.api.AreaApi;
import com.example.net.net.bean.result.ResultBean;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * Created by pby on 2018/1/22.
 */

public class AreaOperation extends BaseOperation {
    public void getAllArea() {
        AreaApi areaApi = OperationUtil.generateApi(AreaApi.class);
        Call<ResultBean> call = areaApi.getAllArea("getAllArea");
        execute(call);
    }

    public Observable getAllAreaObservable() {
        AreaApi areaApi = OperationUtil.generateApi(AreaApi.class);
        Call<ResultBean> call = areaApi.getAllArea("getAllArea");
        return executeObservable(call);
    }
}
