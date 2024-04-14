package com.example.animeapp.Model;

import androidx.annotation.NonNull;

import java.util.Date;

public class Chapter {
    private  int id;
    private String chapterName;
    private String createAt;
    private int idStory;

    public Chapter() {
    }

    public Chapter(int id, String chapterName, String createAt, int idStory) {
        this.id = id;
        this.chapterName = chapterName;
        this.createAt = createAt;
        this.idStory = idStory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public int getIdStory() {
        return idStory;
    }

    public void setIdStory(int idStory) {
        this.idStory = idStory;
    }

    @NonNull
    @Override
    public String toString() {
        return this.chapterName;
    }
}
