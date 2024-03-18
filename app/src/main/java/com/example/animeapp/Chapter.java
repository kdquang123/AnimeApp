package com.example.animeapp;

import java.util.Date;

public class Chapter {
    private  int Id;
    private String ChapterName;
    private Date CreateAt;
    private int IdStory;

    public Chapter() {
    }

    public Chapter(int id, String chapterName, Date createAt, int idStory) {
        Id = id;
        ChapterName = chapterName;
        CreateAt = createAt;
        IdStory = idStory;
    }

    public int getId() {
        return Id;
    }

    public String getChapterName() {
        return ChapterName;
    }

    public Date getCreateAt() {
        return CreateAt;
    }

    public int getIdStory() {
        return IdStory;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setChapterName(String chapterName) {
        ChapterName = chapterName;
    }

    public void setCreateAt(Date createAt) {
        CreateAt = createAt;
    }

    public void setIdStory(int idStory) {
        IdStory = idStory;
    }
}
