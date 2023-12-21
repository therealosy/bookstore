package com.bookstore.repository;

import com.bookstore.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
    Collection<Book> searchByTitle(@Param("title") String title);

    Collection<Book> findAllByTitle(String title);

    Collection<Book> findAllByYearPublished(Integer yearPublished);
}
