package ua.goodvice.easylib.easylib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goodvice.easylib.easylib.dao.BookRepository;
import ua.goodvice.easylib.easylib.entity.Book;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    
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
