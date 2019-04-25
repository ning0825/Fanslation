package com.tanhuan.fanslation.mvp;

import com.tanhuan.fanslation.bean.AssocBean;
import com.tanhuan.fanslation.bean.ImageBean;
import com.tanhuan.fanslation.bean.ParaBean;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ImagePresenter implements IPresenter {
    ImageModel imageModel;
    IView view;
    Disposable disposable;

    public ImagePresenter(IView v) {
        this.view = v;
        imageModel = new ImageModel();
    }

    @Override
    public void request(String s) {
        disposable = imageModel.getFromServer(s)
                .subscribe(new Consumer<ImageBean>() {
                    @Override
                    public void accept(ImageBean imageBean) throws Exception {
                        view.showResult(imageBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError(throwable);
                    }
                });
    }

    public void save(ImageBean imageBean) {
        imageModel.saveToBox(imageBean);
    }

    @Override
    public void detachView() {
        view = null;
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
