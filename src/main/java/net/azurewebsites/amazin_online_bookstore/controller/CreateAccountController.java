package net.azurewebsites.amazin_online_bookstore.controller;

import net.azurewebsites.amazin_online_bookstore.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The CreateAccountController class
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-29
 */
@Controller
@RequestMapping("/create-account")
public class CreateAccountController {

    public CreateAccountController() {}

    @GetMapping("")
    public String getCreateAccountPage() {
        return "create-account";
    }

    @PostMapping("")
    public String createAccount(@RequestParam("username") String username,
                                @RequestParam("password") String password) {
        // Use person service to check if username exists
        // If it doesn't, create and save a new user person account
        return "redirect:/login";
    }
}
