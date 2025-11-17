package net.azurewebsites.amazin_online_bookstore.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import net.azurewebsites.amazin_online_bookstore.entity.Book;
import net.azurewebsites.amazin_online_bookstore.service.BookService;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class HomeController {

    // TODO: Redirect here after login once login flow is updated.
    // (home page) instead of welcome.html.

    private final BookService bookService;

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {

        //If the user is not logged in, user should go to login page first
        //If the user is already logged in go to homepage
        if(session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("q", ""); // empty search box
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "q", required = false) String q,
                         Model model,
                         HttpSession session) {

        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        List<Book> books = bookService.search(q);
        model.addAttribute("books", books);
        model.addAttribute("q", q == null ? "" : q); // keeps the text in the search bar
        return "index";
    }

}
