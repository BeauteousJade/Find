package com.example.net.inter.impl;

import com.example.net.inter.base.BaseOperation;
import com.example.net.inter.util.OperationUtil;
import com.example.net.net.api.MessageApi;
import com.example.net.net.bean.result.ResultBean;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * Created by pby on 2018/2/12.
 */

public class MessageOperation extends BaseOperation {
    public void getMessageNumber(String email) {
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.getMessageNumber(email);
        execute(call);
    }

    public Observable getMessageNumberObservable(String email) {
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.getMessageNumber(email);
        return executeObservable(call);
    }


    public void getDiscuss(String email) {
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.getDiscuss(email);
        execute(call);
    }

    public Observable getDiscussObservable(String email) {
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.getDiscuss(email);
        return executeObservable(call);
    }

    public void loadMoreDiscuss(String email, int pos) {
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.loadMoreDiscuss(email, pos);
        execute(call);
    }

    public Observable loadMoreDiscussObservable(String email, int pos) {
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.loadMoreDiscuss(email, pos);
        return executeObservable(call);
    }


    public void getAppreciate(String email) {
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.getAppreciate(email);
        execute(call);
    }

    public Observable getAppreciateObservable(String email) {
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.getAppreciate(email);
        return executeObservable(call);
    }

    public void loadMoreAppreciate(String email, int pos) {
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.loadMoreAppreciate(email, pos);
        execute(call);
    }

    public Observable loadMoreAppreciateObservable(String email, int pos) {
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.loadMoreAppreciate(email, pos);
        return executeObservable(call);
    }


    public void getReply(String email) {
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.getReply(email);
        execute(call);
    }

    public Observable getReplyObservable(String email) {
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.getReply(email);
        return executeObservable(call);
    }


    public void loadMoreReply(String email, int pos) {
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.loadMoreReply(email, pos);
        execute(call);
    }

    public Observable loadMoreReplyObservable(String email, int pos) {
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.loadMoreReply(email, pos);
        return executeObservable(call);
    }



    public void readDiscuss(String email){
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.readDiscuss(email);
        execute(call);
    }

    public Observable readDiscussObservable(String email){
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.readDiscuss(email);
        return executeObservable(call);
    }

    public void readAppreciate(String email){
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.readAppreciate(email);
        execute(call);
    }

    public Observable readAppreciateObservable(String email){
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.readAppreciate(email);
        return executeObservable(call);
    }

    public void readFollow(String email){
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.readFollow(email);
        execute(call);
    }

    public Observable readFollowObservable(String email){
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.readFollow(email);
        return executeObservable(call);
    }

    public void readReply(String email){
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.readReply(email);
        execute(call);
    }

    public Observable readReplyObservable(String email){
        MessageApi messageApi = OperationUtil.generateApi(MessageApi.class);
        Call<ResultBean> call = messageApi.readReply(email);
        return executeObservable(call);
    }
}
