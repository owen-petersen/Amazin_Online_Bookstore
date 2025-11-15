package net.azurewebsites.amazin_online_bookstore.datatransferobj;

import net.azurewebsites.amazin_online_bookstore.service.BookService;

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
     * Default constructor.
     */
    public OrderPricingSummary() {
        this.itemsTotal = 0.0d;
        this.tax = 0.0d;
        this.shipping = 0.0d;
        this.orderTotal = 0.0d;
    }

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
     * Gets the total cost payable by the user.
     * @return The total cost payable by the user.
     */
    public Double getOrderTotal() {
        return orderTotal;
    }

    /**
     * Sets the aggregate sum of items without other costs.
     * @param itemsTotal The new total to assign.
     */
    public void setItemsTotal(Double itemsTotal) {
        this.itemsTotal = itemsTotal;
    }

    /**
     * Sets the tax of the items.
     * @param tax The tax to assign.
     */
    public void setTax(Double tax) {
        this.tax = tax;
    }

    /**
     * Sets the shipping cost of the order.
     * @param shipping The shipping cost to assign.
     */
    public void setShipping(Double shipping) {
        this.shipping = shipping;
    }

    /**
     * Sets the total of the order.
     * @param orderTotal The new total of the order.
     */
    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }
}
