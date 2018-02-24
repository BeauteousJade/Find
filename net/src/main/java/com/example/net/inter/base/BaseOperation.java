package com.example.net.inter.base;


import android.util.Log;

import com.example.net.net.bean.result.ResultBean;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by apple on 2018/1/21.
 */

public class BaseOperation {
    public void execute(Call<ResultBean> call) {
        Observable.create((ObservableOnSubscribe<Response<ResultBean>>) emitter -> {
            Response<ResultBean> resultBeanResponse = call.execute();
            emitter.onNext(resultBeanResponse);
            emitter.onComplete();
        }).subscribeOn(Schedulers.newThread()).subscribe(resultBeanResponse -> Log.i("pby123", "123 " + resultBeanResponse.body()));
    }

    public Observable executeObservable(Call<ResultBean> call) {
        return Observable.create((ObservableOnSubscribe<Response<ResultBean>>) emitter -> {
            Response<ResultBean> resultBeanResponse = call.execute();
            emitter.onNext(resultBeanResponse);
            emitter.onComplete();
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public void executeObservable(Observable observable) {
        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultBeanResponse -> Log.i("pby123", "" + ((Response<ResultBean>) resultBeanResponse).body()));
    }
}
