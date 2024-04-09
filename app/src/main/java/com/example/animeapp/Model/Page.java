package com.example.animeapp.Model;

public class Page {
    private int id;
    private String image;
    private int pageNumber;
    private int idChap;

    public Page() {
    }

    public Page(int id, String image, int pageNumber, int idChap) {
        this.id = id;
        this.image = image;
        this.pageNumber = pageNumber;
        this.idChap = idChap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getIdChap() {
        return idChap;
    }

    public void setIdChap(int idChap) {
        this.idChap = idChap;
    }
}
