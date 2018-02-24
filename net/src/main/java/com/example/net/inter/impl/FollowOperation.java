package com.example.net.inter.impl;

import com.example.net.inter.base.BaseOperation;
import com.example.net.inter.util.OperationUtil;
import com.example.net.net.api.FollowApi;
import com.example.net.net.bean.result.ResultBean;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * Created by pby on 2018/2/12.
 */

public class FollowOperation extends BaseOperation {
    public void validateFollow(String email, String followEmail) {
        final FollowApi followApi = OperationUtil.generateApi(FollowApi.class);
        Call<ResultBean> call = followApi.validateFollow(email, followEmail);
        execute(call);
    }

    public Observable validateFollowObservable(String email, String followEmail) {
        final FollowApi followApi = OperationUtil.generateApi(FollowApi.class);
        Call<ResultBean> call = followApi.validateFollow(email, followEmail);
        return executeObservable(call);
    }
    public void follow(String email, String followEmail) {
        final FollowApi followApi = OperationUtil.generateApi(FollowApi.class);
        Call<ResultBean> call = followApi.follow(email, followEmail);
        execute(call);
    }

    public Observable followObservable(String email, String followEmail) {
        final FollowApi followApi = OperationUtil.generateApi(FollowApi.class);
        Call<ResultBean> call = followApi.follow(email, followEmail);
        return executeObservable(call);
    }

    public void cancelFollow(String email, String followEmail) {
        final FollowApi followApi = OperationUtil.generateApi(FollowApi.class);
        Call<ResultBean> call = followApi.cancelFollow(email, followEmail);
        execute(call);
    }

    public Observable cancelFollowObservable(String email, String followEmail) {
        final FollowApi followApi = OperationUtil.generateApi(FollowApi.class);
        Call<ResultBean> call = followApi.follow(email, followEmail);
        return executeObservable(call);
    }

}
