package com.tanhuan.fanslation.mvp;

import com.tanhuan.fanslation.bean.ParaBean;

import io.reactivex.disposables.Disposable;

public class ParaPresenter implements IPresenter {
    private ParaModel paraModel;
    private IView<ParaBean> view;
    private Disposable disposable;

    public ParaPresenter(IView<ParaBean> view) {
        this.view = view;
        paraModel = new ParaModel();
    }

    @Override
    public void request(String s) {
        disposable = paraModel.getFromServer(s)
                .subscribe((paraBean) ->
                    view.showResult(paraBean)
                );
    }

    @Override
    public void detachView() {
        view = null;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
