package ua.goodvice.easylib.easylib.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goodvice.easylib.easylib.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Method returns User instance by email
     *
     * @param email user's email
     * @return user by email
     */
    Optional<User> findByEmail(String email);

    boolean existsUserByEmail(String email);
}
