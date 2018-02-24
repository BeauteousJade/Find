package com.example.net.inter.impl;

import android.util.Log;

import com.example.net.inter.base.BaseOperation;
import com.example.net.inter.util.OperationUtil;
import com.example.net.net.api.DiscussApi;
import com.example.net.net.bean.Discuss;
import com.example.net.net.bean.result.ResultBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by pby on 2018/2/9.
 */

public class DiscussOperation extends BaseOperation {
    public void refresh(String noteId) {
        DiscussApi discussApi = OperationUtil.generateApi(DiscussApi.class);
        Call<ResultBean> call = discussApi.refresh(noteId);
        execute(call);
    }

    public Observable refreshObservable(String noteId) {
        DiscussApi discussApi = OperationUtil.generateApi(DiscussApi.class);
        Call<ResultBean> call = discussApi.refresh(noteId);
        return executeObservable(call);
    }

    public void loadMoreDiscuss(String noteId, int position) {
        DiscussApi discussApi = OperationUtil.generateApi(DiscussApi.class);
        Call<ResultBean> call = discussApi.loadMore(noteId, position);
        execute(call);
    }

    public Observable loadMoreDiscussObservable(String noteId, int position) {
        DiscussApi discussApi = OperationUtil.generateApi(DiscussApi.class);
        Call<ResultBean> call = discussApi.loadMore(noteId, position);
        return executeObservable(call);
    }

    public void insertDiscuss(Discuss discuss) {
        DiscussApi discussApi = OperationUtil.generateApi(DiscussApi.class);
        if (discuss.getImages() != null && discuss.getImages().size() != 0) {
            FileOperation fileOperation = new FileOperation();
            fileOperation
                    .uploadObservable(discuss.getImages(), discuss.getEmail()).doOnNext(o -> {
            })
                    .flatMap(resultBeanResponse -> {

                        Type type = new TypeToken<List<String>>() {
                        }.getType();
                        Gson gson = new Gson();
                        List<String> list = gson.fromJson(((Response<ResultBean>) resultBeanResponse).body().getResult(), type);
                        discuss.setImages(list);
                        Call<ResultBean> call = discussApi.insert(discuss);
                        return executeObservable(call);
                    })
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(resultBeanResponse -> Log.i("pby123", "" + ((Response<ResultBean>) resultBeanResponse).body()));
        } else {
            Call<ResultBean> call = discussApi.insert(discuss);
            execute(call);
        }
    }

    public Observable insertDiscussObservable(Discuss discuss) {
        DiscussApi discussApi = OperationUtil.generateApi(DiscussApi.class);
        if (discuss.getImages() != null && discuss.getImages().size() != 0) {
            FileOperation fileOperation = new FileOperation();
            return fileOperation
                    .uploadObservable(discuss.getImages(), discuss.getEmail()).doOnNext(o -> {
                    })
                    .flatMap(resultBeanResponse -> {

                        Type type = new TypeToken<List<String>>() {
                        }.getType();
                        Gson gson = new Gson();
                        List<String> list = gson.fromJson(((Response<ResultBean>) resultBeanResponse).body().getResult(), type);
                        discuss.setImages(list);
                        Call<ResultBean> call = discussApi.insert(discuss);
                        return executeObservable(call);
                    })
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());

        } else {
            Call<ResultBean> call = discussApi.insert(discuss);
            return executeObservable(call);
        }
    }
}
