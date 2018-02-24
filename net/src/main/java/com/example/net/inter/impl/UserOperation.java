package com.example.net.inter.impl;


import android.util.Log;

import com.example.net.inter.base.BaseOperation;
import com.example.net.inter.util.OperationUtil;
import com.example.net.net.api.MessageApi;
import com.example.net.net.api.UserApi;
import com.example.net.net.bean.User;
import com.example.net.net.bean.result.ResultBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by apple on 2018/1/21.
 */

public class UserOperation extends BaseOperation {
    private static User user = null;


    public void register(String code, User user) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        final Call<ResultBean> call = userApi.register("register", code, user);
        execute(call);
    }

    public Observable registerObservable(String code, User user) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        final Call<ResultBean> call = userApi.register("register", code, user);
        return executeObservable(call);
    }

    public void login(String email, String pwd) {
        final String e = email;
        final String p = pwd;
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        final Call<ResultBean> call = userApi.login("login", e, p);
        execute(call);
    }

    public Observable loginObservable(String email, String pwd) {
        final String e = email;
        final String p = pwd;
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        final Call<ResultBean> call = userApi.login("login", e, p);
        return executeObservable(call);
    }


    public void modifyPassword(String code, User user) {
        final User u = user;
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        final Call<ResultBean> call = userApi.modifyPassword("modify", code, u);
        execute(call);
    }

    public Observable modifyPasswordObservable(String code, User user) {
        final User u = user;
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        final Call<ResultBean> call = userApi.modifyPassword("modify", code, u);
        return executeObservable(call);
    }

    public void getUserInfo(String email) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.getUserInfo(email);
        execute(call);
    }

    public Observable getUserInfoObservable(String email) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.getUserInfo(email);
        return executeObservable(call);
    }

    public void getNumberCount(String email) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.getNumberCount(email);
        execute(call);
    }

    public Observable getNumberCountObservable(String email) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.getNumberCount(email);
        return executeObservable(call);
    }

    public void getFans(String email) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.getFans(email);
        execute(call);
    }

    public Observable getFansObservable(String email) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.getFans(email);
        return executeObservable(call);
    }

    public void loadMoreFans(String email, int pos) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.loadMoreFans(email, pos);
        execute(call);
    }

    public Observable loadMoreFansObservable(String email, int pos) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.loadMoreFans(email, pos);
        return executeObservable(call);
    }

    public void getFollow(String email) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.getFollow(email);
        execute(call);
    }

    public Observable getFollowObservable(String email) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.getFollow(email);
        return executeObservable(call);
    }

    public void loadMoreFollow(String email, int pos) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.loadMoreFollow(email, pos);
        execute(call);
    }

    public Observable loadMoreFollowObservable(String email, int pos) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.loadMoreFollow(email, pos);
        return executeObservable(call);
    }

    public void getDiscuss(String email, int pos) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.getDiscuss(email, pos);
        execute(call);
    }


    public Observable getDiscussObservable(String email, int pos) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.getDiscuss(email, pos);
        return executeObservable(call);
    }


    public void updateHead(String email, String path) {
        final FileOperation fileOperation = new FileOperation();
        fileOperation.uploadObservable(Arrays.asList(path), email).doOnNext(o -> {
        }).flatMap(resultBeanResponse -> {
            Type type = new TypeToken<List<String>>() {
            }.getType();
            Gson gson = new Gson();
            List<String> list = gson.fromJson(((Response<ResultBean>) resultBeanResponse).body().getResult(), type);
            final UserApi userApi = OperationUtil.generateApi(UserApi.class);
            Call<ResultBean> call = userApi.updateHead(email, path);
            userApi.updateHead(email, path);
            return executeObservable(call);
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(resultBeanResponse -> Log.i("pby123", "" + ((Response<ResultBean>) resultBeanResponse).body()));
    }

    public Observable updateHeadObservable(String email, String path) {
        final FileOperation fileOperation = new FileOperation();
        return fileOperation.uploadObservable(Arrays.asList(path), email).doOnNext(o -> {
        }).flatMap(resultBeanResponse -> {
            Type type = new TypeToken<List<String>>() {
            }.getType();
            Gson gson = new Gson();
            List<String> list = gson.fromJson(((Response<ResultBean>) resultBeanResponse).body().getResult(), type);
            final UserApi userApi = OperationUtil.generateApi(UserApi.class);
            Call<ResultBean> call = userApi.updateHead(email, list.get(0));
            userApi.updateHead(email, path);
            return executeObservable(call);
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }


    public void setHead(String email, String head) {
        final UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.setHead(email, head);
        execute(call);
    }

    public Observable setHeadObservable(String email, String head) {
        final UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.setHead(email, head);
        return executeObservable(call);
    }

    public void getHistoryHead(String email) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.getHistoryHead(email);
        execute(call);
    }

    public Observable getHistoryHeadObservable(String email) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.getHistoryHead(email);
        return executeObservable(call);
    }


    public void deleteHistoryHead(String email, String url) {
        deleteHistoryHeadObservable(email, url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultBeanResponse -> Log.i("pby123", "" + ((Response<ResultBean>) resultBeanResponse).body()));
    }

    public Observable deleteHistoryHeadObservable(String email, String head) {
        final FileOperation fileOperation = new FileOperation();
        return fileOperation.deleteObservable(Arrays.asList(head)).doOnNext(o -> {
        }).flatMap(resultBeanResponse -> {

            final UserApi userApi = OperationUtil.generateApi(UserApi.class);
            Call<ResultBean> call = userApi.deleteHistoryHead(email, head);
            return executeObservable(call);
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public void setName(String email, String name) {
        executeObservable(setNameObservable(email, name));
    }

    public Observable setNameObservable(String email, String name) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.setName(email, name);
        return executeObservable(call);
    }


    public void setAutograph(String email, String autograph) {
        executeObservable(setAutographObservable(email, autograph));
    }

    public Observable setAutographObservable(String email, String autograph) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.setAutograph(email, autograph);
        return executeObservable(call);
    }

    public void resetPwd(String email, String oldPwd, String newPwd) {
        executeObservable(resetPwdObservable(email, oldPwd, newPwd));

    }

    public Observable resetPwdObservable(String email, String oldPwd, String newPwd) {
        UserApi userApi = OperationUtil.generateApi(UserApi.class);
        Call<ResultBean> call = userApi.resetPwd(email, oldPwd, newPwd);
        return executeObservable(call);
    }

    public static User getCurrentUser() {
        return user;
    }

    public static void setCurrentUser(User u) {
        user = u;
    }
}
