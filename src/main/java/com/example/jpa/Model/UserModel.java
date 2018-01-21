package com.example.jpa.Model;

import javax.persistence.*;
import java.util.List;

@Entity
//teraz klasa jest encja, encja ozwierciedla tabele bazy danych
@Table(name = "user")
public class UserModel {
    @Id //adnotacja mowiaca ktory element to klucz glowny
    @GeneratedValue(strategy = GenerationType.AUTO) // adnotacja dla hibernate mowiaca ze to wartosc ustawiana automatycznie
    private int id;
    private String login;
    private String password;
//    @Column(name = "nameCos") - jesli chcialby zmienna nazwac jednak inaczej niz nazwa
// kolumny, kolumna naceCos bedzie zmapowana w programie na 'name'
    private String name;
    @OneToMany(mappedBy = "who")
    List<BookModel> bookModels;

    public List<BookModel> getBookModels() {
        return bookModels;
    }

    public void setBookModels(List<BookModel> bookModels) {
        this.bookModels = bookModels;
    }

    @Transient //dzieiki tej adnotacji ponizsze pole nie bedzie mapowane przez hibernate (ORM mapowanie relacyjno obiektowe)
    // , wiec mamy pole pomocnicze w encji
    private String myName;

    //mapowanie czasu w springu z pomiedyz czasem bazy danych a java

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }
    //kiedy mam zmienna mojCamelCase w bazie hibernate poszukuje moj_camel_case - trzeba zmienic
    //nazewnictwo ustawia się to w application.properties :spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

    //w springu do elementow prywatnych dostajemy sie getterami i setterami
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", myName='" + myName + '\'' +
                '}';
    }
}
