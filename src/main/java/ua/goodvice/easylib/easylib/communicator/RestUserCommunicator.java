package ua.goodvice.easylib.easylib.communicator;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.goodvice.easylib.easylib.security.AuthenticationRequest;
import ua.goodvice.easylib.easylib.security.AuthenticationResponse;
import ua.goodvice.easylib.easylib.util.RestAuthUtil;

@Component
@RequiredArgsConstructor
public class RestUserCommunicator {
    private final RestTemplate restTemplate;
    private final RestAuthUtil restAuthUtil;

    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest authenticationRequest) {
        return restTemplate.postForEntity("http://localhost:8080/api/auth/authenticate",
                authenticationRequest,
                AuthenticationResponse.class);
    }
}
