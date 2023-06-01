package ua.goodvice.easylib.easylib.communicator;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.goodvice.easylib.easylib.security.AuthenticationRequest;
import ua.goodvice.easylib.easylib.security.AuthenticationResponse;
import ua.goodvice.easylib.easylib.security.RegisterRequest;

@Component
@RequiredArgsConstructor
public class RestUserCommunicator {
    private final RestTemplate restTemplate;

    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest authenticationRequest) {
        return restTemplate.postForEntity("http://localhost:8080/api/auth/authenticate",
                authenticationRequest,
                AuthenticationResponse.class);
    }

    public ResponseEntity<AuthenticationResponse> register(RegisterRequest registerRequest) {
        return restTemplate.postForEntity("http://localhost:8080/api/auth/register",
                registerRequest,
                AuthenticationResponse.class);
    }
}
