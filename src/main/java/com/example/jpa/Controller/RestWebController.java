package com.example.jpa.Controller;

import com.example.jpa.Model.Repositories.BookRepository;
import com.example.jpa.Model.Repositories.UserRepository;
import com.example.jpa.Model.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.jpa.message.Response;
import com.example.jpa.Model.BookModel;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class RestWebController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    ObjectMapper mapper = new ObjectMapper();

    Gson gson = new Gson();
    String text = "";

    List<BookModel> books = new ArrayList<>();
    List<UserModel> users = new ArrayList<>();

    String info = "";
    JSONObject jsonInfo = new JSONObject();

    @GetMapping(value = "/all")
    @ResponseBody
    public String getResource() throws JsonProcessingException, JSONException {

        for(BookModel book : bookRepository.findAll()){
            jsonInfo.put("title", book.getTitle());
            System.out.println(jsonInfo.toString(4));
        }
        System.out.println("____________________");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonInfo.toString(4));
        String prettyJsonString = gson.toJson(je);


        Response response = new Response("Done", users);
        return prettyJsonString;
    }
}
