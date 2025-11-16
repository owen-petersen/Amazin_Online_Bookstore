package net.azurewebsites.amazin_online_bookstore.entity;

import jakarta.persistence.*;

@Entity
public class Purchase {

    // Variables

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Book purchasedBook;
    private String dateTime; //TODO OP 25-Oct-2025 Replace with actual date/time type
    private Integer quantity;


    // Getters and Setters

    public int getId() {return id;}

    public Book getPurchasedBook() {return purchasedBook;}
    public void setPurchasedBook(Book purchasedBook) {this.purchasedBook = purchasedBook;}

    public String getDateTime() {return dateTime;}
    public void setDateTime(String dateTime) {this.dateTime = dateTime;}

    public Integer getQuantity() {return quantity;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}


    // Constructors

    public Purchase() {}
}
