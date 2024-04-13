package com.example.animeapp.Model;

import java.util.Date;

public class Comment {
    private int id;
    private String content;
    private String createAt;
    private String userName;
    private int idStory;

    public Comment() {
    }

    public Comment(int id, String content, String createAt, String userName, int idStory) {
        this.id = id;
        this.content = content;
        this.createAt = createAt;
        this.userName = userName;
        this.idStory = idStory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        content = content;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        createAt = createAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        userName = userName;
    }

    public int getIdStory() {
        return idStory;
    }

    public void setIdStory(int idStory) {
        idStory = idStory;
    }
}
