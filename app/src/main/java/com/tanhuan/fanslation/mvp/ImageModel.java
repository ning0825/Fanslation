package com.tanhuan.fanslation.mvp;

import com.tanhuan.fanslation.bean.ImageBean;
import com.tanhuan.fanslation.util.HttpUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ImageModel implements IModel<ImageBean> {
    @Override
    public Observable<ImageBean> getFromServer(String s) {
        return Observable.create((ObservableOnSubscribe<ImageBean>)emitter -> {
            if (HttpUtil.getPara(s) != null) {
                emitter.onNext(HttpUtil.getImage(s));
            } else {
                emitter.onError(new Throwable("response is null"));
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public ImageBean getFromBox(String s) {
        return null;
    }
}
