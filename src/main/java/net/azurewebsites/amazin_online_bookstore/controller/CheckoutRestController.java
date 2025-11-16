package net.azurewebsites.amazin_online_bookstore.controller;

import net.azurewebsites.amazin_online_bookstore.datatransferobj.BookOrder;
import net.azurewebsites.amazin_online_bookstore.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The CheckoutRestController class
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-15
 */
@RestController
@RequestMapping("/internal/checkout")
public class CheckoutRestController {
    BookService bookService;

    public CheckoutRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/purchase")
    public boolean purchase(@RequestBody BookOrder orderItems) {
        for (Integer item: orderItems.getItemIds()) {
//            System.out.println("Book " + item + ": " + bookService.getById(item).getInventory());
            bookService.takeBookFromInventory(item);
//            System.out.println("Book " + item + ": " + bookService.getById(item).getInventory());
        }
        // TODO MA-O Nov 15 25: Include the creation of a new purchase when Users are implemented.
        return true;
    }
}
