package com.example.jpa.Controller;

import com.example.jpa.Model.BookModel;
import com.example.jpa.Model.RentalModel;
import com.example.jpa.Model.Repositories.BookRepository;
import com.example.jpa.Model.Repositories.RentalRepository;
import com.example.jpa.Model.Repositories.UserRepository;
import com.example.jpa.Model.UserModel;
import com.example.jpa.Model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RentalsController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    RentalRepository rentalRepository;

    @RequestMapping(value = "/rent", method = RequestMethod.GET)
    public String getRentalsGet(ModelMap modelMap) {
        List<BookModel> listOfAvailable = new ArrayList<>();
        for (BookModel bm : bookRepository.findAll()) {
            if (bm.getAvailability() == 0 && bm.getWho().getId() != userService.getUserModel().getId()) {
                System.out.println(bm.getTitle() + " - " + bm.getAvailability());
                listOfAvailable.add(bm);
            }
        }
        modelMap.addAttribute("listOfAvailable", listOfAvailable);
        return "rent";
    }

    @RequestMapping(value = "/rent/{id}", method = RequestMethod.GET)
    public String rent(@PathVariable int id, ModelMap modelMap) {
        BookModel bm = bookRepository.findById(id).get(0);
        System.out.println("ID: "+id);
        if(bm.getAvailability()==0){
            System.out.println("pozycja dostępna! ____________");
            bm.setAvailability(userService.getId());
        }
        bookRepository.save(bm);
        return "myborrows";
    }

    @GetMapping("/rent/myborrows")
    public String getMyRents(ModelMap modelMap){
        modelMap.addAttribute("bookList", bookRepository.findByAvailability(userService.getId()));
        return "asdf";
    }

    @RequestMapping(value = "/rent/myborrows", params = "giveback", method = RequestMethod.GET)
    public String giveBack(@RequestParam(value = "giveback") int id, ModelMap modelMap){
            BookModel bm = bookRepository.findById(id).get(0);
            if(bm.getAvailability()!=0){
                bm.setAvailability(0);
            }
            bookRepository.save(bm);
            return "myborrows";
        }

}
