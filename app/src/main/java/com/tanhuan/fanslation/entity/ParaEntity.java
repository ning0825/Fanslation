package com.tanhuan.fanslation.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

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
    //点击 “记住” 的次数
    int remeberCount;

    public ToOne<BookEntity> toOneBookEntity;

    public ParaEntity() {}

    public ParaEntity(String input, String phone, String examType, String trans, String sentences, int count) {
        this.input = input;
        this.phone = phone;
        this.examType = examType;
        this.trans = trans;
        this.sentences = sentences;
        this.remeberCount = count;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public String getSentences() {
        return sentences;
    }

    public void setSentences(String sentences) {
        this.sentences = sentences;
    }

    public int getRemeberCount() {
        return remeberCount;
    }

    public void setRemeberCount(int remeberCount) {
        this.remeberCount = remeberCount;
    }
}
