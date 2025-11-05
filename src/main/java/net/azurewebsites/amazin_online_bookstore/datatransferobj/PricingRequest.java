package net.azurewebsites.amazin_online_bookstore.datatransferobj;

import java.util.List;

/**
 * The PricingRequest class is an object representation for pricing requests from the client.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-05
 */
public class PricingRequest {
    private List<Integer> itemIds;

    public  PricingRequest() {}

    public List<Integer> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Integer> itemIds) {
        this.itemIds = itemIds;
    }
}
