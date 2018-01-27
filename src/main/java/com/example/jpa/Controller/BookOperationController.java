package com.example.jpa.Controller;

import com.example.jpa.Model.BookModel;
import com.example.jpa.Model.Repositories.BookRepository;
import com.example.jpa.Model.Repositories.UserRepository;
import com.example.jpa.Model.UserModel;
import com.example.jpa.Model.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookOperationController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/mybooks", method = RequestMethod.GET)
    public String addBookGet(Model model) {

        model.addAttribute("bookModel", new BookModel());
        if(userService.isLogIn()){
            List<BookModel> books = bookRepository.findByWho(userService.getUserModel());
            model.addAttribute("bookList", books);
        }else{
            model.addAttribute("info", "Sign in to see your books");
        }
        return "mybooks";
    }

    @RequestMapping(value = "/mybooks", method = RequestMethod.POST)
    public String addBookPost(@ModelAttribute("bookModel") BookModel bookModel, Model model) {
        if (userService.isLogIn()) {
            if(bookModel.getPages()<1 && bookModel.getTitle().length()<2 && bookModel.getAuthor().length()<5){
                model.addAttribute("info", "write real book information");
                return "mybooks";
            }
//tutaj kod, który pobierze obiekt/model aktualnie zalogowaneo użytkownika
            bookModel.setWho(userService.getUserModel());
            bookRepository.save(bookModel);
            model.addAttribute("info", "new record added successful");
            List<BookModel> books = bookRepository.findByWho(userService.getUserModel());
            model.addAttribute("bookList", books);
            return "mybooks";
        } else {
            model.addAttribute("info", "only login user can add books");
            return "mybooks";
        }
    }

    @RequestMapping(value = "/allbooks", method = RequestMethod.GET)
    public String allBooksGet(Model model) {
//        model.addAttribute("bookModel", new BookModel());
            List<BookModel> listOfBooks = bookRepository.findAllByIdGreaterThan(0);
            model.addAttribute("listOfBooks", listOfBooks);
        return "allbooks";
    }


    @RequestMapping(value = "/mybooks/", method = RequestMethod.GET)
    public String booksDeleting(@RequestParam(value="del") int id, Model model) {
        if(userService.isLogIn()) {
            if (!bookRepository.existsById(id)){
                return "redirect:/mybooks";
            }
            int ownerUserId = bookRepository.findById(id).get(0).getWho().getId();
            int currentUserId = userService.getId();
            String title = bookRepository.findById(id).get(0).getTitle();
            if (ownerUserId == currentUserId) {
                bookRepository.delete(id);
                return "redirect:/mybooks";
            }
        }
        return "redirect:/mybooks";
    }
}
