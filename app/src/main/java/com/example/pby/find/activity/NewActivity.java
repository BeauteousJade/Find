package com.example.pby.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.net.inter.impl.UserOperation;
import com.example.net.net.bean.Discuss;
import com.example.pby.find.R;
import com.example.pby.find.activity.base.BaseActivity;
import com.example.pby.find.adapter.list.ImageAdapter;
import com.example.pby.find.bean.Operation;
import com.example.pby.find.bean.recyclerView.ImageBean;
import com.example.pby.find.customview.RichEditText;
import com.example.pby.find.mvp.presenter.PromptDialogPresenter;
import com.example.pby.find.mvp.view.IPromptDialogView;
import com.example.pby.find.util.ExpressionUtil;
import com.example.pby.find.util.IntUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by pby on 2018/2/7.
 */

public class NewActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemChildLongClickListener, BaseQuickAdapter.OnItemChildClickListener, IPromptDialogView {
    private ImageView mImageViewBack = null;
    private TextView mTextViewTitle = null;

    private RichEditText mEditTextContent = null;
    private ImageView mImageViewExpression = null;
    private ImageView mImageViewPicture = null;
    private Button mButtonOk = null;


    private RecyclerView mRecyclerViewExpression = null;
    private List<ImageBean> mExpressionList = null;
    private ImageAdapter mAdapterExpression = null;

    private RecyclerView mRecyclerViewImage = null;
    private List<ImageBean> mImageList = null;
    private ImageAdapter mAdapterImage = null;

    private PromptDialog mPromptDialog = null;
    private PromptDialogPresenter mPresenter = null;

    private List<String> pathList = null;
    private String mAreaName = null;
    private Operation mOperation = null;
    public static final String KEY_AREA_NAME = "areaName";
    public static final String KEY_OPERATION_REPLY = "reply";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        setImmerseMode();
    }

    @Override
    public void init() {
        mAreaName = getIntent().getStringExtra(KEY_AREA_NAME);
        if (mAreaName == null) {
            mOperation = getIntent().getParcelableExtra(KEY_OPERATION_REPLY);
        }
        mImageViewBack = findView(R.id.toolbar_back_ok_imageView_back);
        mTextViewTitle = findView(R.id.toolbar_back_ok_textView_title);
        mImageViewExpression = findView(R.id.activity_new_imageView_expression);
        mImageViewPicture = findView(R.id.activity_new_imageView_picture);

        mEditTextContent = findView(R.id.activity_new_richEditText_content);
        mButtonOk = findView(R.id.activity_new_button_ok);


        mRecyclerViewExpression = findView(R.id.activity_new_recyclerView_expression);
        mExpressionList = ExpressionUtil.getAllExpression(this);
        mAdapterExpression = new ImageAdapter(R.layout.item_recyclerview_image_expression, mExpressionList);
        mRecyclerViewExpression.setAdapter(mAdapterExpression);
        mRecyclerViewExpression.setLayoutManager(new GridLayoutManager(this, 7));

        mRecyclerViewImage = findView(R.id.activity_new_recyclerView_picture);
        mImageList = new ArrayList<>();
        mAdapterImage = new ImageAdapter(R.layout.item_recyclerview_image_image, mImageList);
        mRecyclerViewImage.setAdapter(mAdapterImage);
        mRecyclerViewImage.setLayoutManager(new GridLayoutManager(this, 3));

        mPromptDialog = new PromptDialog(this);
        mPresenter = new PromptDialogPresenter();
        if (mAreaName != null) {
            mTextViewTitle.setText("帖子");
        } else {
            mTextViewTitle.setText("回复 " + mOperation.getName());
        }
        mImageViewExpression.setOnClickListener(this);
        mImageViewBack.setOnClickListener(this);
        mImageViewPicture.setOnClickListener(this);
        mButtonOk.setOnClickListener(this);

        mAdapterImage.setOnItemChildLongClickListener(this);
        mAdapterExpression.setOnItemChildClickListener(this);
        pathList = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_new_imageView_expression: {
                if (mRecyclerViewExpression.getVisibility() == View.GONE) {
                    mRecyclerViewExpression.setVisibility(View.VISIBLE);
                    Glide.with(this).load(R.mipmap.icon_new_expression_01).into(mImageViewExpression);
                } else {
                    mRecyclerViewExpression.setVisibility(View.GONE);
                    Glide.with(this).load(R.mipmap.icon_new_expression_00).into(mImageViewExpression);
                }
                break;
            }
            case R.id.toolbar_back_ok_imageView_back: {
                onBackPressed();
                break;
            }
            case R.id.activity_new_imageView_picture: {
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())
                        .cropCompressQuality(10)
                        .enableCrop(true)
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            }
            case R.id.activity_new_button_ok: {
                doWork();
                break;
            }
        }
    }

    private void doWork() {
        if (mAreaName != null) {
            mPresenter.insertNote(UserOperation.getCurrentUser().getEmail(), mAreaName, mEditTextContent.getText().toString(), pathList, this, this);
        } else if (mOperation != null) {
            Discuss discuss = new Discuss();
            discuss.setContent(mEditTextContent.getText().toString());
            discuss.setIsChild(mOperation.isChild() ? 1 : 0);
            discuss.setEmail(UserOperation.getCurrentUser().getEmail());
            discuss.setImages(pathList);
            discuss.setNoteId(mOperation.getId());
            if (mOperation.isChild()) {
                discuss.setParentCOntent(mOperation.getParentContent());
                discuss.setParentEmail(mOperation.getParentEmail());
            }
            mPresenter.insertDiscuss(discuss, this, this);
        }
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
                            Log.i("pby123", "path = " + path);
                        } else if (media.isCut()) {
                            path = media.getCutPath();
                        } else {
                            path = media.getPath();
                        }
                        ImageBean bean = new ImageBean();
                        bean.setImagePath(path);
                        bean.setImageType(ImageBean.Type.image);
                        mAdapterImage.addData(bean);
                        pathList.add(path);
                    }
                    break;
            }
        }
    }


    @Override
    public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
        mAdapterImage.remove(position);
        return true;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        mEditTextContent.appendExpression(mExpressionList.get(position).getImagePath());
    }

    @Override
    public void result(Throwable e, Object data, int requestCode) {
        if (e == null) {
            int experience = 0;
            int level = 0;
            if (mAreaName == null) {
                experience = UserOperation.getCurrentUser().getExperience() + 5;
            }else{
                experience = UserOperation.getCurrentUser().getExperience() + 10;
            }
            level = IntUtil.buildLevel(experience);
            UserOperation.getCurrentUser().setLevel(level);
            UserOperation.getCurrentUser().setExperience(experience);
            Toasty.success(this, mAreaName == null ? "评论成功!经验+5" : "发布成功!经验+10", Toast.LENGTH_SHORT, false).show();

            onBackPressed();
        } else {
            Toasty.normal(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void changeStatus(boolean flag) {
        if (flag) {
            mPromptDialog.showLoading("发送中...");
        } else {
            mPromptDialog.dismiss();
        }
    }
}
