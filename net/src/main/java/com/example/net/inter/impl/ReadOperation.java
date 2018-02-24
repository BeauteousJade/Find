package com.example.net.inter.impl;

import com.example.net.inter.base.BaseOperation;
import com.example.net.inter.util.OperationUtil;
import com.example.net.net.api.ReadApi;
import com.example.net.net.bean.result.ResultBean;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * Created by pby on 2018/2/12.
 */

public class ReadOperation extends BaseOperation {

    public void addRead(String email, String noteId){
        ReadApi readApi = OperationUtil.generateApi(ReadApi.class);
        Call<ResultBean> call = readApi.addRead(email, noteId);
        execute(call);
    }

    public Observable addReadObservable(String email, String noteId){
        ReadApi readApi = OperationUtil.generateApi(ReadApi.class);
        Call<ResultBean> call = readApi.addRead(email, noteId);
        return executeObservable(call);
    }
}
