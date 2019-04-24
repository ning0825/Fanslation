package com.tanhuan.fanslation.mvp;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.bean.ParaBean;
import com.tanhuan.fanslation.entity.BookEntity;
import com.tanhuan.fanslation.entity.DataEntity;
import com.tanhuan.fanslation.entity.DataEntity_;
import com.tanhuan.fanslation.entity.ParaEntity;
import com.tanhuan.fanslation.util.HttpUtil;
import com.tanhuan.fanslation.util.ViewUtil;

import java.util.Date;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ParaModel implements IModel<ParaBean> {
    Gson gson = new Gson();
    BoxStore store = BaseApp.getBoxStore();
    Box<BookEntity> bookBox = store.boxFor(BookEntity.class);
    Box<DataEntity> dataBox = store.boxFor(DataEntity.class);
    private static final String TAG = "ParaModel";

    @Override
    public Observable<ParaBean> getFromServer(final String s) {
        return Observable.create((ObservableOnSubscribe<ParaBean>) emitter -> {
            if (HttpUtil.getPara(s) != null) {
                emitter.onNext(HttpUtil.getPara(s));
            } else {
                emitter.onError(new Throwable("response is null"));
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    void saveToBox(ParaBean paraBean, long whichBook, Callback callback) {
        String input = paraBean.getInput();

        String phone = paraBean.getEc().getWord().get(0).getUsphone();

        String examType;
        StringBuffer sbExam = new StringBuffer();
        if (paraBean.getEc().getExam_type() != null) {
            for (String s : paraBean.getEc().getExam_type()) {
                sbExam.append(s);
                sbExam.append("$");
            }
        }
        examType = sbExam.toString();

        String trans;
        StringBuffer sbTrans = new StringBuffer();
        if (paraBean.getEc().getWord().get(0).getTrs() != null) {
            for (ParaBean.EcBean.WordBean.TrsBean s : paraBean.getEc().getWord().get(0).getTrs()) {
                sbTrans.append(s.getTr().get(0).getL().getI().get(0));
                sbTrans.append("\n");
            }
        }
        trans = sbTrans.toString();

        String sentences;
        String senEn;
        String senCn;
        StringBuffer sbEn = new StringBuffer();
        StringBuffer sbCn = new StringBuffer();
        if (paraBean.getBlng_sents_part().getSentencepair() != null) {
            for (ParaBean.BlngSentsPartBean.SentencepairBean s : paraBean.getBlng_sents_part().getSentencepair()) {
                sbEn.append(s.getSentence());
                sbEn.append("$");
                sbCn.append(s.getSentencetranslation());
                sbCn.append("$");
            }
        }
        senEn = sbEn.toString();
        senCn = sbCn.toString();
        sentences = senEn + "*" + senCn;

        ParaEntity paraEntity = new ParaEntity(input, phone, examType,trans, sentences);
        BookEntity bookEntity = bookBox.get(whichBook);
        bookEntity.toManyTransEntities.add(paraEntity);
        bookBox.put(bookEntity);

        List<DataEntity> datas = dataBox.query().equal(DataEntity_.date, ViewUtil.getDate()).build().find();
        if (datas.size() == 0) {
            DataEntity dataEntity = new DataEntity(ViewUtil.getDate());
            dataEntity.setSearchNum(1);
            dataBox.put(dataEntity);
        } else {
            DataEntity dataEntity = datas.get(0);
            dataEntity.setSearchNum(dataEntity.getSearchNum() + 1);
            dataBox.put(dataEntity);
        }
        callback.onResult(0);
    }
}
