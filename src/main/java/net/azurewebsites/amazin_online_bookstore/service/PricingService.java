package net.azurewebsites.amazin_online_bookstore.service;

import net.azurewebsites.amazin_online_bookstore.datatransferobj.OrderPricingSummary;
import net.azurewebsites.amazin_online_bookstore.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The PricingService class provides the operations necessary for pricing an order.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-04
 */
@Service
public class PricingService {
    private BookService bookService;

    public PricingService(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Get a summary of the pricing information for an order.
     * @param bookIds The list of books that make up an order.
     * @return The summary of the pricing information for an order.
     */
    public OrderPricingSummary getOrderPricingSummary(List<Integer> bookIds) {
        ArrayList<Book> books = new ArrayList<>();

        for (Integer bookId : bookIds) {
            books.add(bookService.getById(bookId));
        }

        if (books.isEmpty()) {
            return new OrderPricingSummary(0.0d, 0.0d, 0.0d, 0.0d);
        }

        Double totalPrice = 0.0d;
        Double tax;
        Double shipping = 15.00d; // TODO MA-O Oct-30-2025: Discuss calculating shipping information in another way.
        Double totalCost = 0.0d;

        for (Book book : books) {
            totalPrice += book.getPrice();
        }
        tax = totalPrice * 0.13d;
        totalCost = totalPrice + tax + shipping;

        return new OrderPricingSummary(totalPrice, tax, shipping, totalCost);
    }
}
