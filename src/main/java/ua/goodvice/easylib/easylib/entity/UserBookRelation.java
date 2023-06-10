package ua.goodvice.easylib.easylib.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_book_relation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBookRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "book_id")
    private int bookId;
}
