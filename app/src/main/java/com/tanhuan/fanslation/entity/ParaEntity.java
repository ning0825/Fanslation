package com.tanhuan.fanslation.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ParaEntity {
    @Id long id;

    //查询单词
    String input;
    //音标
    String phone;
    //考试类型
    String examType;
    //翻译
    String trans;
    //例句
    String sentences;

    public ParaEntity(String input, String phone, String examType, String trans, String sentences) {
        this.input = input;
        this.phone = phone;
        this.examType = examType;
        this.trans = trans;
        this.sentences = sentences;
    }
}
