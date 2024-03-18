package com.example.animeapp.Model;

import java.util.Date;

public class Comment {
    private int Id;
    private String Content;
    private Date CreateAt;
    private String UserName;
    private int RatingValue;
    private int IdStory;

    public Comment() {
    }

    public Comment(int id, String content, Date createAt, String userName, int ratingValue, int idStory) {
        Id = id;
        Content = content;
        CreateAt = createAt;
        UserName = userName;
        RatingValue = ratingValue;
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

    public int getRatingValue() {
        return RatingValue;
    }

    public void setRatingValue(int ratingValue) {
        RatingValue = ratingValue;
    }

    public int getIdStory() {
        return IdStory;
    }

    public void setIdStory(int idStory) {
        IdStory = idStory;
    }
}
