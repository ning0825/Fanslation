package com.tanhuan.fanslation.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ParaEntity {
    @Id long id;

    String input;

    String phone;

    String examType;

    String trans;

    String sentences;
}
