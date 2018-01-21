package com.example.jpa.Controller;

import com.example.jpa.Model.BookModel;
import com.example.jpa.Model.Repositories.BookRepository;
import com.example.jpa.Model.Repositories.UserRepository;
import com.example.jpa.Model.UserModel;
import com.example.jpa.Model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@Controller
public class MainController {
    @Autowired //przywolujemy repozytorium bez potrzeby tworzenia instancji
            UserRepository userRepository;

    @Autowired
    UserService userService;


    @GetMapping("/")
    @ResponseBody
    public String index() {
        UserModel model = new UserModel();
        model.setLogin("znaszejapki");
        model.setName("Appkowa Appka");
        model.setPassword("tajnehaslo123");
        //podajac w modelu istniejace ID tzn. setId wtedy nadpisze rekord pod tym id.
        userRepository.save(model);
        return "utworzylem nowy rekord w bazie";
    }

    @Autowired //przywolujemy repozytorium bez potrzeby tworzenia instancji
            BookRepository bookRepository;

    @GetMapping("/addbook")
    @ResponseBody
    public String addbook() {

//zapisywanie do bazy
//        BookModel model = new BookModel();
//        model.setWho(2);
//        model.setTitle("Nowy Tytul");
//        model.setAuthor("Nowy Author");
//        model.setPages(255);
        List<BookModel> books = bookRepository.findByPagesGreaterThan(120);
        List<BookModel> booksByAuthor = bookRepository.findByAuthorContaining("w");
        //        BookModel bookTable = bookRepository.findOne(3);

        return booksByAuthor.toString();
    }

    @GetMapping("/wieledojeden")
    @ResponseBody
    public String kluczobcy() {
        BookModel bookModel = bookRepository.findOne(1);
        //przeiterowania bo całej tabeli book
        Iterable<BookModel> books = bookRepository.findAll();
        for (BookModel book : books) {
            System.out.println(book.getWho());
        }
        //czyli z modelu ksiazka dostajemy sie do powiazanego z nia uzytkownika
        return "Ksiazka: " +
                bookModel.getTitle() +
                "\nAutor: " + bookModel.getAuthor() +
                "\nDodający: " + bookModel.getWho().getName();
    }

    @GetMapping("/jedendowiele")
    @ResponseBody
    public String jedendowiele() {

        UserModel userModel = userRepository.findOne(3);
        return "Author: "+ userModel.getName();
    }

    //wyspisywanie na froncie
    @GetMapping("/bookfront")
    public String bookFront(Model model) {
    List<BookModel> booksByAuthor = bookRepository.findByAuthorContaining("w");
    model.addAttribute("authorswithletter", booksByAuthor);

        return "index";
    }




    //-----------------------------------------------------------------------
//    @GetMapping("/text/{text}")
//    @ResponseBody
//    public String text(@PathVariable("text") String cos){
//        userService.setText(cos);
//        return "zmienilem tekst";
//    }
//
//    @GetMapping("/text")
//    @ResponseBody
//    public String text(){
//        return userService.getText();
//    }

}