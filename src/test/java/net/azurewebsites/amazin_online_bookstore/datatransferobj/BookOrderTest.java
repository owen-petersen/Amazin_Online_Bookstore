package net.azurewebsites.amazin_online_bookstore.datatransferobj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BookOrderTest {
    private BookOrder bookOrder;

    @BeforeEach
    void setUp() {
        bookOrder = new BookOrder();
    }

    @Test
    void getItemIds() {
        assertTrue(bookOrder.getItemIds().isEmpty());
        bookOrder.setItemIds(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        assertEquals(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)), bookOrder.getItemIds());
    }

    @Test
    void setItemIds() {
        bookOrder.setItemIds(new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60)));
        assertEquals(new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60)), bookOrder.getItemIds());
        bookOrder.setItemIds(new ArrayList<>());
        assertTrue(bookOrder.getItemIds().isEmpty());
    }
}