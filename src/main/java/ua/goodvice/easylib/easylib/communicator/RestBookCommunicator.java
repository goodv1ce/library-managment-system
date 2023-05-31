package ua.goodvice.easylib.easylib.communicator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.goodvice.easylib.easylib.entity.Book;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestBookCommunicator {
    private final RestTemplate restTemplate;

    public List<Book> getAllBooks() {
        ResponseEntity<List<Book>> responseEntity = restTemplate.exchange("http://localhost:8080/api/books"
                , HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
        return responseEntity.getBody();
    }

    public Book getBookById(int id) {
        return restTemplate.getForObject("http://localhost:8080/api/books/" + id, Book.class);
    }

    /**
     * Method creates HTTP request and puts book object as JSON in the request body. After that it sends request to the
     * REST controller which adds entity to the table
     *
     * @param book entity class that contains information about the book
     */
    public void addBook(Book book) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper mapper = new ObjectMapper();
        String postDataJSON = mapper.writeValueAsString(book);

        HttpEntity<String> request = new HttpEntity<>(postDataJSON, headers);
        String response = restTemplate.postForObject("http://localhost:8080/api/books", request, String.class);
    }

}
