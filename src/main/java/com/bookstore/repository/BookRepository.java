package com.bookstore.repository;

import com.bookstore.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT u FROM Book u WHERE title LIKE '%?1%'")
    Collection<Book> searchByTitle(String title);

    Collection<Book> findAllByTitle(String title);

    Collection<Book> findAllByYearPublished(Integer yearPublished);
}
