package com.example.jpa.Model.Repositories;
//jedno repozytorium odpowiada jednej encji

import com.example.jpa.Model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

//repo to interfejs, ktory zawiera w sobie podstawowe CRUDowe metody
//UserRepository to ziarno i jest singletonem
//ziarno to klasa z mozliwoscią serializacji (musi się dać zamienić klasę w ciąg znakow)
// i konstruktorem bezargumentowym
public interface UserRepository extends CrudRepository<UserModel, Integer>{ // tu w argumecie kolekcji podajemy obiekt i typ id w tym obiekcie
    boolean existsByLoginAndPassword(String login, String Password);
    boolean existsByLogin(String login);
    UserModel getByLoginEquals(String login);
}
