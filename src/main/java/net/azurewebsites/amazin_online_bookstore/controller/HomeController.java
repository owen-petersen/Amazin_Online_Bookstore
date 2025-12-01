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

        // default empty filters
        model.addAttribute("q", "");
        model.addAttribute("selectedAuthor", "");
        model.addAttribute("selectedGenre", "");
        model.addAttribute("selectedPublisher", "");
        model.addAttribute("sort", "");
        model.addAttribute("length", "");
        model.addAttribute("inStockOnly", false);

        // dropdown options
        model.addAttribute("authors", bookService.getAllAuthors());
        model.addAttribute("genres", bookService.getAllGenres());
        model.addAttribute("publishers", bookService.getAllPublishers());

        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "q", required = false) String q,
                         @RequestParam(value = "author", required = false) String author,
                         @RequestParam(value = "genre", required = false) String genre,
                         @RequestParam(value = "publisher", required = false) String publisher,
                         @RequestParam(value = "sort", required = false) String sort,
                         @RequestParam(value = "length", required = false) String length,
                         @RequestParam(value = "inStockOnly", required = false) Boolean inStockOnly,
                         Model model,
                         HttpSession session) {

        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        List<Book> books = bookService.searchAndFilter(q, author, genre, publisher);
        books = bookService.applyLengthAndInventoryFilters(books, length, inStockOnly);
        books = bookService.applySorting(books, sort);

        if (books.isEmpty() && q != null && !q.isBlank()) {
            List<String> suggestions = bookService.suggestSimilarTitles(q);

            if (!suggestions.isEmpty()) {
                model.addAttribute("query", q);
                model.addAttribute("suggestions", suggestions);
                return "did_you_mean";
            }
        }

        model.addAttribute("books", books);

        model.addAttribute("q", q == null ? "" : q);
        model.addAttribute("selectedAuthor", author == null ? "" : author);
        model.addAttribute("selectedGenre", genre == null ? "" : genre);
        model.addAttribute("selectedPublisher", publisher == null ? "" : publisher);

        // Dropdown options
        model.addAttribute("authors", bookService.getAllAuthors());
        model.addAttribute("genres", bookService.getAllGenres());
        model.addAttribute("publishers", bookService.getAllPublishers());
        model.addAttribute("sort", sort);
        model.addAttribute("length", length == null ? "" : length);
        model.addAttribute("inStockOnly", inStockOnly != null && inStockOnly);

        return "index";
    }

}
