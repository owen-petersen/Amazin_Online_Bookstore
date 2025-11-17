package net.azurewebsites.amazin_online_bookstore.controller;

import jakarta.servlet.http.HttpSession;
import net.azurewebsites.amazin_online_bookstore.datatransferobj.BookOrder;
import net.azurewebsites.amazin_online_bookstore.entity.Purchase;
import net.azurewebsites.amazin_online_bookstore.service.BookService;
import net.azurewebsites.amazin_online_bookstore.service.PersonService;
import net.azurewebsites.amazin_online_bookstore.service.PurchaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

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
    PersonService personService;
    PurchaseService purchaseService;

    public CheckoutRestController(BookService bookService,  PersonService personService, PurchaseService purchaseService) {
        this.personService = personService;
        this.purchaseService = purchaseService;
        this.bookService = bookService;
    }

    @PostMapping("/purchase")
    public boolean purchase(HttpSession session, @RequestBody BookOrder orderItems) {
        HashMap<Integer, Integer> itemAndFreq = new HashMap<>();
        for (Integer item: orderItems.getItemIds()) {
            bookService.takeBookFromInventory(item);
            itemAndFreq.put(item, itemAndFreq.getOrDefault(item, 0) + 1);
        }

        // Save purchases
        for (Integer item: itemAndFreq.keySet()) {
            Purchase purchase = new Purchase();
            purchase.setBuyer(personService.findById((Integer) session.getAttribute("userId")));
            purchase.setPurchasedBook(bookService.getById(item));
            purchase.setQuantity(itemAndFreq.get(item));
            purchase.setDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            purchaseService.saveNewPurchase(purchase);
            System.out.println(purchase);
        }

        return true;
    }
}
