package com.example.pby.find.mvp.presenter;


import com.example.net.net.bean.Discuss;
import com.example.net.net.bean.User;
import com.example.pby.find.mvp.model.PromptDialogModel;
import com.example.pby.find.mvp.view.IPromptDialogView;
import com.example.pby.find.mvp.view.IResultView;

import java.util.List;

/**
 * Created by pby on 2018/1/24.
 */

public class PromptDialogPresenter {
    private PromptDialogModel mModel = null;

    public PromptDialogPresenter() {
        mModel = new PromptDialogModel();
    }

    public void login(String email, String pwd, IResultView view, IPromptDialogView view1) {
        mModel.login(email, pwd, view, view1);
    }

    public void sendEmail(String email, IResultView view, IPromptDialogView view1) {
        mModel.sendEmail(email, view, view1);
    }

    public void register(User user, String code, IResultView view, IPromptDialogView view1) {
        mModel.register(user, code, view, view1);
    }

    public void modify(User user, String code, IResultView view, IPromptDialogView view1) {
        mModel.modify(user, code, view, view1);
    }

    public void insertNote(String email, String areaName, String content, List<String> pathList, IResultView view, IPromptDialogView view1) {
        mModel.insertNote(email, areaName, content, pathList, view, view1);
    }

    public void insertDiscuss(Discuss discuss, IResultView view, IPromptDialogView view1) {
        mModel.insertDiscuss(discuss, view, view1);
    }

    public void updateHead(String email, String path, IResultView view, IPromptDialogView view1) {
        mModel.updateHead(email, path, view, view1);
    }
    public void resetPassword(String email, String oldPwd, String newPwd, IResultView view, IPromptDialogView view1){
        mModel.resetPassword(email, oldPwd, newPwd, view, view1);
    }
}
