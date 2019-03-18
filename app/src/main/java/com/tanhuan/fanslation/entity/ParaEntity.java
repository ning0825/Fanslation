package com.tanhuan.fanslation.entity;

import android.os.Build;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ParaEntity {
    @Id long id;

    String input;


}
