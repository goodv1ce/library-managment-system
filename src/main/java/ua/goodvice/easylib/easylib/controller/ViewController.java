package ua.goodvice.easylib.easylib.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.goodvice.easylib.easylib.communicator.RestBookCommunicator;
import ua.goodvice.easylib.easylib.communicator.RestUserCommunicator;
import ua.goodvice.easylib.easylib.entity.Book;
import ua.goodvice.easylib.easylib.security.AuthenticationRequest;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ViewController {
    private final RestBookCommunicator restBookCommunicator;
    private final RestUserCommunicator restUserCommunicator;

    @GetMapping("/")
    public String showStartPage() {
        return "index";
    }

    @GetMapping("/catalogue")
    public String showBookCatalogue(Model model) {
        model.addAttribute("bookList", restBookCommunicator.getAllBooks());
        return "catalogue";
    }

    @GetMapping("/donate")
    public String showDonationBookPage(Model model) {
        model.addAttribute("book", new Book());
        return "book-donation-page";
    }

    @PostMapping("/donate")
    public String addBookAndRedirect(@ModelAttribute Book book) throws JsonProcessingException {
        restBookCommunicator.addBook(book);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("authenticationRequest", new AuthenticationRequest());
        return "login-page";
    }

    @PostMapping("/login")
    public String loginAndRedirect(@ModelAttribute AuthenticationRequest authenticationRequest) throws JsonProcessingException {
        restUserCommunicator.login(authenticationRequest);
        return "redirect:/";
    }
}
