package com.tanhuan.fanslation.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.tanhuan.fanslation.bean.AssocBean;
import com.tanhuan.fanslation.bean.ImageBean;
import com.tanhuan.fanslation.bean.ParaBean;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {
    private static final String TAG = "----HttpUtil----";

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Gson gson = new Gson();

    /*
    * 获取联想*/
    public static AssocBean getAssoc(String s) {
        final String url = Constants.ASSOCIATION_URL + s;
        String responseString = okRun(url);
        AssocBean assocBean = gson.fromJson(responseString, AssocBean.class);
        return assocBean;
    }

    /*
    * 获取释义*/
    public static ParaBean getPara(String s) {
        final String url = Constants.PARAPHRASE_URL + s;
        String responseString = okRun(url);
        ParaBean paraBean = gson.fromJson(responseString, ParaBean.class);
        return paraBean;
    }

    /*
    * 每日一句*/
    public static ImageBean getImage(String data) {
        final String url = Constants.IMAGE_URL + data;
        String responseString = okRun(url);
        ImageBean imageBean = gson.fromJson(responseString, ImageBean.class);
        return imageBean;
    }

    /*
    请求总方法
     */
    private static String okRun(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Log.e(TAG, url );
        String responseString = null;
        try {
            Response response = okHttpClient.newCall(request).execute();
            responseString = response.body().string();
            Log.e(TAG, responseString );
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return responseString;
    }
}
