package com.example.pby.find.mvp.model;

import com.example.net.inter.impl.CollectionOperation;
import com.example.net.inter.impl.DiscussOperation;
import com.example.net.inter.impl.FollowOperation;
import com.example.net.inter.impl.MessageOperation;
import com.example.net.inter.impl.NoteOperation;
import com.example.net.inter.impl.UserOperation;
import com.example.net.net.bean.User;
import com.example.net.net.bean.result.ResultBean;
import com.example.pby.find.bean.recyclerView.DiscussBean;
import com.example.pby.find.bean.recyclerView.ImageBean;
import com.example.pby.find.bean.recyclerView.MessageDiscussBean;
import com.example.pby.find.bean.recyclerView.MessageFollowBean;
import com.example.pby.find.bean.recyclerView.NoteBean;
import com.example.pby.find.mvp.view.IResultView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by pby on 2018/2/8.
 */

public class LoadModel {
    public void getAllNote(String email, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.getAllNoteObservable(email).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void loadMoreAllNote(String email, int pos, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.loadMoreAllNoteObservable(email, pos).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void getNote(String email, String noteId, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.getNoteObservable(email, noteId).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void refreshDiscuss(String noteId, IResultView resultView, int requestCode) {
        DiscussOperation discussOperation = new DiscussOperation();
        Type type = new TypeToken<List<DiscussBean>>() {
        }.getType();
        discussOperation.refreshObservable(noteId).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void loadMoreDiscuss(String noteId, int position, IResultView resultView, int requestCode) {
        DiscussOperation discussOperation = new DiscussOperation();
        Type type = new TypeToken<List<DiscussBean>>() {
        }.getType();
        discussOperation.loadMoreDiscussObservable(noteId, position).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void refreshAll(String email, String areaName, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.refreshAllObservable(email, areaName).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void refreshHot(String email, String areaName, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.refreshHotObservable(email, areaName).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void loadMoreAll(String email, String areaName, int position, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.loadMoreAllObservable(email, areaName, position).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void loadMoreHot(String email, String areaName, int position, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.loadMoreHotObservable(email, areaName, position).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void getRandomNote(String email, IResultView resultView) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.getRandomNoteObservable(email).subscribe(buildObserver(resultView, -1, type));
    }

    public void getNote(String email, String id, IResultView resultView) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.getNoteObservable(email, id).subscribe(buildObserver(resultView, -1, type));

    }

    public void getRecNote(String email, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.getRecNoteObservable(email).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void getNewestNote(String email, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.getNewestNoteObservable(email).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void getFollowNote(String email, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.getFollowNoteObservable(email).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void loadMoreRecNote(String email, int position, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.loadMoreRecNoteObservable(email, position).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void loadMoreNewestNote(String email, int position, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.loadMoreNewestNoteObservable(email, position).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void loadMoreFollowNote(String email, int pos, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.loadMoreFollowNoteObservable(email, pos).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void validateFollow(String email, String followEmail, IResultView resultView, int requstCode) {
        FollowOperation followOperation = new FollowOperation();
        Type type = new TypeToken<List<Integer>>() {
        }.getType();
        followOperation.validateFollowObservable(email, followEmail).subscribe(buildObserver(resultView, requstCode, type));
    }

    public void validateCollection(String email, String noteId, IResultView resultView, int requstCode) {
        CollectionOperation operation = new CollectionOperation();
        Type type = new TypeToken<List<Integer>>() {
        }.getType();
        operation.validateCollectionObservable(email, noteId).subscribe(buildObserver(resultView, requstCode, type));
    }


    public void getMessageNumber(String email, IResultView resultView) {
        MessageOperation messageOperation = new MessageOperation();
        Type type = new TypeToken<List<Integer>>() {
        }.getType();
        messageOperation.getMessageNumberObservable(email).subscribe(buildObserver(resultView, -1, type));
    }

    public void getMessageDiscuss(String email, IResultView resultView, int requestCode) {
        MessageOperation messageOperation = new MessageOperation();
        Type type = new TypeToken<List<MessageDiscussBean>>() {
        }.getType();
        messageOperation.getDiscussObservable(email).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void loadMoreMessageDiscuss(String email, int pos, IResultView resultView, int requestCode) {
        MessageOperation messageOperation = new MessageOperation();
        Type type = new TypeToken<List<MessageDiscussBean>>() {
        }.getType();
        messageOperation.loadMoreDiscussObservable(email, pos).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void getMessageAppreciate(String email, IResultView resultView, int requestCode) {
        MessageOperation messageOperation = new MessageOperation();
        Type type = new TypeToken<List<MessageDiscussBean>>() {
        }.getType();
        messageOperation.getAppreciateObservable(email).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void loadMoreMessageAppreciate(String email, int pos, IResultView resultView, int requestCode) {
        MessageOperation messageOperation = new MessageOperation();
        Type type = new TypeToken<List<MessageDiscussBean>>() {
        }.getType();
        messageOperation.loadMoreAppreciateObservable(email, pos).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void getMessageReply(String email, IResultView resultView, int requestCode) {
        MessageOperation messageOperation = new MessageOperation();
        Type type = new TypeToken<List<MessageDiscussBean>>() {
        }.getType();
        messageOperation.getReplyObservable(email).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void loadMoreMessageReply(String email, int pos, IResultView resultView, int requestCode) {
        MessageOperation messageOperation = new MessageOperation();
        Type type = new TypeToken<List<MessageDiscussBean>>() {
        }.getType();
        messageOperation.loadMoreReplyObservable(email, pos).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void getFans(String email, IResultView resultView, int requestCode) {
        UserOperation operation = new UserOperation();
        Type type = new TypeToken<List<MessageFollowBean>>() {
        }.getType();
        operation.getFansObservable(email).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void loadMoreFans(String email, int pos, IResultView resultView, int requestCode) {
        UserOperation operation = new UserOperation();
        Type type = new TypeToken<List<MessageFollowBean>>() {
        }.getType();
        operation.loadMoreFansObservable(email, pos).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void getFollow(String email, IResultView resultView, int requestCode) {
        UserOperation operation = new UserOperation();
        Type type = new TypeToken<List<MessageFollowBean>>() {
        }.getType();
        operation.getFollowObservable(email).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void loadMoreFollow(String email, int pos, IResultView resultView, int requestCode) {
        UserOperation operation = new UserOperation();
        Type type = new TypeToken<List<MessageFollowBean>>() {
        }.getType();
        operation.loadMoreFollowObservable(email, pos).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void getUserInfo(String email, IResultView resultView, int requestCode) {
        UserOperation userOperation = new UserOperation();
        Type type = new TypeToken<List<User>>() {

        }.getType();
        userOperation.getUserInfoObservable(email).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void getNumberCount(String email, IResultView resultView, int requestCode) {
        UserOperation userOperation = new UserOperation();
        Type type = new TypeToken<List<Integer>>() {

        }.getType();
        userOperation.getNumberCountObservable(email).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void getAppreciateNote(String email, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.getAppreciateNoteObservable(email).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void loadMoreAppreciateNote(String email, int pos, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.loadMoreAppreciateNoteObservable(email, pos).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void getRecordNote(String email, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.getRecordNoteObservable(email).subscribe(buildObserver(resultView, requestCode, type));
    }

    public void loadMoreRecordNote(String email, int pos, IResultView resultView, int requestCode) {
        NoteOperation noteOperation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {
        }.getType();
        noteOperation.loadMoreRecordNoteObservable(email, pos).subscribe(buildObserver(resultView, requestCode, type));
    }


    public void getDiscuss(String email, int pos, IResultView resultView, int requestCode) {
        UserOperation operation = new UserOperation();
        Type type = new TypeToken<List<MessageDiscussBean>>() {
        }.getType();
        operation.getDiscussObservable(email, pos).subscribe(buildObserver(resultView, requestCode, type));
    }


    public void getHistoryHead(String email, IResultView resultView, int requestCode) {
        UserOperation operation = new UserOperation();
        Type type = new TypeToken<List<ImageBean>>() {
        }.getType();
        operation.getHistoryHeadObservable(email).subscribe(buildObserver(resultView, requestCode, type));

    }

    public void getCollectionNote(String email, int pos, IResultView resultView, int requestCode) {
        NoteOperation operation = new NoteOperation();
        Type type = new TypeToken<List<NoteBean>>() {

        }.getType();
        operation.getCollectionNoteObservable(email, pos).subscribe(buildObserver(resultView, requestCode, type));
    }

    public Observer<Response<ResultBean>> buildObserver(IResultView resultView, int requestCode, Type type) {
        return new Observer<Response<ResultBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<ResultBean> o) {
                if (o.body().getCode() == ResultBean.CODE_SUCCESS) {
                    resultView.result(null, new Gson().fromJson(o.body().getResult(), type), requestCode);
                } else {
                    resultView.result(new Exception(o.body().getMessage()), null, requestCode);
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                resultView.result(e, null, requestCode);
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
