package net.azurewebsites.amazin_online_bookstore.controller;

import net.azurewebsites.amazin_online_bookstore.datatransferobj.BookOrder;
import net.azurewebsites.amazin_online_bookstore.datatransferobj.OrderPricingSummary;
import net.azurewebsites.amazin_online_bookstore.service.PricingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param bookOrder The object form of the client request containing the list of bookIds.
     * @return An OrderPricingSummary object containing the summary of the total cost of an order.
     */
    @PostMapping("/summary")
    public OrderPricingSummary OrderSummary(@RequestBody BookOrder bookOrder) {
        return pricingService.getOrderPricingSummary(bookOrder.getItemIds());
    }
}
