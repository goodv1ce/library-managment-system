package ua.goodvice.easylib.easylib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goodvice.easylib.easylib.config.JwtService;
import ua.goodvice.easylib.easylib.dao.UserBookRelationRepository;
import ua.goodvice.easylib.easylib.dao.UserRepository;
import ua.goodvice.easylib.easylib.entity.UserBookRelation;

@Service
@RequiredArgsConstructor
public class UserBookRelationService {
    private final UserBookRelationRepository userBookRelationRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;


    public void addRelation(String jwt, int bookId) {
        String userEmail = jwtService.extractUsername(jwt);
        int userId = userRepository.findByEmail(userEmail).orElseThrow().getId();
        userBookRelationRepository.save(UserBookRelation.builder()
                .userId(userId)
                .bookId(bookId)
                .build());
    }
}
