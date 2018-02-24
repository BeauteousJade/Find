package com.example.net.inter.impl;

import com.example.net.inter.base.BaseOperation;
import com.example.net.inter.util.OperationUtil;
import com.example.net.net.api.CollectionApi;
import com.example.net.net.bean.result.ResultBean;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * Created by pby on 2018/2/24.
 */

public class CollectionOperation extends BaseOperation {

    public Observable validateCollectionObservable(String email, String noteId) {
        CollectionApi api = OperationUtil.generateApi(CollectionApi.class);
        Call<ResultBean> call = api.validateCollection(email, noteId);
        return executeObservable(call);
    }

    public void validateCollection(String email, String noteId) {
        executeObservable(validateCollectionObservable(email, noteId));
    }

    public Observable collectionObservable(String email, String noteId) {
        CollectionApi api = OperationUtil.generateApi(CollectionApi.class);
        Call<ResultBean> call = api.collection(email, noteId);
        return executeObservable(call);
    }
    public void collection(String email, String noteId) {
        executeObservable(collectionObservable(email, noteId));
    }
    public Observable cancelCollectionObservable(String email, String noteId) {
        CollectionApi api = OperationUtil.generateApi(CollectionApi.class);
        Call<ResultBean> call = api.cancelCollection(email, noteId);
        return executeObservable(call);
    }
    public void cancelCollection(String email, String noteId) {
        executeObservable(cancelCollectionObservable(email, noteId));
    }
}
