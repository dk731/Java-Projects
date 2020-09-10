package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AppController {

    @Autowired
    private Facade facade;

    @RequestMapping("/jsp")
    public String jspStart(ModelMap model){
        model.addAttribute("booksList" , facade.getBooks());
        return "web";
    }

    @RequestMapping("/")
    public String jspClean(ModelMap model){
        model.addAttribute("booksList" , facade.getBooks());
        return "web";
    }

    @RequestMapping("sortAction")
    public String sortAction(ModelMap model, @RequestParam String sortParam) {
        model.addAttribute("booksList" , facade.getSorted(sortParam));
        return "web";
    }

    @RequestMapping("addAction")
    public String addAction(ModelMap model, @RequestParam String autorAdd , @RequestParam String nameAdd , @RequestParam int pagesAdd , @RequestParam int ratingAdd) {
        facade.addBook(autorAdd,nameAdd,pagesAdd,ratingAdd);
        model.addAttribute("booksList" , facade.getBooks());
        return "web";
    }

    @RequestMapping("delAction")
    public String delAction(ModelMap model, @RequestParam String delName) {
        facade.removeBook(delName);
        model.addAttribute("booksList" , facade.getBooks());
        return "web";
    }

    @RequestMapping("goToAutors")
    public String goToAutor(ModelMap model) {
        model.addAttribute("autorsList" , facade.getAutors());
        return "webAutor";
    }

    @RequestMapping("goToBooks")
    public String goToBooks(ModelMap model) {
        model.addAttribute("booksList" , facade.getBooks());
        return "web";
    }



}
