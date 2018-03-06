package com.example.jpa.Model.Repositories;
import com.example.jpa.Model.BookModel;
import com.example.jpa.Model.RentalModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends CrudRepository<RentalModel, Integer> {
    List<RentalModel> findByOwnerUserId(int id);
    List<RentalModel> findByRentsUserId(int id);
}
