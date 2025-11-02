package net.azurewebsites.amazin_online_bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // TODO: Redirect here after login once login flow is updated.
    // (home page) instead of welcome.html.

    @Autowired
    private BookRepository bookRepository;
    // TODO: Use BookService instead of BookRepository after merge.

    @GetMapping("/")
    public String home(Model model) {
        // TODO: Update to use BookService.getAllBooks() once available.
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }
}
