package com.example.animeapp.Model;

public class Story {
    private int Id;
    private String Name;
    private String CoverImage;
    private String Author;
    private String Summary;
    private String Category;
    private int numOfChapter;

    public Story() {
    }

    public Story(int id, String name, String coverImage, String author, String summary, String category, int numOfChapter) {
        Id = id;
        Name = name;
        CoverImage = coverImage;
        Author = author;
        Summary = summary;
        Category = category;
        this.numOfChapter = numOfChapter;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCoverImage() {
        return CoverImage;
    }

    public void setCoverImage(String coverImage) {
        CoverImage = coverImage;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }
    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getNumOfChapter() {
        return numOfChapter;
    }

    public void setNumOfChapter(int numOfChapter) {
        this.numOfChapter = numOfChapter;
    }
}
