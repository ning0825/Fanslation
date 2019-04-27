package com.tanhuan.fanslation.util;

public class Constants {
    //Association url
    static final String ASSOCIATION_URL = "https://dict.youdao.com/suggest?doctype=json&q=";
    //Paraphrase url
    static final String PARAPHRASE_URL = "https://dict.youdao.com/jsonapi?jsonversion=2&client=mobile&dicts=%7B%22count%22%3A99%2C%22dicts%22%3A%5B%5B%22collins%22%5D%2C%5B%22ec%22%5D%2C%5B%22blng_sents_part%22%5D%5D%7D&q=";
    //Image Everyday url (format：2019-02-19）
    static final String IMAGE_URL = "https://open.iciba.com/dsapi/?date=";
    //Iciba API
    static final String ICIBA_API = "http://dict-co.iciba.com/api/dictionary.php?key=C3B416E6684265E0DB4B2DD2F8E16E13&type=json&w=";

    //sharedPreferenceName
    public static final String SP_NAME = "spName";
    public static final String SP_DEFAULT_USER_ID_KEY = "defaultUserId";
    public static final String SP_CURRENT_USER_ID_KEY = "currentUserId";
    public static final String SP_DEFAULT_BOOK_ID_KEY = "defaultBook";
    public static final String SP_CURRENT_BOOK_ID_KEY = "currentBook";
    public static final String SP_USER_CLOUD_KEY = "userCloudId";

    //unsplash Key
//    public static final String UNSPLASH_ACCESS_KEY = "0166838389a0ca6b4a2c57bcbb56ecf0b8dfe38fe09e929798c74262cef014de";
//    public static final String UNSPLASH_SECRET_KEY = "6b1c3f976ca06bcc4ebb4ed45e014b586e101f28dc595d442301698fb552166b";
}
