package ua.goodvice.easylib.easylib.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.goodvice.easylib.easylib.communicator.RestBookCommunicator;
import ua.goodvice.easylib.easylib.communicator.RestUserCommunicator;
import ua.goodvice.easylib.easylib.entity.Book;
import ua.goodvice.easylib.easylib.security.AuthenticationRequest;
import ua.goodvice.easylib.easylib.security.AuthenticationResponse;
import ua.goodvice.easylib.easylib.security.RegisterRequest;

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
    public String addBookAndRedirect(@ModelAttribute Book book, HttpServletRequest httpServletRequest) {
        restBookCommunicator.addBook(book, httpServletRequest);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("authenticationRequest", new AuthenticationRequest());
        model.addAttribute("registerRequest", new RegisterRequest());
        return "authentication";
    }

    @PostMapping("/login")
    public String loginAndRedirect(@ModelAttribute AuthenticationRequest authenticationRequest,
                                   HttpServletResponse httpServletResponse) {
        ResponseEntity<AuthenticationResponse> responseEntity =
                restUserCommunicator.login(authenticationRequest);
        AuthenticationResponse authenticationResponse = responseEntity.getBody();
        assert authenticationResponse != null;
        String jwt = authenticationResponse.getToken();
        Cookie jwtCookie = new Cookie("user-id", jwt);
        httpServletResponse.addCookie(jwtCookie);
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest registerRequest,
                           HttpServletResponse httpServletResponse) {
        ResponseEntity<AuthenticationResponse> responseEntity =
                restUserCommunicator.register(registerRequest);
        AuthenticationResponse authenticationResponse = responseEntity.getBody();
        assert authenticationResponse != null;
        String jwt = authenticationResponse.getToken();
        Cookie jwtCookie = new Cookie("user-id", jwt);
        httpServletResponse.addCookie(jwtCookie);
        return "redirect:/";
    }
}
