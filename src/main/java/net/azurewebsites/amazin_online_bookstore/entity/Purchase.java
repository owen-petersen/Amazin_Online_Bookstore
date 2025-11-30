package net.azurewebsites.amazin_online_bookstore.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Purchase {

    // Variables

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne (fetch = FetchType.EAGER)
    private Person buyer;
    @ManyToOne(fetch = FetchType.EAGER)
    private Book purchasedBook;
    private LocalDateTime dateTime;
    private Integer quantity;


    // Getters and Setters

    public int getId() {return id;}

    public Person getBuyer() {return buyer;}
    public void setBuyer(Person buyer) {this.buyer = buyer;}

    public Book getPurchasedBook() {return purchasedBook;}
    public void setPurchasedBook(Book purchasedBook) {this.purchasedBook = purchasedBook;}

    public LocalDateTime getDateTime() {return dateTime;}
    public void setDateTime(LocalDateTime dateTime) {this.dateTime = dateTime;}

    public Integer getQuantity() {return quantity;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}


    // Constructors

    public Purchase() {}

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", \n\tbuyer=" + buyer +
                ", \n\tpurchasedBook=" + purchasedBook +
                ", \n\tdateTime='" + dateTime + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
