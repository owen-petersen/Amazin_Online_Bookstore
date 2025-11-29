package net.azurewebsites.amazin_online_bookstore.controller;

import net.azurewebsites.amazin_online_bookstore.entity.Person;
import net.azurewebsites.amazin_online_bookstore.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * The UserProfileController class manages request for information and pages unique to the user.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-29
 */
@Controller
@RequestMapping("/profile")
public class UserProfileController {
    private PersonRepository personRepository;

    @Autowired
    public UserProfileController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public String defaultPage(@SessionAttribute(value = "username") String username, Model model) {
        Person person = personRepository.findByUsername(username);
        model.addAttribute("user", person); // To add the current user
        return "profile/profile-page";
    }

    @GetMapping("/profile-page")
    public String profilePage(@SessionAttribute(value = "username") String username, Model model) {
        Person person = personRepository.findByUsername(username);
        model.addAttribute("user", person);
        return "profile/profile-page";
    }

    @GetMapping("/purchase-history")
    public String purchaseHistory(@SessionAttribute(value = "username") String username, Model model) {
        Person person = personRepository.findByUsername(username);
        model.addAttribute("user", person);
        return "profile/purchase-history";
    }

    @GetMapping("/account-details")
    public String accountDetails(@SessionAttribute(value = "username") String username, Model model) {
        Person person = personRepository.findByUsername(username);
        model.addAttribute("user", person);
        return "profile/account-details";
    }
}
