package ua.goodvice.easylib.easylib.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goodvice.easylib.easylib.entity.Book;
import ua.goodvice.easylib.easylib.entity.UserBookRelation;

import java.util.List;

public interface UserBookRelationRepository extends JpaRepository<UserBookRelation, Integer> {
    List<UserBookRelation> findAllByUserId(int userId);

    boolean existsByBookIdAndUserId(int bookId, int userId);
}
