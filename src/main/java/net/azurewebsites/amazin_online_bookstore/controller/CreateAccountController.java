package net.azurewebsites.amazin_online_bookstore.controller;

import net.azurewebsites.amazin_online_bookstore.entity.Person;
import net.azurewebsites.amazin_online_bookstore.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * The CreateAccountController class
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-29
 */
@Controller
@RequestMapping("/create-account")
public class CreateAccountController {
    private PersonService personService;

    @Autowired
    public CreateAccountController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    public String getCreateAccountPage() {
        return "create-account";
    }

    @PostMapping("")
    public String createAccount(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("firstname") String firstname,
                                @RequestParam("lastname") String lastname,
                                @RequestParam("email") String email,
                                RedirectAttributes redirectAttributes) {
        // Use person service to check if username exists
        // If it doesn't, create and save a new user person account
        if (personService.createAccount(username, password, firstname, lastname, email)) {
            return "redirect:/login";
        }

        redirectAttributes.addFlashAttribute("error", "Account could not be created. Missing information");
        return "redirect:/create-account";
    }
}
