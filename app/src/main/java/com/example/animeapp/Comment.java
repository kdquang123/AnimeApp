package com.example.animeapp;

import java.util.Date;

public class Comment {
    private int Id;
    private String Content;
    private Date CreateAt;
    private String UserName;
    private int SatingValue;
    private int IdStory;

    public Comment() {
    }

    public Comment(int id, String content, Date createAt, String userName, int satingValue, int idStory) {
        Id = id;
        Content = content;
        CreateAt = createAt;
        UserName = userName;
        SatingValue = satingValue;
        IdStory = idStory;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(Date createAt) {
        CreateAt = createAt;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getSatingValue() {
        return SatingValue;
    }

    public void setSatingValue(int satingValue) {
        SatingValue = satingValue;
    }

    public int getIdStory() {
        return IdStory;
    }

    public void setIdStory(int idStory) {
        IdStory = idStory;
    }
}
