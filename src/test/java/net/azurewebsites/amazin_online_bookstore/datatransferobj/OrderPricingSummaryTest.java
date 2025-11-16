package net.azurewebsites.amazin_online_bookstore.datatransferobj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderPricingSummaryTest {
    @Test
    void defaultSummaryIsCorrectlyEmpty() {
        OrderPricingSummary orderPricingSummary = new OrderPricingSummary();
        assertEquals(0.0d, orderPricingSummary.getItemsTotal(), 0.0);
        assertEquals(0.0d, orderPricingSummary.getTax(), 0.0);
        assertEquals(0.0d, orderPricingSummary.getShipping(), 0.0);
        assertEquals(0.0d, orderPricingSummary.getOrderTotal(), 0.0);
    }

    @Test
    void populatedSummaryIsCorrectlyPopulated() {
        OrderPricingSummary orderPricingSummary = new OrderPricingSummary(1.0d, 0.13d, 3.0d, 4.13d);
        assertEquals(1.0d, orderPricingSummary.getItemsTotal(), 0.0);
        assertEquals(0.13d, orderPricingSummary.getTax(), 0.0);
        assertEquals(3.0d, orderPricingSummary.getShipping(), 0.0);
        assertEquals(4.13d, orderPricingSummary.getOrderTotal(), 0.0);
    }
}