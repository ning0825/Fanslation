package com.tanhuan.fanslation.util;

public class Constants {
    //联想url
    static final String ASSOCIATION_URL = "http://dict.youdao.com/suggest?doctype=json&q=";
    //释义url
    static final String PARAPHRASE_URL = "http://dict.youdao.com/jsonapi?jsonversion=2&client=mobile&dicts=%7B%22count%22%3A99%2C%22dicts%22%3A%5B%5B%22collins%22%5D%2C%5B%22ec%22%5D%2C%5B%22blng_sents_part%22%5D%5D%7D&q=";
    //每日图片url (格式：2019-02-19）
    static final String IMAGE_URL = "http://open.iciba.com/dsapi/?date=";

    //sharedPreferenceName
    public static final String SP_NAME = "spName";
    public static final String SP_DEFAULT_BOOK_ID_KEY = "defaultBook";
}
