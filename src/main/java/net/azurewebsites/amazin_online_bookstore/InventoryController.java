package net.azurewebsites.amazin_online_bookstore;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InventoryController {

    @Autowired
    private BookService bookService;

    @GetMapping("/inventory")
    public String showInventoryForm(Model model, HttpSession session) {
        // TODO check role and deny access
//        if (session.getAttribute("role") != Person.Role.Employee) {
//            return "error";
//        }
        model.addAttribute("restockReq", )
        return "inventory";
    }

    @PostMapping("/inventory")
    public ResponseEntity<?> restockItem(
            @RequestParam("isbn") String isbn,
            @RequestParam("numOfItems") Integer numOfItems,
            HttpSession session) {

        if (session.getAttribute("role") != Person.Role.Employee) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (bookService.existsByIsbn(isbn)) {
            bookService.setInventoryByIsbn(isbn, numOfItems);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ISBN not found.");
        }
    }

    @GetMapping("/inventory/new")
    public String newItemForm(HttpSession session) {
        // TODO check role and deny access
//        if (session.getAttribute("role") != Person.Role.Employee) {
//            return "error";
//        }
        return "inventoryNew";
    }

    @PostMapping("inventory/new")
    public ResponseEntity<?> newItem(
            @RequestParam("book") Book book,
            HttpSession session){
        if (session.getAttribute("role") != Person.Role.Employee) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (bookService.existsByIsbn(book.getIsbn())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ISBN already exists.");
        }
        bookService.saveNewBook(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }
}
