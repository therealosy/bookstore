package com.bookstore.model.entity;

import com.bookstore.util.constraint.ISBNConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @ISBNConstraint
    private String isbn;

    @ManyToMany
    @JoinTable(name="bookAuthors",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "authorId")
    )
    private Set<Author> authors;


    @ManyToMany
    @JoinTable(name="bookGenres",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "genreId")
    )
    private Set<Genre> genres;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Integer yearPublished;

    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private Set<Cart> carts;

    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private Set<Order> orders;
}
