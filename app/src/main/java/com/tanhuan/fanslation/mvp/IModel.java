package com.tanhuan.fanslation.mvp;

import io.reactivex.Observable;

public interface IModel<T> {
    Observable<T> getFromServer(String s);
}
