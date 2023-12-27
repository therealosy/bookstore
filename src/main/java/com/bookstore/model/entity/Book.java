package com.bookstore.model.entity;

import com.bookstore.util.constraint.ISBNConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @ISBNConstraint
    private String isbn;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="bookAuthors",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "authorId")
    )
    private List<Author> authors;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="bookGenres",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "genreId")
    )
    private List<Genre> genres;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Integer yearPublished;

    @JsonIgnore
    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private Set<Cart> carts;

    @JsonIgnore
    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private Set<Order> orders;

    @Override
    public String toString(){
        return "Book{"
                +"title='" + this.title + "', "
                +"isbn='" + this.isbn + "', "
                +"yearPublished=" +this.yearPublished + ", "
                +"price=" + this.price + ", "
                +"genres=" + this.genres + ", "
                +"authors=" + this.authors
                +"' }";
    }
}
