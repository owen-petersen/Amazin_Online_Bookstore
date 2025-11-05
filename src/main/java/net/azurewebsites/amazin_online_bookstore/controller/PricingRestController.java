package net.azurewebsites.amazin_online_bookstore.controller;

import net.azurewebsites.amazin_online_bookstore.datatransferobj.OrderPricingSummary;
import net.azurewebsites.amazin_online_bookstore.datatransferobj.PricingRequest;
import net.azurewebsites.amazin_online_bookstore.service.PricingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * @param pricingRequest The object form of the client request containing the list of bookIds.
     * @return An OrderPricingSummary object containing the summary of the total cost of an order.
     */
    @PostMapping("/summary")
    public OrderPricingSummary OrderSummary(@RequestBody PricingRequest pricingRequest) {
        return pricingService.getOrderPricingSummary(pricingRequest.getItemIds());
    }
}
