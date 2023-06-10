package ua.goodvice.easylib.easylib.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goodvice.easylib.easylib.entity.UserBookRelation;

public interface UserBookRelationRepository extends JpaRepository<UserBookRelation, Integer> {
}
