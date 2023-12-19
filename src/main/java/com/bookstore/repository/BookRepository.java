package com.bookstore.repository;

import com.bookstore.model.entity.Author;
import com.bookstore.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Year;
import java.util.Collection;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT u FROM Books u WHERE Title LIKE '%?1%'")
    Collection<Book> searchByTitle(String title);

    Optional<Book> findByTitle(String title);

    Collection<Book> findAllByYear(Integer year);
}
