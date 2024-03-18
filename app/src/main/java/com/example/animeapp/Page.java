package com.example.animeapp;

public class Page {
    private int Id;
    private String Image;
    private int PageNumber;
    private int IdChap;

    public Page() {
    }

    public Page(int id, String image, int pageNumber, int idChap) {
        Id = id;
        Image = image;
        PageNumber = pageNumber;
        IdChap = idChap;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(int pageNumber) {
        PageNumber = pageNumber;
    }

    public int getIdChap() {
        return IdChap;
    }

    public void setIdChap(int idChap) {
        IdChap = idChap;
    }
}
