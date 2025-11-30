package net.azurewebsites.amazin_online_bookstore.controller;

import jakarta.servlet.http.HttpSession;
import net.azurewebsites.amazin_online_bookstore.datatransferobj.BookOrder;
import net.azurewebsites.amazin_online_bookstore.entity.Person;
import net.azurewebsites.amazin_online_bookstore.repository.PersonRepository;
import net.azurewebsites.amazin_online_bookstore.service.BookService;
import net.azurewebsites.amazin_online_bookstore.service.JaccardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * The CheckoutRestController class
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-15
 */
@RestController
@RequestMapping("/internal/checkout")
public class CheckoutRestController {
    PersonRepository personRepository;

    BookService bookService;
    JaccardService jaccardService;

    public CheckoutRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/purchase")
    public boolean purchase(HttpSession session, @RequestBody BookOrder orderItems) {
        Optional<Person> person =
                personRepository.findByUsername(String.valueOf(session.getAttribute("username")));

        if (person.isEmpty()) { return false; }

        for (Integer item: orderItems.getItemIds()) {
//            System.out.println("Book " + item + ": " + bookService.getById(item).getInventory());
            bookService.takeBookFromInventory(item);
//            System.out.println("Book " + item + ": " + bookService.getById(item).getInventory());
        }
        // TODO MA-O Nov 15 25: Include the creation of a new purchase when Users are implemented.

        jaccardService.UpdateJaccardsForUser(person.get());

        return true;
    }
}
