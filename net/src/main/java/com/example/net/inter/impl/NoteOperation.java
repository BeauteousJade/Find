package com.example.net.inter.impl;

import android.util.Log;

import com.example.net.inter.base.BaseOperation;
import com.example.net.inter.util.OperationUtil;
import com.example.net.net.api.NoteApi;
import com.example.net.net.bean.Note;
import com.example.net.net.bean.result.ResultBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by pby on 2018/2/8.
 */

public class NoteOperation extends BaseOperation {
    public void insertNote(String email, String areaName, String content, List<String> images) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        final Note note = new Note();
        note.setEmail(email);
        note.setAreaName(areaName);
        note.setContent(content);
        note.setImages(images);
        FileOperation fileOperation = new FileOperation();
        if (images.size() != 0) {
            fileOperation.uploadObservable(images, email).doOnNext(o -> {
            }).flatMap(resultBeanResponse -> {

                Type type = new TypeToken<List<String>>() {
                }.getType();
                Gson gson = new Gson();
                List<String> list = gson.fromJson(((Response<ResultBean>) resultBeanResponse).body().getResult(), type);
                note.setImages(list);
                Call<ResultBean> call = noteApi.insertNote(note);
                return executeObservable(call);
            }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(resultBeanResponse -> Log.i("pby123", "" + ((Response<ResultBean>) resultBeanResponse).body()));
        } else {
            Call<ResultBean> call = noteApi.insertNote(note);
            execute(call);
        }
    }

    public Observable insertNoteObservable(String email, String areaName, String content, List<String> images) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        final Note note = new Note();
        note.setEmail(email);
        note.setAreaName(areaName);
        note.setContent(content);
        note.setImages(images);
        FileOperation fileOperation = new FileOperation();
        if (images.size() == 0) {
            Call<ResultBean> call = noteApi.insertNote(note);
            return executeObservable(call);
        } else {
            return fileOperation.uploadObservable(images, email).doOnNext(o -> {
            }).flatMap(resultBeanResponse -> {
                Type type = new TypeToken<List<String>>() {
                }.getType();
                Gson gson = new Gson();
                List<String> list = gson.fromJson(((Response<ResultBean>) resultBeanResponse).body().getResult(), type);
                note.setImages(list);
                Call<ResultBean> call = noteApi.insertNote(note);
                return executeObservable(call);
            }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
        }
    }

    public void getAllNote(String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getAllNote(email);
        execute(call);
    }

    public Observable getAllNoteObservable(String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getAllNote(email);
        return executeObservable(call);
    }


    public void loadMoreAllNote(String email, int pos) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreAllNote(email, pos);
        execute(call);
    }

    public Observable loadMoreAllNoteObservable(String email, int pos) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreAllNote(email, pos);
        return executeObservable(call);
    }

    public void appreciate(String id, String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.appreciate(id, email);
        execute(call);
    }

    public Observable appreciateObservable(String id, String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.appreciate(id, email);
        return executeObservable(call);
    }

    public void cancelAppreciate(String id, String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.cancelAppreciate(id, email);
        execute(call);
    }

    public Observable cancelAppreciateObservable(String id, String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.cancelAppreciate(id, email);
        return executeObservable(call);
    }


    public void refreshAll(String email, String areaName) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.refreshAll(email, areaName);
        execute(call);
    }

    public Observable refreshAllObservable(String email, String areaName) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.refreshAll(email, areaName);
        return executeObservable(call);
    }

    public void refreshHot(String email, String areaName) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.refreshHot(email, areaName);
        execute(call);
    }

    public Observable refreshHotObservable(String email, String areaName) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.refreshHot(email, areaName);
        return executeObservable(call);
    }

    public void loadMoreAll(String email, String areaName, int position) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreAll(email, areaName, position);
        execute(call);
    }

    public Observable loadMoreAllObservable(String email, String areaName, int position) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreAll(email, areaName, position);
        return executeObservable(call);
    }

    public void loadMoreHot(String email, String areaName, int position) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreHot(email, areaName, position);
        execute(call);
    }

    public Observable loadMoreHotObservable(String email, String areaName, int position) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreHot(email, areaName, position);
        return executeObservable(call);
    }

    public void addReadNumber(String noteId) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.addReadNumber(noteId);
        execute(call);
    }

    public Observable addReadNumberObservable(String noteId) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.addReadNumber(noteId);
        return executeObservable(call);
    }

    public void getRandomNote(String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getRandomNote(email);
        execute(call);
    }

    public Observable getRandomNoteObservable(String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getRandomNote(email);
        return executeObservable(call);
    }

    public void getNote(String email, String id) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getNote(email, id);
        execute(call);
    }

    public Observable getNoteObservable(String email, String id) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getNote(email, id);
        return executeObservable(call);
    }

    public void getRecNote(String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getRecNote(email);
        execute(call);
    }

    public Observable getRecNoteObservable(String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getRecNote(email);
        return executeObservable(call);
    }

    public void getNewestNote(String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getNewestNote(email);
        execute(call);
    }

    public Observable getNewestNoteObservable(String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getNewestNote(email);
        return executeObservable(call);
    }


    public void getFollowNote(String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getFollowNote(email);
        execute(call);
    }

    public Observable getFollowNoteObservable(String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getFollowNote(email);
        return executeObservable(call);
    }

    public void loadMoreRecNote(String email, int position) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreRecNote(email, position);
        execute(call);
    }

    public Observable loadMoreRecNoteObservable(String email, int position) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreRecNote(email, position);
        return executeObservable(call);
    }

    public void loadMoreNewestNote(String email, int position) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreNewestNote(email, position);
        execute(call);
    }

    public Observable loadMoreNewestNoteObservable(String email, int position) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreNewestNote(email, position);
        return executeObservable(call);
    }


    public void loadMoreFollowNote(String email, int pos) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreFollowNote(email, pos);
        execute(call);
    }

    public Observable loadMoreFollowNoteObservable(String email, int pos) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreFollowNote(email, pos);
        return executeObservable(call);
    }

    public void getAppreciateNote(String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getAppreciateNote(email);
        execute(call);
    }

    public Observable getAppreciateNoteObservable(String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getAppreciateNote(email);
        return executeObservable(call);
    }

    public void loadMoreAppreciateNote(String email, int pos) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreAppreciateNote(email, pos);
        execute(call);
    }

    public Observable loadMoreAppreciateNoteObservable(String email, int pos) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreAppreciateNote(email, pos);
        return executeObservable(call);
    }

    public void getRecordNote(String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getRecordNote(email);
        execute(call);
    }

    public Observable getRecordNoteObservable(String email) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getRecordNote(email);
        return executeObservable(call);
    }

    public void loadMoreRecordNote(String email, int pos) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreRecordNote(email, pos);
        execute(call);
    }

    public Observable loadMoreRecordNoteObservable(String email, int pos) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.loadMoreAppreciateNote(email, pos);
        return executeObservable(call);
    }

    public Observable getCollectionNoteObservable(String email, int pos) {
        final NoteApi noteApi = OperationUtil.generateApi(NoteApi.class);
        Call<ResultBean> call = noteApi.getCollectionNote(email, pos);
        return executeObservable(call);
    }

    public void getCollectionNote(String email, int pos) {
        executeObservable(getCollectionNoteObservable(email, pos));
    }
}
