package com.tanhuan.fanslation.entity;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.Unique;
import io.objectbox.relation.ToMany;

@Entity
public class UserEntity {

    @Id public long Id;

    @Index
    @Unique
    private String userName;

    private String phone;

    private String objectId;

    @Backlink
    public ToMany<BookEntity> toManyBookEntities;

    public UserEntity() {}

    public UserEntity(String userName, String phone) {
        this.userName = userName;
        this.phone = phone;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
