package net.azurewebsites.amazin_online_bookstore.datatransferobj;

public class RestockRequest {
    private String isbn;
    private Integer quantity;

    public RestockRequest() {}

    public String getIsbn() { return this.isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getQuantity() { return this.quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
