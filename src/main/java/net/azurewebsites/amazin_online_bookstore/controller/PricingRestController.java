package net.azurewebsites.amazin_online_bookstore.controller;

import net.azurewebsites.amazin_online_bookstore.datatransferobj.OrderPricingSummary;
import net.azurewebsites.amazin_online_bookstore.service.PricingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The PricingRestController class provides pricing data for the pages that request it.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-04
 */
@RestController
@RequestMapping("/internal/pricing")
public class PricingRestController {
    private PricingService pricingService;

    public PricingRestController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    /**
     * Gives an object containing the summary of the total cost of an order.
     * @param bookIds The list of book ids for the books in an order.
     * @return An OrderPricingSummary object containing the summary of the total cost of an order.
     */
    public OrderPricingSummary OrderSummary(List<Integer> bookIds) {
        return pricingService.getOrderPricingSummary(bookIds);
    }
}
