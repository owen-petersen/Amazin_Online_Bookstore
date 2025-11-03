package net.azurewebsites.amazin_online_bookstore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
    }

    @Test
    public void testSetTitle() {
        assertNull(book.getTitle());

        String title1 = "First Title";
        book.setTitle(title1);
        assertEquals(title1, book.getTitle());

        String title2 = "Second Title";
        book.setTitle(title2);
        assertNotEquals(title1, book.getTitle());
        assertEquals(title2, book.getTitle());
    }

    @Test
    public void testSetAuthor() {
        assertNull(book.getAuthor());

        String author1 = "Author One";
        book.setAuthor(author1);
        assertEquals(author1, book.getAuthor());

        String author2 = "Author Two";
        book.setAuthor(author2);
        assertNotEquals(author1, book.getAuthor());
        assertEquals(author2, book.getAuthor());
    }

    @Test
    public void testSetIsbn() {
        assertNull(book.getIsbn());

        String isbn1 = "1234567890";
        book.setIsbn(isbn1);
        assertEquals(isbn1, book.getIsbn());

        String isbn2 = "0987654321";
        book.setIsbn(isbn2);
        assertNotEquals(isbn1, book.getIsbn());
        assertEquals(isbn2, book.getIsbn());
    }

    @Test
    public void testSetPublisher() {
        assertNull(book.getPublisher());

        String publisher1 = "Publisher A";
        book.setPublisher(publisher1);
        assertEquals(publisher1, book.getPublisher());

        String publisher2 = "Publisher B";
        book.setPublisher(publisher2);
        assertNotEquals(publisher1, book.getPublisher());
        assertEquals(publisher2, book.getPublisher());
    }

    @Test
    public void testSetPublishedYear() {
        assertNull(book.getPublishedYear());

        Integer year1 = 2000;
        book.setPublishedYear(year1);
        assertEquals(year1, book.getPublishedYear());

        Integer year2 = 2020;
        book.setPublishedYear(year2);
        assertNotEquals(year1, book.getPublishedYear());
        assertEquals(year2, book.getPublishedYear());
    }

    @Test
    public void testSetEdition() {
        assertNull(book.getEdition());

        Integer edition1 = 1;
        book.setEdition(edition1);
        assertEquals(edition1, book.getEdition());

        Integer edition2 = 2;
        book.setEdition(edition2);
        assertNotEquals(edition1, book.getEdition());
        assertEquals(edition2, book.getEdition());
    }

    @Test
    public void testSetGenre() {
        assertNull(book.getGenre());

        String genre1 = "Fiction";
        book.setGenre(genre1);
        assertEquals(genre1, book.getGenre());

        String genre2 = "Non-Fiction";
        book.setGenre(genre2);
        assertNotEquals(genre1, book.getGenre());
        assertEquals(genre2, book.getGenre());
    }

    @Test
    public void testSetInventory() {
        assertNull(book.getInventory());

        Integer inventory1 = 10;
        book.setInventory(inventory1);
        assertEquals(inventory1, book.getInventory());

        Integer inventory2 = 20;
        book.setInventory(inventory2);
        assertNotEquals(inventory1, book.getInventory());
        assertEquals(inventory2, book.getInventory());
    }

    @Test
    public void testSetNumOfPages() {
        assertNull(book.getNumOfPages());

        Integer pages1 = 100;
        book.setNumOfPages(pages1);
        assertEquals(pages1, book.getNumOfPages());

        Integer pages2 = 200;
        book.setNumOfPages(pages2);
        assertNotEquals(pages1, book.getNumOfPages());
        assertEquals(pages2, book.getNumOfPages());
    }

    @Test
    public void testSetPrice() {
        assertNull(book.getPrice());

        Double price1 = 9.99;
        book.setPrice(price1);
        assertEquals(price1, book.getPrice());

        Double price2 = 19.99;
        book.setPrice(price2);
        assertNotEquals(price1, book.getPrice());
        assertEquals(price2, book.getPrice());
    }
}
