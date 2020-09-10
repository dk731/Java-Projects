package com;

public class Autor {


    public String name;
    public int booksCount;
    public int sumRating;



    public int avgRating;

    public Autor (String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getBooksCount() {
        return booksCount;
    }

    public int getSumRating() {
        return sumRating;
    }

    public int getAvgRating() {
        return avgRating;
    }
}
