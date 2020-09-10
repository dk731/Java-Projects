package com;

public class Book {
    public String autor;
    public String name;
    public int pages ;
    public int rating;

    public Book (String name , String autor , int pages , int rating) {
        this.autor = autor;
        this.name = name;
        this.pages = pages;
        this.rating = rating;
    }

    public String getAutor () {
        return autor;
    }
    public String getName () {
        return name;
    }
    public int getPages () {
        return pages;
    }
    public int getRating () {
        return rating;
    }
}
