package com.example.jpa.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rentaldetails")
public class RentalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @NotNull
    @Column(name = "userwhorentsid")
    int rentsUserId;

    @NotNull
    @JoinColumn(name="ownerUserId")
    int ownerUserId;

    @JoinColumn(name="bookId")
    @NotNull
    int bookId;
    String rentalDate;
    String returnDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRentsUserId() {
        return rentsUserId;
    }

    public void setRentsUserId(int rentsUserId) {
        this.rentsUserId = rentsUserId;
    }

    public int getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(int ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
