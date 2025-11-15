package net.azurewebsites.amazin_online_bookstore;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping({"/login"})
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        Person person = personRepository.findByUsernameAndPassword(username, password);
        if (person != null) {
            session.setAttribute("userId", person.getId());
            session.setAttribute("username", person.getUsername());
            session.setAttribute("role", person.getRole());
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
    }



    @GetMapping("/welcome")
    public String welcome(@SessionAttribute(value = "username", required = false) String username,
                          Model model) {
        if (username == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", username);
        return "welcome";
    }


    @GetMapping("/index")
    public String showIndexPage(Model model) {
        return "index";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }


}
