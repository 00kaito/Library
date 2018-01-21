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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookOperationCotroller {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/addbookform", method = RequestMethod.GET)
    public String addBookGet(Model model){
        model.addAttribute("bookModel", new BookModel());
        return "newbook";
    }

    @RequestMapping(value = "/addbookform", method = RequestMethod.POST)
    public String addBookPost(@ModelAttribute("bookModel") BookModel bookModel, Model model){

        if(userService.isLogIn()){
            
//tutaj kod, który pobierze obiekt/model aktualnie zalogowaneo użytkownika

            bookRepository.save(bookModel);
            model.addAttribute("info", "pozycja dodana");
            return "newbook";
        }else{
            model.addAttribute("info", "musisz być zalogowany, aby móc dodawać ksiązki");
            return "newbook";
        }

    }
}
