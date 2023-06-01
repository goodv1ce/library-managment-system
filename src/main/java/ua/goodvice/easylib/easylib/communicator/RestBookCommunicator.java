package ua.goodvice.easylib.easylib.communicator;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.goodvice.easylib.easylib.entity.Book;
import ua.goodvice.easylib.easylib.util.RestAuthUtil;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestBookCommunicator {
    private final RestTemplate restTemplate;
    private final RestAuthUtil restAuthUtil;

    public List<Book> getAllBooks(HttpServletRequest httpServletRequest) {
        HttpHeaders headers = restAuthUtil.createHeaders(httpServletRequest);
        HttpEntity<String> restTemplateRequest = new HttpEntity<>(headers);
        ResponseEntity<List<Book>> response = restTemplate.exchange("http://localhost:8080/api/books", HttpMethod.GET,
                restTemplateRequest, new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    /**
     * Method creates HTTP request and puts book object as JSON in the request body. After that it sends request to the
     * REST controller which adds entity to the table
     *
     * @param book entity class that contains information about the book
     */
    public void addBook(Book book, HttpServletRequest httpServletRequest) {

        HttpHeaders headers = restAuthUtil.createHeaders(httpServletRequest);
        HttpEntity<Book> restTemplateRequest = new HttpEntity<>(book, headers);
        restTemplate.postForEntity("http://localhost:8080/api/books", restTemplateRequest, Book.class);

    }

}
