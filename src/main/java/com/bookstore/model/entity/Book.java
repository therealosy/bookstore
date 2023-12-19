package com.bookstore.model.entity;

import com.bookstore.util.constraint.ISBNConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @ISBNConstraint
    private String isbn;


    @ManyToMany(mappedBy = "books")
    @JsonIgnoreProperties("books")
    private Set<Author> authors;

    @ManyToMany(mappedBy = "books")
    @JsonIgnoreProperties("books")
    private Set<Genre> genres;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Integer yearPublished;

    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private Set<Cart> carts;
}
