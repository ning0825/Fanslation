package com.tanhuan.fanslation.mvp;

import com.google.gson.Gson;
import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.bean.ParaBean;
import com.tanhuan.fanslation.entity.ParaEntity;
import com.tanhuan.fanslation.util.HttpUtil;

import io.objectbox.Box;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ParaModel implements IModel<ParaBean> {
    Gson gson = new Gson();
    Box<ParaEntity> paraBox = BaseApp.getBoxStore().boxFor(ParaEntity.class);
    @Override
    public Observable<ParaBean> getFromServer(final String s) {
        return Observable.create((ObservableOnSubscribe<ParaBean>) emitter -> emitter.onNext(HttpUtil.getPara(s)))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void saveToBox(ParaBean paraBean, Callback callback) {
        String input = paraBean.getInput();
        String phone = paraBean.getEc().getWord().get(0).getUsphone();
        String examType = gson.toJson(paraBean.getEc().getExam_type());
        String trans = gson.toJson(paraBean.getEc().getWord().get(0).getTrs());
        String sentences = gson.toJson(paraBean.getBlng_sents_part().getSentencepair());

        ParaEntity paraEntity = new ParaEntity(input, phone, examType, trans, sentences);
        paraBox.put(paraEntity);
        callback.onResult(0);
    }
}
