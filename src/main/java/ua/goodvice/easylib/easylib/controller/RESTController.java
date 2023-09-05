package ua.goodvice.easylib.easylib.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.goodvice.easylib.easylib.entity.Book;
import ua.goodvice.easylib.easylib.service.BookService;

import java.util.List;

/**
 * REST Controller
 * Contains methods that interact with database via entity classes
 *
 * @author goodvice
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RESTController {
    /**
     * Autowired book service class.
     * Contains main functionality of interacting with database
     */
    private final BookService bookService;

    /**
     * Returns all books that are in the database
     *
     * @return List of Book objects in JSON format
     */
    @GetMapping("/books")
    public List<Book> showAllBooks() {
        return bookService.getAllBooks();
    }

    /**
     * Returns book by id from database
     *
     * @param id book id
     * @return Information about the book in JSON format
     */
    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable int id) {
        return bookService.getBook(id);
    }

    /**
     * Adding a new book
     *
     * @param book Book POJO
     * @return JSON of the added book
     */
    @PostMapping("/books")
    public Book addNewBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return book;
    }

    /**
     * Updating information about the book in the database
     * New information takes from JSON in HTTP request body
     * Which book needs to be updated becomes known by the 'id' property
     *
     * @param book Book object
     * @return Book object in the JSON format after updating
     */
    @PutMapping("/books")
    public Book updateBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return book;
    }

    /**
     * Deleting book from database by id
     *
     * @param id book id that is needed to be deleted
     * @return String object with information about successful deleting book with some id
     */
    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "Book with ID = " + id + " was deleted!";
    }
}
