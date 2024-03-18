package com.example.animeapp;

public class Story {
    private int Id;
    private String Name;
    private String CoverImage;
    private String Author;
    private String Summary;
    private int IdCategory;

    public Story() {
    }

    public Story(int id, String name, String coverImage, String author, String summary, int idCategory) {
        Id = id;
        Name = name;
        CoverImage = coverImage;
        Author = author;
        Summary = summary;
        IdCategory = idCategory;
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

    public int getIdCategory() {
        return IdCategory;
    }

    public void setIdCategory(int idCategory) {
        IdCategory = idCategory;
    }
}
