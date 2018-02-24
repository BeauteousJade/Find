package com.example.pby.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.net.inter.impl.UserOperation;
import com.example.net.net.bean.User;
import com.example.pby.find.R;
import com.example.pby.find.activity.base.BaseActivity;
import com.example.pby.find.adapter.list.SettingAdapter;
import com.example.pby.find.bean.SettingBean;
import com.example.pby.find.constant.ConstVariable;
import com.example.pby.find.customview.PbyPopup;
import com.example.pby.find.decoration.LinearRecyclerViewDecoration;
import com.example.pby.find.mvp.presenter.LoadPresenter;
import com.example.pby.find.mvp.presenter.PromptDialogPresenter;
import com.example.pby.find.mvp.view.IPromptDialogView;
import com.example.pby.find.util.ApplicationUtil;
import com.example.pby.find.util.PopupUtil;
import com.example.pby.find.util.ScreenUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

import java.util.Arrays;
import java.util.List;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by pby on 2018/2/22.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, PbyPopup.OnMenuItemClickClickListener, IPromptDialogView {
    private ImageView mImageViewBack = null;
    private TextView mTextViewTitle = null;

    private RecyclerView mRecyclerView = null;
    private List<SettingBean> mDataList = null;
    private SettingAdapter mAdapter = null;

    private SmartRefreshLayout mRefreshLayout = null;
    private LoadPresenter mPresenter = null;
    private PromptDialogPresenter mPromptDialogPresenter = null;

    private PbyPopup mInputMenu = null;
    private PbyPopup mHeadMenu = null;
    private PbyPopup mDialogMenu = null;
    private PromptDialog mPromptDialog = null;

    private static final String STRING_CLEAR_CACHE = "清除缓存";
    private static final String STRING_CLEAR_LOGIN_RECORD = "清除登录记录";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setImmerseMode();
    }

    @Override
    public void init() {
        mPresenter = new LoadPresenter();
        mPromptDialogPresenter = new PromptDialogPresenter();

        mImageViewBack = findView(R.id.toolbar_back_ok_imageView_back);
        mTextViewTitle = findView(R.id.toolbar_back_ok_textView_title);

        mRecyclerView = findView(R.id.activity_setting_recyclerView);
        mDataList = Arrays.asList(new SettingBean("个人设置", "", SettingBean.TYPE_TITLE),
                new SettingBean("个人头像", "", SettingBean.TYPE_IMG), new SettingBean("个人昵称", "", SettingBean.TYPE_TEXT),
                new SettingBean("个性签名", "", SettingBean.TYPE_TEXT), new SettingBean("重置密码", "", SettingBean.TYPE_TEXT),
                new SettingBean("应用设置", "", SettingBean.TYPE_TITLE),
                new SettingBean("清空缓存", "", SettingBean.TYPE_TEXT), new SettingBean("清除登录记录", "", SettingBean.TYPE_TEXT),
                new SettingBean("自动登录", "", SettingBean.TYPE_SWITCH),
                new SettingBean("其他设置", "", SettingBean.TYPE_TITLE),
                new SettingBean("版本更新", "当前版本 " + ApplicationUtil.getAppVersionName(this), SettingBean.TYPE_TEXT)
        );
        mAdapter = new SettingAdapter(mDataList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new LinearRecyclerViewDecoration(this, R.drawable.decoration_gray_01, LinearRecyclerViewDecoration.VERTIACL));


        mRefreshLayout = findView(R.id.activity_setting_refreshLayout);
        mRefreshLayout.setEnableOverScrollBounce(true);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);


        mInputMenu = PopupUtil.buildMenu(R.layout.popup_input, this)
                .setText(R.id.popup_input_textView_title, "修改昵称")
                .setOnMenuItemClickClickListener(this)
                .addOnMenuItemClickClick(R.id.popup_input_textView_ok)
                .addOnMenuItemClickClick(R.id.popup_input_textView_no)
                .setWidth((int) ScreenUtil.getScreentWidth(this))
                .createPopup();

        mHeadMenu = PopupUtil.buildMenu(R.layout.popup_menu_head, this)
                .setOnMenuItemClickClickListener(this)
                .addOnMenuItemClickClick(R.id.popup_menu_head_textView_select)
                .addOnMenuItemClickClick(R.id.popup_menu_head_textView_camera)
                .addOnMenuItemClickClick(R.id.popup_menu_head_textView_history)
                .addOnMenuItemClickClick(R.id.popup_menu_head_textView_cancel)
                .setWidth((int) ScreenUtil.getScreentWidth(this))
                .createPopup();

        mDialogMenu = PopupUtil.buildDialog(R.layout.popup_dialog, this)
                .setOnMenuItemClickClickListener(this)
                .addOnMenuItemClickClick(R.id.popup_dialog_textView_cancel)
                .addOnMenuItemClickClick(R.id.popup_dialog_textView_ok)
                .setText(R.id.popup_dialog_textView_title, STRING_CLEAR_CACHE)
                .setText(R.id.popup_dialog_textView_cancel, "取消")
                .setText(R.id.popup_dialog_textView_ok, "确定")
                .setText(R.id.popup_dialog_textView_content, "清除缓存之后，会导致某些资源重新加载")
                .createPopup();

        mPromptDialog = new PromptDialog(this);

        mTextViewTitle.setText("设置");
        mImageViewBack.setOnClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e == null) {
            switch (requestCode) {
                case ConstVariable.CODE_USER: {
                    final List<User> list = (List<User>) data;
                    if (list != null && list.size() != 0) {
                        final User user = list.get(0);
                        mDataList.get(1).setContent(user.getHead());
                        mDataList.get(2).setContent(user.getName());
                        mDataList.get(3).setContent(user.getAutograph());
                        mAdapter.notifyDataSetChanged();
                    }
                    break;
                }
            }
        } else {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back_ok_imageView_back: {
                onBackPressed();
                break;
            }
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 1: {
                mHeadMenu.showAtAnchorView(mRefreshLayout, VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER);
                break;
            }
            case 2: { //昵称
                mInputMenu.setHintText(R.id.popup_input_editText, mDataList.get(position).getContent());
                mInputMenu.showAtAnchorView(mRefreshLayout, VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER);
                break;
            }
            case 3: { //签名
                Intent intent = new Intent(this, SetAutographActivity.class);
                startActivity(intent);
                break;
            }
            case 4: { //重置密码
                Intent intent = new Intent(this, ResetPasswordActivity.class);
                startActivity(intent);
                break;
            }
            case 6: { //清空缓存
                mDialogMenu.showAtAnchorView(mRefreshLayout, VerticalGravity.CENTER, HorizontalGravity.CENTER);
                break;
            }
            case 7: { //清除登录记录
                mDialogMenu.setText(R.id.popup_dialog_textView_title, STRING_CLEAR_LOGIN_RECORD)
                        .setText(R.id.popup_dialog_textView_content, "清除登录记录之后,应用会清除你的所有登录信息")
                        .showAtAnchorView(mRefreshLayout, VerticalGravity.CENTER, HorizontalGravity.CENTER);
            }
        }
    }

    @Override
    protected void onDestroy() {
        mInputMenu.dismiss();
        super.onDestroy();
    }

    @Override
    public void onMenuItemClickClick(PbyPopup pbyPopup, View view) {
        switch (view.getId()) {
            case R.id.popup_menu_head_textView_select: {
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())
                        .cropCompressQuality(1)
                        .enableCrop(true)
                        .compress(true)
                        .isCamera(false)
                        .withAspectRatio(1, 1)
                        .maxSelectNum(1)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            }
            case R.id.popup_menu_head_textView_camera: {
                PictureSelector.create(this)
                        .openCamera(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .enableCrop(true)
                        .withAspectRatio(1, 1)
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            }
            case R.id.popup_menu_head_textView_history: {
                Intent intent = new Intent(this, HistoryHeadActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.popup_input_textView_ok: {
                setName(2);
                break;
            }
            case R.id.popup_dialog_textView_ok: {
                if (mDialogMenu.getText(R.id.popup_dialog_textView_title).equals(STRING_CLEAR_CACHE)) {
                    ApplicationUtil.clearCache(this);
                } else if (mDialogMenu.getText(R.id.popup_dialog_textView_title).equals(STRING_CLEAR_LOGIN_RECORD)) {
                    ApplicationUtil.clearSharedPreferences(this);
                }
                break;
            }
        }
        pbyPopup.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        String path = null;
                        if (media.isCompressed()) {
                            path = media.getCompressPath();
                        } else if (media.isCut()) {
                            path = media.getCutPath();
                        } else {
                            path = media.getPath();
                        }
                        mPromptDialogPresenter.updateHead(UserOperation.getCurrentUser().getEmail(), path, this, this);
                        break;
                    }
                    break;
            }
        }
    }

    @Override
    public void changeStatus(boolean flag) {
        if (flag) {
            mPromptDialog.showLoading("");
        } else {
            mPromptDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getUserInfo(UserOperation.getCurrentUser().getEmail(), this, ConstVariable.CODE_USER);
    }

    public void setName(int selectPosition) {
        final String name = mInputMenu.getText(R.id.popup_input_editText);
        if (!name.equals("")) {
            final String email = UserOperation.getCurrentUser().getEmail();
            UserOperation operation = new UserOperation();
            operation.setName(email, name);
            mInputMenu.setText(R.id.popup_input_editText, "");
            mDataList.get(selectPosition).setContent(name);
            mAdapter.notifyItemChanged(selectPosition);
        }
    }
}
