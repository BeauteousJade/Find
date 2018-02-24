package com.example.net.inter.impl;

import com.example.net.inter.base.BaseOperation;
import com.example.net.inter.util.OperationUtil;
import com.example.net.net.api.EmailApi;
import com.example.net.net.bean.result.ResultBean;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * Created by apple on 2018/1/21.
 */

public class EmailOperation extends BaseOperation {
    public void sendEmail(String email) {
        EmailApi emailApi = OperationUtil.generateApi(EmailApi.class);
        Call<ResultBean> call = emailApi.sendEmail(email);
        execute(call);
    }

    public Observable sendEmailObservable(String email) {
        EmailApi emailApi = OperationUtil.generateApi(EmailApi.class);
        Call<ResultBean> call = emailApi.sendEmail(email);
        return executeObservable(call);
    }
}
