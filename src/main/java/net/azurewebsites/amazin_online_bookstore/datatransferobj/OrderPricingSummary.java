package net.azurewebsites.amazin_online_bookstore.datatransferobj;

/**
 * The OrderPricingSummary class is a data object that compiles pricing information for an order.
 * An order can be one item or a list of items.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-04
 */
public class OrderPricingSummary {
    private Double itemsTotal;
    private Double tax;
    private Double shipping;
    private Double orderTotal;

    /**
     * Constructor for the OrderPricingSummary class.
     * @param itemsTotal The aggregate sum of the prices items in an order.
     * @param tax The tax calculated based on the items in an order.
     * @param shipping The shipping cost for the items in an order.
     * @param orderTotal The total compiled price due to the customer.
     */
    public OrderPricingSummary(Double itemsTotal, Double tax, Double shipping, Double orderTotal) {
        this.itemsTotal = itemsTotal;
        this.tax = tax;
        this.shipping = shipping;
        this.orderTotal = orderTotal;
    }

    /**
     * Gets the aggregate sum of the items without any other costs.
     * @return The aggregate sum of the items without any other costs.
     */
    public Double getItemsTotal() {
        return itemsTotal;
    }

    /**
     * Gets the total tax based on the items of an order.
     * @return The total tax based on the items of an order.
     */
    public Double getTax() {
        return tax;
    }

    /**
     * Gets the shipping cost of an order.
     * @return The shipping cost of an order.
     */
    public Double getShipping() {
        return shipping;
    }

    /**
     * The total cost payable by the user.
     * @return The total cost payable by the user.
     */
    public Double getOrderTotal() {
        return orderTotal;
    }
}
