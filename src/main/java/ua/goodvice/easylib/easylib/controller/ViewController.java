package ua.goodvice.easylib.easylib.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.goodvice.easylib.easylib.communicator.RestBookCommunicator;
import ua.goodvice.easylib.easylib.communicator.RestUserCommunicator;
import ua.goodvice.easylib.easylib.entity.Book;
import ua.goodvice.easylib.easylib.security.AuthenticationRequest;
import ua.goodvice.easylib.easylib.security.AuthenticationResponse;

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
    public String showBookCatalogue(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("bookList", restBookCommunicator.getAllBooks(httpServletRequest));
        return "catalogue";
    }

    @GetMapping("/donate")
    public String showDonationBookPage(Model model) {
        model.addAttribute("book", new Book());
        return "book-donation-page";
    }

    @PostMapping("/donate")
    public String addBookAndRedirect(@ModelAttribute Book book, HttpServletRequest httpServletRequest)
            throws JsonProcessingException {
        restBookCommunicator.addBook(book, httpServletRequest);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("authenticationRequest", new AuthenticationRequest());
        return "login-page";
    }

    @PostMapping("/login")
    public String loginAndRedirect(@ModelAttribute AuthenticationRequest authenticationRequest,
                                   HttpServletResponse response) {
        ResponseEntity<AuthenticationResponse> responseEntity =
                restUserCommunicator.login(authenticationRequest);
        String cookies = (responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE));
        String jwt;
        if (cookies != null) {
            jwt = cookies.substring(cookies.indexOf("user-id=") + 8, cookies.indexOf(";"));
        } else {
            jwt = ""; // todo implement
        }
        response.addCookie(new Cookie("user-id", jwt));
        return "redirect:/";
    }
}
