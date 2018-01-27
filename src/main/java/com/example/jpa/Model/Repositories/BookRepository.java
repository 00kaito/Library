package com.example.jpa.Model.Repositories;

import com.example.jpa.Model.BookModel;
import com.example.jpa.Model.UserModel;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;
import java.util.stream.Stream;

//jpa silnik do mapowania

@Repository
//w repozytoriach tworzymy zapytania do bazy
public interface BookRepository extends CrudRepository<BookModel, Integer>{
    //pelnoprawne zapytanie zwracające ksiązki
    //których ilosc stron jeset większa niż podana w argumencie

    List<BookModel> findByPagesGreaterThan(int gt);
    List<BookModel> findByAuthorContaining(String s);
    List<BookModel> findByWho(UserModel who);
    List<BookModel> findAllByIdGreaterThan(int greater);
    boolean existsByIdAndWho(int id, BookModel who);
    boolean existsById(int id);
    List<BookModel> findById(int id);
}