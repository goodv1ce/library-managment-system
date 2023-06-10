package ua.goodvice.easylib.easylib.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Book Entity Class
 *
 * @author goodvice
 */
@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    @NotBlank(message = "Type is mandatory!")
    private String type;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory!")
    private String name;

    @Column(name = "author")
    @NotBlank(message = "Author is mandatory!")
    private String author;

    @Column(name = "book_condition")
    @NotBlank(message = "Condition is mandatory!")
    private String condition;

    @Column(name = "date_added")
    private String dateAdded;

    @Column(name = "genre")
    @NotBlank(message = "Genre is mandatory!")
    private String genre;

    public void setType(String type) {
        this.type = type.toLowerCase();
    }

    public void setCondition(String condition) {
        this.condition = condition.toLowerCase();
    }

    public void setGenre(String genre) {
        this.genre = genre.toLowerCase();
    }

    public Book(int id, String type, String name, String author, String condition, String dateAdded, String genre) {
        this.id = id;
        this.type = type.toLowerCase();
        this.name = name;
        this.author = author;
        this.condition = condition.toLowerCase();
        this.dateAdded = dateAdded;
        this.genre = genre.toLowerCase();
    }
}
