package com.tanhuan.fanslation.mvp;

import com.tanhuan.fanslation.bean.AssocBean;

public interface Callback<T> {
    void onResult(T t);
}
