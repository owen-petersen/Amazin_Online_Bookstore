package net.azurewebsites.amazin_online_bookstore.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseTest {

    private Purchase purchase;

    @BeforeEach
    public void setUp() {
        purchase = new Purchase();
    }

    @Test
    public void testSetPurchasedBook() {
        assertNull(purchase.getPurchasedBook());

        Book book1 = new Book();
        book1.setTitle("Book One");
        book1.setAuthor("Author A");

        purchase.setPurchasedBook(book1);
        assertSame(book1, purchase.getPurchasedBook());

        // Different object, different values
        Book book2 = new Book();
        book2.setTitle("Book Two");
        book2.setAuthor("Author B");

        purchase.setPurchasedBook(book2);
        assertNotSame(book1, purchase.getPurchasedBook());
        assertSame(book2, purchase.getPurchasedBook());

        // Different object, same values as book2
        Book book3 = new Book();
        book3.setTitle("Book Two");
        book3.setAuthor("Author B");

        purchase.setPurchasedBook(book3);

        // Explicitly check reference identity
        assertNotSame(book2, purchase.getPurchasedBook());
        assertSame(book3, purchase.getPurchasedBook());
    }

    @Test
    public void testSetDateTime() {
        assertNull(purchase.getDateTime());

        String dateTime1 = "2025-11-01T14:30:00";
        purchase.setDateTime(dateTime1);
        assertEquals(dateTime1, purchase.getDateTime());

        String dateTime2 = "2025-12-15T09:00:00";
        purchase.setDateTime(dateTime2);
        assertNotEquals(dateTime1, purchase.getDateTime());
        assertEquals(dateTime2, purchase.getDateTime());
    }

    @Test
    public void testSetQuantity() {
        assertNull(purchase.getQuantity());

        Integer quantity1 = 1;
        purchase.setQuantity(quantity1);
        assertEquals(quantity1, purchase.getQuantity());

        Integer quantity2 = 5;
        purchase.setQuantity(quantity2);
        assertNotEquals(quantity1, purchase.getQuantity());
        assertEquals(quantity2, purchase.getQuantity());
    }
}
