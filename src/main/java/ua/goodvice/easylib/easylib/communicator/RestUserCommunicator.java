package ua.goodvice.easylib.easylib.communicator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.goodvice.easylib.easylib.security.AuthenticationRequest;

@Component
@RequiredArgsConstructor
public class RestUserCommunicator {
    private final RestTemplate restTemplate;

    public void login(AuthenticationRequest credentials) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper mapper = new ObjectMapper();
        String postDataJSON = mapper.writeValueAsString(credentials);

        HttpEntity<String> request = new HttpEntity<>(postDataJSON, headers);
        String response = restTemplate.postForObject("http://localhost:8080/api/auth/authenticate", request, String.class);
        System.out.println(response);
    }
}
