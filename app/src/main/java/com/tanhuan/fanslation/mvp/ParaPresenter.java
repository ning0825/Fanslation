package com.tanhuan.fanslation.mvp;

import android.content.Context;
import android.widget.Toast;

import com.tanhuan.fanslation.bean.ParaBean;
import com.tanhuan.fanslation.entity.BookEntity;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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
                .subscribe(new Consumer<ParaBean>() {
                    @Override
                    public void accept(ParaBean paraBean) throws Exception {
                        view.showResult(paraBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError(throwable);
                    }
                });
    }

    public void save(ParaBean paraBean, long whichBook, Context context) {
        paraModel.saveToBox(paraBean, whichBook, new Callback() {
            @Override
            public void onResult(Object o) {
                Toast.makeText(context, "saved", Toast.LENGTH_SHORT).show();
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
