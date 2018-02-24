package com.example.pby.find.mvp.presenter;

import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.mvp.model.LoadModel;
import com.example.pby.find.mvp.view.IResultView;

/**
 * Created by pby on 2018/2/8.
 */

public class LoadPresenter {
    private LoadModel mNormalModel = null;

    public LoadPresenter() {
        mNormalModel = new LoadModel();
    }

    public void getAllNote(String email, IResultView resultView, int requestCode) {
        mNormalModel.getAllNote(email, resultView, requestCode);
    }

    public void loadMoreAllNote(String email, int pos, IResultView resultView, int requestCode) {
        mNormalModel.loadMoreAllNote(email, pos, resultView, requestCode);

    }

    public void refreshDiscuss(String noteId, IResultView resultView, int requestCode) {
        mNormalModel.refreshDiscuss(noteId, resultView, requestCode);
    }

    public void getNote(String email, String noteId, IResultView resultView, int requestCode) {
        mNormalModel.getNote(email, noteId, resultView, requestCode);
    }

    public void loadMoreDiscuss(String noteId, int position, IResultView resultView, int requestCode) {
        mNormalModel.loadMoreDiscuss(noteId, position, resultView, requestCode);
    }

    public void refreshNote(String email, String areaName, IResultView resultView, int requestCode, int dataCode) {
        if (dataCode == ConstVariable.CODE_ALL) {
            mNormalModel.refreshAll(email, areaName, resultView, requestCode);
        } else if (dataCode == ConstVariable.CODE_HOT) {
            mNormalModel.refreshHot(email, areaName, resultView, requestCode);
        } else {
            resultView.result(new Exception(""), null, requestCode);
        }
    }

    public void lodMoreNote(String email, String areaName, int position, IResultView resultView, int requestCode, int dataCode) {
        if (dataCode == ConstVariable.CODE_ALL) {
            mNormalModel.loadMoreAll(email, areaName, position, resultView, requestCode);
        } else if (dataCode == ConstVariable.CODE_HOT) {
            mNormalModel.loadMoreHot(email, areaName, position, resultView, requestCode);
        } else {
            resultView.result(new Exception(""), null, requestCode);
        }
    }

    public void getRandomNote(String email, IResultView resultView) {
        mNormalModel.getRandomNote(email, resultView);
    }

    public void getNote(String email, String id, IResultView resultView) {
        if (id != null) {
            mNormalModel.getNote(email, id, resultView);
        } else {
            resultView.result(new Exception(""), null, -1);
        }
    }

    public void getRecNote(String email, IResultView resultView, int requestCode) {
        mNormalModel.getRecNote(email, resultView, requestCode);
    }

    public void getNewestNote(String email, IResultView resultView, int requestCode) {
        mNormalModel.getNewestNote(email, resultView, requestCode);
    }

    public void getFollowNote(String email, IResultView resultView, int requestCode) {
        mNormalModel.getFollowNote(email, resultView, requestCode);
    }

    public void loadMoreRecNote(String email, int position, IResultView resultView, int requestCode) {
        mNormalModel.loadMoreRecNote(email, position, resultView, requestCode);
    }

    public void loadMoreNewestNote(String email, int position, IResultView resultView, int requestCode) {
        mNormalModel.loadMoreNewestNote(email, position, resultView, requestCode);
    }

    public void loadMoreFollowNote(String email, int pos, IResultView resultView, int requestCode) {
        mNormalModel.loadMoreFollowNote(email, pos, resultView, requestCode);
    }

    public void validateFollow(String email, String followEmail, IResultView resultView, int requestCode) {
        mNormalModel.validateFollow(email, followEmail, resultView, requestCode);
    }

    public void validateCollection(String email, String noteId, IResultView resultView, int requestCode) {
        mNormalModel.validateCollection(email, noteId, resultView, requestCode);
    }

    public void getMessageNumber(String email, IResultView resultView) {
        mNormalModel.getMessageNumber(email, resultView);
    }

    public void getMessageDiscuss(String email, IResultView resultView, int requestCode) {
        mNormalModel.getMessageDiscuss(email, resultView, requestCode);
    }

    public void loadMoreMessageDiscuss(String email, int pos, IResultView resultView, int requestCode) {
        mNormalModel.loadMoreMessageDiscuss(email, pos, resultView, requestCode);
    }

    public void getMessageAppreciate(String email, IResultView resultView, int requestCode) {
        mNormalModel.getMessageAppreciate(email, resultView, requestCode);
    }

    public void loadMoreMessageAppreciate(String email, int pos, IResultView resultView, int requestCode) {
        mNormalModel.loadMoreMessageAppreciate(email, pos, resultView, requestCode);
    }

    public void getMessageReply(String email, IResultView resultView, int requestCode) {
        mNormalModel.getMessageReply(email, resultView, requestCode);
    }

    public void loadMoreMessageReply(String email, int pos, IResultView resultView, int requestCode) {
        mNormalModel.loadMoreMessageReply(email, pos, resultView, requestCode);
    }

    public void getFans(String email, IResultView resultView, int requestCode) {
        mNormalModel.getFans(email, resultView, requestCode);
    }

    public void loadMoreFans(String email, int pos, IResultView resultView, int requestCode) {
        mNormalModel.loadMoreFans(email, pos, resultView, requestCode);
    }

    public void getUserInfo(String email, IResultView resultView, int requestCode) {
        mNormalModel.getUserInfo(email, resultView, requestCode);
    }

    public void getNumberCount(String email, IResultView resultView, int requestCode) {
        mNormalModel.getNumberCount(email, resultView, requestCode);
    }

    public void getFollow(String email, IResultView resultView, int requestCode) {
        mNormalModel.getFollow(email, resultView, requestCode);
    }

    public void loadMoreFollow(String email, int pos, IResultView resultView, int requestCode) {
        mNormalModel.loadMoreFollow(email, pos, resultView, requestCode);

    }

    public void getAppreciateNote(String email, IResultView resultView, int requestCode) {
        mNormalModel.getAppreciateNote(email, resultView, requestCode);
    }

    public void loadMoreAppreciateNote(String email, int pos, IResultView resultView, int requestCode) {
        mNormalModel.loadMoreAppreciateNote(email, pos, resultView, requestCode);
    }

    public void getRecordNote(String email, IResultView resultView, int requestCode) {
        mNormalModel.getRecordNote(email, resultView, requestCode);
    }

    public void loadMoreRecordNote(String email, int pos, IResultView resultView, int requestCode) {
        mNormalModel.loadMoreRecordNote(email, pos, resultView, requestCode);
    }

    public void getDiscuss(String email, int pos, IResultView resultView, int requestCode) {
        mNormalModel.getDiscuss(email, pos, resultView, requestCode);
    }

    public void getHistoryHead(String email, IResultView resultView, int requestCode) {
        mNormalModel.getHistoryHead(email, resultView, requestCode);
    }

    public void getCollectionNote(String email, int pos, IResultView resultView, int requestCode) {
        mNormalModel.getCollectionNote(email, pos, resultView, requestCode);
    }
}
