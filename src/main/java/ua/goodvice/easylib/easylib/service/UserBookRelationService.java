package ua.goodvice.easylib.easylib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goodvice.easylib.easylib.config.JwtService;
import ua.goodvice.easylib.easylib.dao.UserBookRelationRepository;
import ua.goodvice.easylib.easylib.dao.UserRepository;
import ua.goodvice.easylib.easylib.entity.Book;
import ua.goodvice.easylib.easylib.entity.UserBookRelation;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBookRelationService {
    private final UserBookRelationRepository userBookRelationRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BookService bookService;


    public void addRelation(String jwt, int bookId) {
        String userEmail = jwtService.extractUsername(jwt);
        int userId = userRepository.findByEmail(userEmail).orElseThrow().getId();
        if (!userBookRelationRepository.existsByBookIdAndUserId(bookId, userId)) {
            userBookRelationRepository.save(UserBookRelation.builder()
                    .userId(userId)
                    .bookId(bookId)
                    .build());
        }
    }

    public List<Book> getIssuedBooks(String jwt) {
        String userEmail = jwtService.extractUsername(jwt);
        int userId = userRepository.findByEmail(userEmail).orElseThrow().getId();
        List<UserBookRelation> relations = userBookRelationRepository.findAllByUserId(userId);
        List<Book> bookList = new ArrayList<>();
        for (UserBookRelation relation : relations) {
            bookList.add(bookService.getBook(relation.getBookId()));
        }
        return bookList;
    }


}
