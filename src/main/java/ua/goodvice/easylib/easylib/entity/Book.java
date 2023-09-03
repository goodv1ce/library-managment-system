package ua.goodvice.easylib.easylib.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO class which represents a book
 * Lombok @Data annotation was used. This annotation set up setters and getters for every field
 *
 * @author goodvice
 * @version 1.0
 */
@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(name = "book_type")
    private String type;

    @Column(name = "book_genre")
    private String genre;

    @Column(name = "book_name")
    private String name;

    @Column(name = "book_author")
    private String author;

    @Column(name = "book_condition")
    private String condition;

    @Column(name = "book_add_date")
    private String dateAdded;
}
