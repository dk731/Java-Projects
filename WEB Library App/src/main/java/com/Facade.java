package com;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class Facade {

    static List<Book> books = new ArrayList<>();
    static List<Autor> autors = new ArrayList<>();

    public List getBooks() {
        //books.sort(Comparator.comparing(Book::getAutor));
        return books;
    }

    public String addBook( String autor , String name , int pages , int rating) {
        books.add(new Book( name , autor , pages , rating));
        return "DONE!";
    }

    public List getSorted (String sortParam) {
        String temp = "";
        List<Book> tempBooks = books;
        switch(sortParam) {
            case "autor":
                tempBooks.sort(Comparator.comparing(Book::getAutor));
                return tempBooks;
            case "name":
                tempBooks.sort(Comparator.comparing(Book::getName));
                return tempBooks;
            case "pages":
                tempBooks.sort(Comparator.comparing(Book::getPages));
                return tempBooks;
            case "rating":
                tempBooks.sort(Comparator.comparing(Book::getRating));
                return tempBooks;
        }
        return tempBooks;
    }

    public String removeBook (String name) {

        for (Book a : books) {
            if (a.getName().equals(name)) {
                books.remove(a);
                return "DONE!";
            }
        }

        return "NO BOOK WITH THIS NAME WAS FOUND!";
    }

    public String getAutor (String autor , String sortParam) {
        List<Book> tempList = new ArrayList<>(  );
        boolean autorFind = false;
        for (Book a : books) {
            if (a.getAutor().equals(autor)) {
                tempList.add(a);
                autorFind = true;
            }
        }

        if (!autorFind) {
            return "No books were found!";
        }

        if (sortParam.equals("asc")) {
            tempList.sort(Comparator.comparing(Book::getName));
        } else {
            tempList.sort(Comparator.comparing(Book::getName).reversed());
        }

        return makeString(tempList);
    }

    public String editBook (String name , String editParam , String value) {

        for (Book a : books) {
            if (a.getName().equals(name)) {
                switch(editParam) {
                    case "autor":
                        a.autor = value;
                        return "DONE!";
                    case "name":
                        a.name = value;
                        return "DONE!";
                    case "pages":
                        a.pages = Integer.valueOf(value);
                        return "DONE!";
                    case "rating":
                        a.rating = Integer.valueOf(value);
                        return "DONE!";
                }
            }
        }

        return "NO BOOK WITH THIS NAME WAS FOUND!";
    }



    public String getAutorStats (String autor) {
        String temp = "";
        int booksAmount = 0;
        List<Book> autorBooks = new ArrayList<>();
        boolean autorFind = false;
        int avgRating = 0;
        int avgPages = 0;

        for (Book a : books) {
            if (a.getAutor().equals(autor)) {
                autorBooks.add(a);
                autorFind = true;
            }
        }

        if (!autorFind) {
            return "No books were find!";
        }

        Book longestBook = books.get(0);
        Book bestBook = books.get(0);
        Book worstBook = books.get(0);
        Book shortestBook = books.get(0);

        for (Book a : autorBooks) {
            if (a.rating > bestBook.rating) {
                bestBook = a;
            }

            if (a.pages > longestBook.pages) {
                longestBook = a;
            }

            booksAmount +=1;

            if (a.rating < worstBook.rating) {
                worstBook = a;
            }

            if (a.pages < shortestBook.pages) {
                shortestBook = a;
            }

            avgRating = a.rating + avgRating;
            avgPages = a.pages + avgPages;
        }

        avgRating = avgRating / booksAmount;
        avgPages = avgPages / booksAmount;

        temp =  "Best book by rating : " + bestBook.name + " - " + bestBook.rating +
                "   | Longest book : " + longestBook.name + " - " + longestBook.pages +
                "   | Worst book : " + worstBook.name + " - " + worstBook.rating +
                "   | Shortest book : " + shortestBook.name + " - " + shortestBook.pages +
                "   | AVG rating : " + avgRating +
                "   | AVG pages : " + avgPages +
                "   | Books amount : " + booksAmount;

        return temp;
    }

    public String getStats () {
        String temp = "";
        int booksAmount = 0;

        Book longestBook = books.get(0);
        Book bestBook = books.get(0);
        Book worstBook = books.get(0);
        Book shortestBook = books.get(0);

        for (Book a : books) {
            if (a.rating > bestBook.rating) {
                bestBook = a;
            }

            if (a.pages > longestBook.pages) {
                longestBook = a;
            }

            booksAmount +=1;

            if (a.rating < worstBook.rating) {
                worstBook = a;
            }

            if (a.pages < shortestBook.pages) {
                shortestBook = a;
            }
        }


        temp =  "Best book by rating : " + bestBook.name + " - " + bestBook.rating +
                "   | Longest book : " + longestBook.name + " - " + longestBook.pages +
                "   | Worst book : " + worstBook.name + " - " + worstBook.rating +
                "   | Shortest book : " + shortestBook.name + " - " + shortestBook.pages +
                "   | Books amount : " + booksAmount;

        return temp;
    }



    private String makeString(List<Book> list) {
        String temp = "";

        for (int i = 0 ; i < list.size() ; i++) {
            temp = temp + list.get(i).autor + " ";
            temp = temp + list.get(i).name + " ";
            temp = temp + list.get(i).pages + " ";
            temp = temp + list.get(i).rating + "   |   ";
        }

        return temp;
    }

    public List getAutors() {
        autors.clear();
        List<String> names = new ArrayList<>();
        Autor tempAutor;
        for (Book i : books) {
            if (names.contains(i.autor)) {
                for (Autor aut : autors) {
                    if (i.autor.equals(aut.name)) {
                        aut.sumRating += i.rating;
                        aut.booksCount += 1;
                    }
                }
            } else {
                tempAutor = new Autor(i.autor);
                tempAutor.booksCount = 1;
                tempAutor.sumRating = i.rating;
                autors.add(tempAutor);
                names.add(i.autor);
            }
        }
        for (Autor i : autors) {
            i.avgRating = i.sumRating / i.booksCount;
        }
        return autors;
    }

}
