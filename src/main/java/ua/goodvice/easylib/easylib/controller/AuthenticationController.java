package ua.goodvice.easylib.easylib.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.goodvice.easylib.easylib.config.JwtService;
import ua.goodvice.easylib.easylib.security.AuthenticationRequest;
import ua.goodvice.easylib.easylib.security.AuthenticationResponse;
import ua.goodvice.easylib.easylib.security.AuthenticationService;
import ua.goodvice.easylib.easylib.security.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        AuthenticationResponse response = service.register(request);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, jwtService.generateJwtTokenCookie(response.getToken()))
                .header(HttpHeaders.AUTHORIZATION, response.getToken())
                .body(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse response = service.authenticate(request);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, jwtService.generateJwtTokenCookie(response.getToken()))
                .header(HttpHeaders.AUTHORIZATION, response.getToken())
                .body(response);
    }
}
