package com.example.pby.find.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.net.inter.impl.UserOperation;
import com.example.pby.find.R;
import com.example.pby.find.activity.MessageActivity;
import com.example.pby.find.activity.MainActivity;
import com.example.pby.find.activity.MessageFollowActivity;
import com.example.pby.find.adapter.list.MessageIconAdapter;
import com.example.pby.find.bean.recyclerView.MessageBean;
import com.example.pby.find.fragment.base.BaseFragment;
import com.example.pby.find.manager.AreaGridLayoutManager;
import com.example.pby.find.mvp.presenter.LoadPresenter;
import com.example.pby.find.mvp.view.IResultView;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Arrays;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by pby on 2018/2/12.
 */

public class MessageFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener, IResultView {
    private TextView mTextViewTitle = null;
    private RecyclerView mRecyclerViewMessage = null;
    private List<MessageBean> mMessageList = null;
    private MessageIconAdapter mMessageAdapter = null;

    private SmartRefreshLayout mRefreshLayout = null;

    private LoadPresenter mPresenter = null;

    private boolean isCreate = true;

    public MessageFragment() {
        super(R.layout.fragment_message);
    }

    @Override
    public void init(View view) {
        mTextViewTitle = view.findViewById(R.id.toolbar_noBack_search_textView_title);

        mRecyclerViewMessage = view.findViewById(R.id.fragment_message_recyclerView_message);
        mRefreshLayout = view.findViewById(R.id.fragment_message_refreshLayout);

        mMessageList = Arrays.asList(new MessageBean(R.mipmap.icon_message_discuss, "评论", 0)
                , new MessageBean(R.mipmap.icon_message_like, "喜欢", 0)
                , new MessageBean(R.mipmap.icon_message_follow, "关注", 0)
                , new MessageBean(R.mipmap.icon_message_reply, "回复", 0));
        mMessageAdapter = new MessageIconAdapter(R.layout.item_recyclerview_message, mMessageList);
        mRecyclerViewMessage.setAdapter(mMessageAdapter);
        AreaGridLayoutManager layoutManager = new AreaGridLayoutManager(getActivity(), 3);
        layoutManager.setScrollEnabled(false);
        mRecyclerViewMessage.setLayoutManager(layoutManager);

        mRefreshLayout.setRefreshHeader(new WaterDropHeader(getActivity()));
        mRefreshLayout.setEnableOverScrollBounce(true);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> refresh());
        mPresenter = new LoadPresenter();
        mMessageAdapter.setOnItemChildClickListener(this);

        mTextViewTitle.setText("消息");

        isCreate = true;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        MainActivity activity = (MainActivity) getActivity();
        activity.showBadge(-mMessageList.get(position).getCount());
        mMessageList.get(position).setCount(0);
        mMessageAdapter.notifyDataSetChanged();
        switch (position) {
            case 0: {
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                intent.putExtra(MessageActivity.NAME_OPERATION, MessageActivity.KEY_DISCUSS);
                startActivity(intent);
                break;
            }
            case 1: {
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                intent.putExtra(MessageActivity.NAME_OPERATION, MessageActivity.KEY_APPRECIATE);
                startActivity(intent);
                break;
            }
            case 2: {
                Intent intent = new Intent(getActivity(), MessageFollowActivity.class);
                startActivity(intent);
                break;
            }
            case 3: {
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                intent.putExtra(MessageActivity.NAME_OPERATION, MessageActivity.KEY_REPLY);
                startActivity(intent);
                break;
            }
        }
    }

    public void refresh() {
        mPresenter.getMessageNumber(UserOperation.getCurrentUser().getEmail(), this);
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e == null) {
            List<Integer> list = (List<Integer>) data;
            int count = 0;
            for (int i = 0; i < list.size(); i++) {
                mMessageList.get(i).setCount(list.get(i));
                count += list.get(i);
            }
            mMessageAdapter.notifyDataSetChanged();
            MainActivity activity = (MainActivity) getActivity();
            activity.resetBadge(count);
        } else {
            Toasty.normal(getActivity(), "加载失败").show();
        }
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isCreate) {
            mRefreshLayout.autoRefresh();
            isCreate = false;
        }
    }
}
