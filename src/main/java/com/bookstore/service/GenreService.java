package com.bookstore.service;

import com.bookstore.model.entity.Genre;
import com.bookstore.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class GenreService{

    public final GenreRepository genreRepository;

    public Collection<Genre> getAllGenres() {
        return genreRepository.findAll();
    }


    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElseThrow();
    }


    public Genre updateGenre(Long id, Genre genre) {
        Genre updatedGenre = genreRepository.findById(id).orElseThrow();
        updatedGenre.setGenreTitle(genre.getGenreTitle());
        return genreRepository.save(updatedGenre);
    }


    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }


    public Genre getGenreByTitle(String genreTitle) {
        return genreRepository.findByTitle(genreTitle).orElseThrow();
    }
}
