package com.example.jpa.Controller;

import com.example.jpa.Model.Repositories.UserRepository;
import com.example.jpa.Model.UserModel;
import com.example.jpa.Model.forms.RegisterForm;
import com.example.jpa.Model.services.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

//co jeden wiekszy model/modyl tworzymy nowy kontroller
@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginGet() {
        return "login";
    }

    //walidować za pomocą @ można tylko modele
    @PostMapping("/login")
    public String loginPost(@RequestParam("login") String login,
                            @RequestParam("password") String password,
                            Model model) {
        boolean isCorrectData = userRepository.existsByLoginAndPassword(login, password);
        if(isCorrectData){
            userService.setLogin(login);
            userService.setLogIn(true);
            return "login";
        }else{
            model.addAttribute("info", "Błędne dane logowania!");
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerGet(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute @Valid RegisterForm form,
                               BindingResult result, //klasa ktora przechowyuje informacja na temat wystepionych bledow
                               Model model){
        //sprawdzenie i zapis do bazy
        boolean loginExist = userRepository.existsByLogin(form.getLogin());
        if (result.hasErrors()){
            return "register";
        }else if(loginExist){
            model.addAttribute("info", "Wybrany login już istnieje w bazie");
            return "register";
        }else{
            UserModel user = new UserModel();
            user.setLogin(form.getLogin());
            user.setPassword(form.getPassword());
            user.setName(form.getName());
            userRepository.save(user);
            model.addAttribute("info", "Nowe konto zostało założone");
            return "register";
        }
    }
}
