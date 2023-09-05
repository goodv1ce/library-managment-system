package ua.goodvice.easylib.easylib.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    /**
     * User registration endpoint
     *
     * @param request JSON from request body
     * @return response with status 200 with JWT in cookie in header
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        AuthenticationResponse response = service.register(request);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, jwtService.generateJwtTokenCookie(response.getToken()))
                .build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse response = service.authenticate(request);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, jwtService.generateJwtTokenCookie(response.getToken()))
                .body(response);
    }
}
