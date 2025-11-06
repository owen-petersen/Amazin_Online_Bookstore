package net.azurewebsites.amazin_online_bookstore.datatransferobj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PricingRequestTest {
    private PricingRequest pricingRequest;

    @BeforeEach
    void setUp() {
        pricingRequest = new PricingRequest();
    }

    @Test
    void getItemIds() {
        assertTrue(pricingRequest.getItemIds().isEmpty());
        pricingRequest.setItemIds(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        assertEquals(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)), pricingRequest.getItemIds());
    }

    @Test
    void setItemIds() {
        pricingRequest.setItemIds(new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60)));
        assertEquals(new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60)), pricingRequest.getItemIds());
        pricingRequest.setItemIds(new ArrayList<>());
        assertTrue(pricingRequest.getItemIds().isEmpty());
    }
}