package ua.goodvice.easylib.easylib.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ua.goodvice.easylib.easylib.config.JwtService;
import ua.goodvice.easylib.easylib.entity.Book;
import ua.goodvice.easylib.easylib.service.BookService;
import ua.goodvice.easylib.easylib.service.UserBookRelationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * REST controller
 *
 * @author goodvice
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RESTController {
    private final BookService bookService;
    private final UserBookRelationService userBookRelationService;

    /**
     * Getting all books
     *
     * @return list of book entities
     */
    @GetMapping("/books")
    public List<Book> showAllBooks() {
        return bookService.getAllBooks();
    }

    /**
     * Getting book by id
     *
     * @param id book id
     * @return book entity
     */
    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable int id) {
        return bookService.getBook(id);
    }

    /**
     * Adding a new book
     *
     * @param book book entity from request body
     * @return added book entity
     */
    @PostMapping("/books")
    public Book addNewBook(@Valid @RequestBody Book book) {
        bookService.saveBook(book);
        return book;
    }

    /**
     * Updating the book
     *
     * @param book book entity from body
     * @return updated book entity
     */
    @PutMapping("/books")
    public Book updateBook(@Valid @RequestBody Book book) {
        bookService.saveBook(book);
        return book;
    }

    /**
     * Deleting book
     *
     * @param id book id
     * @return message about success deleting
     */
    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "Book with ID = " + id + " was deleted!";
    }

    @PostMapping("/issue/{bookId}")
    public String issueBook(@PathVariable int bookId,
                            @CookieValue(name = "user-id", defaultValue = "null") String jwt) {
        if (Objects.equals(jwt, "null")) {
            throw new AccessDeniedException("Bad JSON Web Token");
        }
        userBookRelationService.addRelation(jwt, bookId);
        return "Relation was added!";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
