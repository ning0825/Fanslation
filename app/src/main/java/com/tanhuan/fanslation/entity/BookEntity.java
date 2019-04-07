package com.tanhuan.fanslation.entity;

import java.io.Serializable;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.Unique;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

@Entity
public class BookEntity {
    @Id
    public long Id;

    @Index
    @Unique
    public String bookName;

    public ToOne<UserEntity> toOneUserEntity;

    @Backlink
    public ToMany<ParaEntity> toManyTransEntities;

    public BookEntity() {}

    public BookEntity(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
