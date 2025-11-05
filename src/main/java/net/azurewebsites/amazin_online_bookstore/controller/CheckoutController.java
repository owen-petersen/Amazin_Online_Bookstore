package net.azurewebsites.amazin_online_bookstore.controller;

import net.azurewebsites.amazin_online_bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The CheckoutController class
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-04
 */
@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    private BookService bookService;

    public CheckoutController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public String checkoutPage(Model model) {
        return "checkout";
    }

    @PostMapping("/purchase")
    public String purchase(Model model) {
        return "purchase-complete";
    }
}
