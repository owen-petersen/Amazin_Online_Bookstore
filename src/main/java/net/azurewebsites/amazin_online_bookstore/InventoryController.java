package net.azurewebsites.amazin_online_bookstore;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InventoryController {

    @Autowired
    private BookService bookService;

    @GetMapping("/inventory")
    public String showInventoryForm(HttpSession session) {
        if (session.getAttribute("role") != Person.Role.Employee) {
            // TODO send access denied response
            return "error";
        }
        return "page";
    }

    @PostMapping("/inventory")
    public String restockItem(
            @RequestParam("isbn") String isbn,
            @RequestParam("numOfItems") Integer numOfItems,
            HttpSession session) {

        if (session.getAttribute("role") != Person.Role.Employee) {
            // TODO send access denied response
            return "error";
        }

        if (bookService.existsByIsbn(isbn)) {
            bookService.setInventoryByIsbn(isbn, numOfItems);
            return "success";
        } else {
            return "redirect:/inventory/new";
        }
    }

    @GetMapping("/inventory/new")
    public String newItemForm(HttpSession session) {
        if (session.getAttribute("role") != Person.Role.Employee) {
            // TODO send access denied response
            return "error";
        }
        return "new item page";
    }

    @PostMapping("inventory/new")
    public String newItem(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("isbn") String isbn,
            @RequestParam("publisher") String publisher,
            @RequestParam("publishedYear") Integer publishedYear,
            @RequestParam("edition") Integer edition,
            @RequestParam("genre") String genre,
            @RequestParam("inventory") Integer numOfItems,
            @RequestParam("numOfPages") Integer numOfPages,
            @RequestParam("price") Double price,
            HttpSession session){
        if (session.getAttribute("role") != Person.Role.Employee) {
            // TODO send access denied response
            return "error";
        }
        if (bookService.existsByIsbn(isbn)) {

            return "error";
        }
        bookService.saveNewBook(new Book() {
            {
                setTitle(title);
                setAuthor(author);
                setIsbn(isbn);
                setPublisher(publisher);
                setPublishedYear(publishedYear);
                setEdition(edition);
                setGenre(genre);
                setInventory(numOfItems);
                setNumOfPages(numOfPages);
                setPrice(price);
            }
        });

    }
}
