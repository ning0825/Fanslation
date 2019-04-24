package com.tanhuan.fanslation.entity;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class DataEntity {
    @Id public long Id;

    String date;
    int searchNum;
    int reciteNum;
    int easyNum;

    public DataEntity(String date) {
        this.date = date;
        this.searchNum = 0;
        this.reciteNum = 0;
        this.reciteNum = 0;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSearchNum() {
        return searchNum;
    }

    public void setSearchNum(int searchNum) {
        this.searchNum = searchNum;
    }

    public int getReciteNum() {
        return reciteNum;
    }

    public void setReciteNum(int reciteNum) {
        this.reciteNum = reciteNum;
    }

    public int getEasyNum() {
        return easyNum;
    }

    public void setEasyNum(int easyNum) {
        this.easyNum = easyNum;
    }
}
