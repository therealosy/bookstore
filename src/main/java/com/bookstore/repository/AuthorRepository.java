package com.bookstore.repository;

import com.bookstore.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT u FROM Author u WHERE firstName LIKE %:name% OR lastName LIKE %:name%")
    Collection<Author> searchAuthorByName(@Param("name") String name);

    Collection<Author> findAllByFirstNameOrLastName(String firstName, String lastName);
}
