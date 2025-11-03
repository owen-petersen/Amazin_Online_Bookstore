package net.azurewebsites.amazin_online_bookstore;

import jakarta.servlet.http.HttpSession;
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
    public String home(Model model, HttpSession session) {

        //If the user is not logged in, user should go to login page first
        //If the user is already logged in go to homepage
        if(session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        // TODO: Update to use BookService.getAllBooks() once available.
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }



}
