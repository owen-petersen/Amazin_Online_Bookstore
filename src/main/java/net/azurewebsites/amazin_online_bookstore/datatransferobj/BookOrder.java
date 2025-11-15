package net.azurewebsites.amazin_online_bookstore.datatransferobj;

import java.util.ArrayList;
import java.util.List;

/**
 * The BookOrder class is an object representation for pricing requests from the client.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-05
 */
public class BookOrder {
    private List<Integer> itemIds;

    public BookOrder() {
        this.itemIds = new ArrayList<>();
    }

    public List<Integer> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Integer> itemIds) {
        this.itemIds = itemIds;
    }
}
