package com.example.jpa.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//do kazdego modelu/encji potrzebne jest repozytorium 10 encji = 10 repozytoriow
@Entity
@Table(name = "book")
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    //patrzymy z perspektywy encji w ktorej jestesmy
    @ManyToOne //wiele do jednego, możemy miec wiele książek dodanych przez 1 usera
    @JoinColumn(name="who") //jezeli pole nazywa się jak zmienna ponizej,w tedy name= nie jest potrzebne
    private UserModel who; //zmieniamy z int na UserModel, wele ksiązek do 1 UserModel
    private String title;
    private String author;
    private int pages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getWho() {
        return who;
    }

    public void setWho(UserModel who) {
        this.who = who;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", who=" + who +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                '}';
    }
}