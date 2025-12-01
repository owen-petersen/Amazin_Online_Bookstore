package net.azurewebsites.amazin_online_bookstore.controller;

import jakarta.servlet.http.HttpSession;
import net.azurewebsites.amazin_online_bookstore.entity.Book;
import net.azurewebsites.amazin_online_bookstore.entity.Person;
import net.azurewebsites.amazin_online_bookstore.repository.PersonRepository;
import net.azurewebsites.amazin_online_bookstore.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/recommendations")
public class RecommendationsController {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonService personService;

    @GetMapping("")
    public String getRecommendations(HttpSession session, Model model) {
        List<Book> recommendedBooks;

        if  (session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        Optional<Person> person = personRepository.findByUsername(String.valueOf(session.getAttribute("username")));

        if (person.isEmpty()) {
            recommendedBooks = new ArrayList<>();
        } else {
            recommendedBooks = personService.getRecommendedBooks(person.get());
        }

        model.addAttribute("books",  recommendedBooks);

        return "recommendations";
    }
}
