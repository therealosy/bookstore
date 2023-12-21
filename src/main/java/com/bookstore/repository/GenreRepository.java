package com.bookstore.repository;

import com.bookstore.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Collection<Genre> findAllByGenreTitle(String genreTitle);
}
