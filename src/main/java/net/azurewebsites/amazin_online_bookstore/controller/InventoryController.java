package net.azurewebsites.amazin_online_bookstore.controller;

import jakarta.servlet.http.HttpSession;
import net.azurewebsites.amazin_online_bookstore.datatransferobj.RestockRequest;
import net.azurewebsites.amazin_online_bookstore.entity.Book;
import net.azurewebsites.amazin_online_bookstore.entity.Person;
import net.azurewebsites.amazin_online_bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InventoryController {

    @Autowired
    private BookService bookService;

    @GetMapping("/inventory")
    public String showInventoryForm(Model model, HttpSession session) {
        if (session.getAttribute("role") != Person.Role.Employee) {
            return "error/403";
        }
        model.addAttribute("restockReq", new RestockRequest());
        return "inventory";
    }

    @PostMapping("/inventory")
    public String restockItem(
            @ModelAttribute RestockRequest restockReq,
            HttpSession session)
    {

        if (session.getAttribute("role") != Person.Role.Employee) {
            return "error/403";
        }

        if (bookService.existsByIsbn(restockReq.getIsbn())) {
            bookService.restockBookInInventory(restockReq.getIsbn(), restockReq.getQuantity());
            return "redirect:/";
        } else {
            return "redirect:/inventory/new";
        }
    }

    @GetMapping("/inventory/new")
    public String newItemForm(HttpSession session, Model model) {
        if (session.getAttribute("role") != Person.Role.Employee) {
            return "error/403";
        }
        model.addAttribute("book", new Book());
        model.addAttribute("message", "Book not found in database, please create a new one.");
        return "newInventory";
    }

    @PostMapping("inventory/new")
    public String newItem(
            @ModelAttribute Book book,
            HttpSession session,
            Model model) {
        if (session.getAttribute("role") != Person.Role.Employee) {
            return "error/403";
        }
        if (bookService.existsByIsbn(book.getIsbn())) {
            model.addAttribute("message", "ISBN already exists");
            return "newInventory";
        }
        bookService.saveNewBook(book);

        model.addAttribute("message", "Created new book");
        return "newInventory";
    }
}
