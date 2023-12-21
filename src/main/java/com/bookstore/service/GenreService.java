package com.bookstore.service;

import com.bookstore.model.entity.Genre;
import com.bookstore.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class GenreService{

    public final GenreRepository genreRepository;

    public Collection<Genre> loadAllGenres() {
        return genreRepository.findAll();
    }


    public Genre loadGenreById(Long id) {
        return genreRepository.findById(id).orElseThrow();
    }


    public Genre updateGenre(Long id, @NotNull Genre genre) {
        Genre updatedGenre = genreRepository.findById(id).orElseThrow();
        updatedGenre.setGenreTitle(genre.getGenreTitle());
        return genreRepository.save(updatedGenre);
    }


    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }


    public Collection<Genre> loadGenresByTitle(String genreTitle) {
        return genreRepository.findAllByGenreTitle(genreTitle);
    }

    public Collection<Genre> loadGenreByIds(Collection<Long> genreIds){
        return genreRepository.findAllById(genreIds);
    }

}
