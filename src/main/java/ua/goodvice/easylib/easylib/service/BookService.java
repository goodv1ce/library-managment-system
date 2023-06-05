package ua.goodvice.easylib.easylib.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goodvice.easylib.easylib.dao.BookRepository;
import ua.goodvice.easylib.easylib.entity.Book;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
@Data
public class BookService {
    private final BookRepository bookRepository;
    private List<String> bookConditions = new ArrayList<>(Arrays
            .asList("New", "Fine", "Good", "Fair", "Poor"));
    private List<String> bookGenres = new ArrayList<>(Arrays
            .asList("Fiction", "Non-fiction", "Poetry", "Drama", "Other"));
    private List<String> bookTypes = new ArrayList<>(Arrays
            .asList("Book", "Magazine", "Comics", "Encyclopaedia"));

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void saveBook(Book book) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        book.setDateAdded(dateFormatter.format(new Date()));
        bookRepository.save(book);
    }

    public Book getBook(int id) {
        Book book = null;
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isPresent()) {
            book = optional.get();
        }
        return book;
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
