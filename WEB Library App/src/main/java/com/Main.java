package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    @Autowired
    static private Facade facade;

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
        facade.books.add(new Book("badbook" , "badautor" , 1 , 1));
        facade.books.add(new Book("1111" , "aut1" , 141 ,  95));
        facade.books.add(new Book("4444 " , "aut4444" , 23589283 , 99));
        facade.books.add(new Book("2222" , "aut22" , 3423 , 13));
        facade.books.add(new Book("3333" , "aut333" , 23 , 67));
        facade.books.add(new Book("secondbook" , "aut1" , 23 , 67));
        facade.books.add(new Book("thirdbook" , "aut1" , 23 , 67));


}

}
