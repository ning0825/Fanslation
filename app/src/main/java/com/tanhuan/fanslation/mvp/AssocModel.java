package com.tanhuan.fanslation.mvp;

import com.tanhuan.fanslation.bean.AssocBean;
import com.tanhuan.fanslation.util.HttpUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AssocModel implements IModel<AssocBean> {
    @Override
    public Observable<AssocBean> getFromServer(final String s) {
        return Observable.create((ObservableOnSubscribe<AssocBean>) emitter -> emitter.onNext(HttpUtil.getAssoc(s)))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
