package net.azurewebsites.amazin_online_bookstore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The CartController class handles the routing for the cart web page.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-02
 */
@Controller
public class CartController {
    public CartController() {}

    @GetMapping("/cart")
    public String cart() {
        return "shopping-cart";
    }
}
