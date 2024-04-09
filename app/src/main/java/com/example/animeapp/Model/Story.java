package com.example.animeapp.Model;

public class Story {
    private int id;
    private String name;
    private String coverImage;
    private String author;
    private String summary;
    private String category;
    private int numOfChapter;

    public Story() {
    }

    public Story(int id, String name, String coverImage, String author, String summary, String category, int numOfChapter) {
        this.id = id;
        this.name = name;
        this.coverImage = coverImage;
        this.author = author;
        this.summary = summary;
        this.category = category;
        this.numOfChapter = numOfChapter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumOfChapter() {
        return numOfChapter;
    }

    public void setNumOfChapter(int numOfChapter) {
        this.numOfChapter = numOfChapter;
    }
}
