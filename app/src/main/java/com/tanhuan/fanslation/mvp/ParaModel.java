package com.tanhuan.fanslation.mvp;

import com.google.gson.Gson;
import com.tanhuan.fanslation.bean.AssocBean;
import com.tanhuan.fanslation.bean.ParaBean;
import com.tanhuan.fanslation.entity.ParaEntity;
import com.tanhuan.fanslation.util.HttpUtil;

import java.util.ArrayList;

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

    public void saveToBox(ParaBean paraBean, Callback callback) {
        String input = paraBean.getInput();

        String phone = paraBean.getEc().getWord().get(0).getUsphone();

        StringBuffer sbTrans = new StringBuffer();
        for (int i = 0; i < paraBean.getEc().getExam_type().size(); i++) {
            sbTrans.append(paraBean.getEc().getExam_type().get(i) + "/");
        }
        String examType = sbTrans.toString();

        //
        Gson gson = new Gson();
        StringBuffer trans = new StringBuffer();
        //show translations
        for (int i = 0; i < paraBean.getEc().getWord().get(0).getTrs().size(); i++) {
            trans.append(paraBean.getEc().getWord().get(0).getTrs().get(i).getTr().get(0).getL().getI().get(0));
        }
        String sentences;
    }
}
