package net.azurewebsites.amazin_online_bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The UserProfileController class manages request for information and pages unique to the user.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-11-28
 */
@Controller
@RequestMapping("/profile")
public class UserProfileController {

    public UserProfileController() {}

    @GetMapping("/")
    public String profilePage(Model model) {
//        model.addAttribute("user", null); // To add the current user
        return "profile/profile-page";
    }
}
