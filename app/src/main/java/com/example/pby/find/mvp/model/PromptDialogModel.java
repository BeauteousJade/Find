package com.example.pby.find.mvp.model;

import android.util.Log;


import com.example.net.inter.impl.DiscussOperation;
import com.example.net.inter.impl.EmailOperation;
import com.example.net.inter.impl.NoteOperation;
import com.example.net.inter.impl.UserOperation;
import com.example.net.net.bean.Discuss;
import com.example.net.net.bean.User;
import com.example.net.net.bean.result.ResultBean;
import com.example.pby.find.mvp.view.IPromptDialogView;
import com.example.pby.find.mvp.view.IResultView;
import com.example.pby.find.util.IntUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by pby on 2018/1/24.
 */

public class PromptDialogModel {
    public void login(String email, String pwd, IResultView view, IPromptDialogView view1) {
        view1.changeStatus(true);
        UserOperation userOperation = new UserOperation();
        userOperation.loginObservable(email, pwd).subscribe(new Observer<Response<ResultBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Response<ResultBean> o) {
                if (o.body().getCode() == ResultBean.CODE_SUCCESS) {
                    //Json的解析类对象
                    JsonParser parser = new JsonParser();
                    //将JSON的String 转成一个JsonArray对象
                    JsonArray jsonArray = parser.parse(o.body().getResult()).getAsJsonArray();
                    Gson gson = new Gson();
                    User user = gson.fromJson(jsonArray.get(0), User.class);
                    user.setLevel(IntUtil.buildLevel(user.getExperience()));
                    UserOperation.setCurrentUser(user);
                    view.result(null, o.body().getMessage(), -1);
                } else {
                    view.result(new Exception(o.body().getMessage()), null, -1);
                }
                view1.changeStatus(false);
            }

            @Override
            public void onError(Throwable e) {
                view1.changeStatus(false);
                view.result(e, null, -1);
            }

            @Override
            public void onComplete() {
                view1.changeStatus(false);
            }
        });
    }

    public void sendEmail(String email, IResultView view, IPromptDialogView view1) {
        view1.changeStatus(true);
        EmailOperation emailOperation = new EmailOperation();
        emailOperation.sendEmailObservable(email).subscribe(builderObservable(view, view1, -1));
    }

    public void register(User user, String code, IResultView view, IPromptDialogView view1) {
        view1.changeStatus(true);
        UserOperation userOperation = new UserOperation();
        userOperation.registerObservable(code, user).subscribe(builderObservable(view, view1, -1));
    }

    public void modify(User user, String code, IResultView view, IPromptDialogView view1) {
        view1.changeStatus(true);
        UserOperation userOperation = new UserOperation();
        userOperation.modifyPasswordObservable(code, user).subscribe(builderObservable(view, view1, -1));
    }

    public void insertNote(String email, String areaName, String content, List<String> pathList, IResultView view, IPromptDialogView view1) {
        view1.changeStatus(true);
        NoteOperation operation = new NoteOperation();
        operation.insertNoteObservable(email, areaName, content, pathList).subscribe(builderObservable(view, view1, -1));
    }

    public void insertDiscuss(Discuss discuss, IResultView view, IPromptDialogView view1) {
        view1.changeStatus(true);
        DiscussOperation discussOperation = new DiscussOperation();
        discussOperation.insertDiscussObservable(discuss).subscribe(builderObservable(view, view1, -1));
    }

    public void updateHead(String email, String path, IResultView view, IPromptDialogView view1) {
        view1.changeStatus(true);
        UserOperation userOperation = new UserOperation();
        userOperation.updateHeadObservable(email, path).subscribe(builderObservable(view, view1, -1));
    }

    public void resetPassword(String email, String oldPwd, String newPwd, IResultView view, IPromptDialogView view1) {
        view1.changeStatus(true);
        UserOperation operation = new UserOperation();
        operation.resetPwdObservable(email, oldPwd, newPwd).subscribe(builderObservable(view, view1, -1));
    }

    public Observer builderObservable(IResultView view, IPromptDialogView view1, int requestCode) {
        return new Observer<Response<ResultBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<ResultBean> o) {
                Log.i("pby123", "" + o.body().getCode());
                if (o.body().getCode() == ResultBean.CODE_SUCCESS) {
                    view.result(null, o.body(), -1);
                } else {
                    view.result(new Exception(o.body().getMessage()), null, -1);
                }
                view1.changeStatus(false);
            }

            @Override
            public void onError(Throwable e) {
                view1.changeStatus(false);
                view.result(e, null, -1);
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
