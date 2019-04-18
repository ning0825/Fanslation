package com.tanhuan.fanslation.mvp;

import com.tanhuan.fanslation.bean.AssocBean;
import com.tanhuan.fanslation.bean.ParaBean;

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
                .subscribe(new Consumer<AssocBean>() {
                    @Override
                    public void accept(AssocBean assocBean) throws Exception {
                        view.showResult(assocBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError(throwable);
                    }
                });
    }

    @Override
    public void detachView() {
        view = null;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
