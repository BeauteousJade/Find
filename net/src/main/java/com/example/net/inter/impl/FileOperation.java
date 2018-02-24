package com.example.net.inter.impl;

import com.example.net.inter.base.BaseOperation;
import com.example.net.inter.util.OperationUtil;
import com.example.net.net.api.FileApi;
import com.example.net.net.api.UserApi;
import com.example.net.net.bean.result.ResultBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import io.reactivex.Observable;

/**
 * Created by pby on 2018/2/8.
 */

public class FileOperation extends BaseOperation {
    public void upload(List<String> pathList, String email) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        List<MultipartBody.Part> partLit = new ArrayList<>();
        for (String path : pathList) {
            File file = new File(path);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("file", file.getName(), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        FileApi fileApi = OperationUtil.generateApi(FileApi.class);
        Call<ResultBean> call = fileApi.upLoad(multipartBody, email);
        execute(call);
    }

    public Observable uploadObservable(List<String> pathList, String email) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (String path : pathList) {
            File file = new File(path);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("file", file.getName(), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        FileApi fileApi = OperationUtil.generateApi(FileApi.class);
        Call<ResultBean> call = fileApi.upLoad(multipartBody, email);

        return executeObservable(call);
    }

    public void delete(List<String> urlList) {
        FileApi fileApi = OperationUtil.generateApi(FileApi.class);
        Call<ResultBean> call = fileApi.delete(urlList);
        execute(call);
    }

    public Observable deleteObservable(List<String> urlList) {
        FileApi fileApi = OperationUtil.generateApi(FileApi.class);
        Call<ResultBean> call = fileApi.delete(urlList);
        return executeObservable(call);
    }
}
