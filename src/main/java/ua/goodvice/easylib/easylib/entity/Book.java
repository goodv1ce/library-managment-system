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
    @Column(name = "id")
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "book_condition")
    private String book_condition;
    @Column(name = "date_added")
    private String date_added;
    @Column(name = "genre")
    private String genre;
}
