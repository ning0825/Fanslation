package com.tanhuan.fanslation.mvp;

import com.tanhuan.fanslation.bean.AssocBean;

public interface IView<T> {
    void showResult(T t);

    void showError(Throwable throwable);
}
