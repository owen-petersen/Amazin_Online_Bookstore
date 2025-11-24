package net.azurewebsites.amazin_online_bookstore.entity;

import jakarta.persistence.*;

@Entity
public class Book {

    // Variables

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Integer publishedYear;
    private Integer edition;
    private String genre;
    private Integer inventory;
    private Integer numOfPages;
    private Double price;


    // Getters and Setters

    public int getId() {return id;}

    public String getTitle() {return title;}
    public void  setTitle(String title) {this.title = title;}

    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}

    public String getIsbn() {return isbn;}
    public void setIsbn(String isbn) {this.isbn = isbn;}

    public String getPublisher() {return publisher;}
    public void setPublisher(String publisher) {this.publisher = publisher;}

    public Integer getPublishedYear() {return publishedYear;}
    public void setPublishedYear(Integer publishedYear) {this.publishedYear = publishedYear;}

    public Integer getEdition() {return edition;}
    public void setEdition(Integer edition) {this.edition = edition;}

    public String getGenre() {return genre;}
    public void setGenre(String genre) {this.genre = genre;}

    public Integer getInventory() {return inventory;}
    public void decrementInventory() {
        if  (this.inventory > 0) this.inventory--;
        if (this.inventory < 0) this.inventory = 0;
    }
    public void incrementInventory() {
        this.inventory++;
    }
    public void setInventory(Integer inventory) {this.inventory = inventory;}

    public Integer getNumOfPages() {return numOfPages;}
    public void setNumOfPages(Integer numOfPages) {this.numOfPages = numOfPages;}

    public Double getPrice() {return price;}
    public void setPrice(Double price) {this.price = price;}


    // Constructors

    public Book() {}
}
