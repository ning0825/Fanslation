package com.tanhuan.fanslation.mvp;

import com.tanhuan.fanslation.bean.AssocBean;
import com.tanhuan.fanslation.bean.ParaBean;
import com.tanhuan.fanslation.util.HttpUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ParaModel implements IModel<ParaBean> {
    @Override
    public Observable<ParaBean> getFromServer(final String s) {
        return Observable.create((ObservableOnSubscribe<ParaBean>) emitter -> emitter.onNext(HttpUtil.getPara(s)))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
