package ua.goodvice.easylib.easylib.service;

import ua.goodvice.easylib.easylib.entity.Book;

import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();

    public void saveBook(Book book);

    public Book getBook(int id);

    public void deleteBook(int id);
}
