package com.example.jpa.Model.Repositories;

import com.example.jpa.Model.BookModel;
import com.example.jpa.Model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//jpa silnik do mapowania

@Repository
//w repozytoriach tworzymy zapytania do bazy
public interface BookRepository extends CrudRepository<BookModel, Integer>{
    //pelnoprawne zapytanie zwracające ksiązki
    //których ilosc stron jeset większa niż podana w argumencie
    List<BookModel> findByPagesGreaterThan(int gt);
    List<BookModel> findByAuthorContaining(String s);

}