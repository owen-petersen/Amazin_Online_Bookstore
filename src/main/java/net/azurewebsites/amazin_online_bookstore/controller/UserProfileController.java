package net.azurewebsites.amazin_online_bookstore.controller;

import jakarta.servlet.http.HttpSession;
import net.azurewebsites.amazin_online_bookstore.entity.Person;
import net.azurewebsites.amazin_online_bookstore.entity.Purchase;
import net.azurewebsites.amazin_online_bookstore.repository.PersonRepository;
import net.azurewebsites.amazin_online_bookstore.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

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
    private PurchaseRepository purchaseRepository;

    @Autowired
    public UserProfileController(PersonRepository personRepository,  PurchaseRepository purchaseRepository) {
        this.personRepository = personRepository;
        this.purchaseRepository = purchaseRepository;
    }

    private String getSessionUsername(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return null;
    }

    @GetMapping("/")
    public String defaultPage(HttpSession session, Model model) {
        String x = getSessionUsername(session);
        if (x != null) return x;
        String username = (String) session.getAttribute("username");
        Person user = personRepository.findByUsername(username);
        model.addAttribute("user", user); // To add the current user
        return "profile/profile-page";
    }

    @GetMapping("/profile-page")
    public String profilePage(HttpSession session, Model model) {
        String x = getSessionUsername(session);
        if (x != null) return x;
        String username = (String) session.getAttribute("username");
        Person user = personRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "profile/profile-page";
    }

    @GetMapping("/purchase-history")
    public String purchaseHistory(HttpSession session, Model model) {
        String x = getSessionUsername(session);
        if (x != null) return x;
        String username = (String) session.getAttribute("username");
        Person user = personRepository.findByUsername(username);
        List<Purchase> purchases = purchaseRepository.findByBuyerOrderByDateTimeAsc(user);
        model.addAttribute("user", user);
        model.addAttribute("purchases", purchases);
        return "profile/purchase-history";
    }

    @GetMapping("/account-details")
    public String accountDetails(HttpSession session, Model model) {
        String x = getSessionUsername(session);
        if (x != null) return x;
        String username = (String) session.getAttribute("username");
        Person user = personRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "profile/account-details";
    }
}
