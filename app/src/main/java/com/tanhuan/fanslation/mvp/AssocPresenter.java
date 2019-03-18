package com.tanhuan.fanslation.mvp;

import com.tanhuan.fanslation.bean.AssocBean;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class AssocPresenter implements IPresenter {
    private AssocModel assocModel;
    private IView view;
    private Disposable disposable;

    public AssocPresenter(IView v) {
        view = v;
        assocModel = new AssocModel();
    }

    @Override
    public void request(String s) {
        disposable = assocModel.getFromServer(s)
                .subscribe(assocBean -> view.showResult(assocBean));
    }

    @Override
    public void detachView() {
        view = null;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
