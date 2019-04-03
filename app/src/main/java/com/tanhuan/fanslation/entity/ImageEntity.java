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
    String picture1;
    //日期
    String dateline;
    //分享图片
    String fenxiangImg;
}
