package com.bookstore.repository;

import com.bookstore.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT u FROM Author u WHERE firstName LIKE '%?1%' OR lastName LIKE '%?1%'")
    Collection<Author> searchAuthorByName(String name);

    Collection<Author> findAllByFirstNameOrLastName(String firstName, String lastName);
}
