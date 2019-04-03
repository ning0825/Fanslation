package com.tanhuan.fanslation.mvp;

import io.reactivex.disposables.Disposable;

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
                .subscribe(imageBean -> view.showResult(imageBean));
    }

    @Override
    public void detachView() {
        view = null;
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
