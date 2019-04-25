package com.tanhuan.fanslation.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ImageEntity {
    @Id long id;

    // 音频 mp3 地址
    String tts;
    // 英文内容
    String content;
    //翻译
    String note;
    //小图
    String picture;
    //大图
    String picture2;
    //日期
    String dateline;
    //分享图片
    String fenxiangImg;

    public ImageEntity(String tts, String content, String note, String picture, String picture2, String dateline, String fenxiangImg) {
        this.tts = tts;
        this.content = content;
        this.note = note;
        this.picture = picture;
        this.picture2 = picture2;
        this.dateline = dateline;
        this.fenxiangImg = fenxiangImg;
    }

    public String getTts() {
        return tts;
    }

    public void setTts(String tts) {
        this.tts = tts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture1(String picture1) {
        this.picture2 = picture1;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getFenxiangImg() {
        return fenxiangImg;
    }

    public void setFenxiangImg(String fenxiangImg) {
        this.fenxiangImg = fenxiangImg;
    }
}
