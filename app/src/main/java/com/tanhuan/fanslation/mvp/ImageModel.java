package com.tanhuan.fanslation.mvp;

import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.bean.ImageBean;
import com.tanhuan.fanslation.entity.ImageEntity;
import com.tanhuan.fanslation.util.HttpUtil;

import io.objectbox.Box;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ImageModel implements IModel<ImageBean> {
    Box<ImageEntity> imageBox;

    public ImageModel() {
        imageBox = BaseApp.getBoxStore().boxFor(ImageEntity.class);
    }

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

    public void saveToBox(ImageBean imageBean) {
        // 音频 mp3 地址
        String tts = imageBean.getTts();
        // 英文内容
        String content = imageBean.getContent();
        //翻译
        String note = imageBean.getNote();
        //小图
        String picture = imageBean.getPicture();
        //大图
        String picture2 = imageBean.getPicture2();
        //日期
        String dateline = imageBean.getDateline();
        //分享图片
        String fenxiangImg = imageBean.getFenxiang_img();

        ImageEntity imageEntity = new ImageEntity(tts, content, note, picture, picture2, dateline, fenxiangImg);
        imageBox.put(imageEntity);
    }
}
