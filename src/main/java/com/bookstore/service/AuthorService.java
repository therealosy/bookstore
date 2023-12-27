package com.bookstore.service;

import com.bookstore.model.entity.Author;
import com.bookstore.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService{


    private final AuthorRepository authorRepository;


    public Collection<Author> loadAllAuthors() {
        return authorRepository.findAll();
    }


    public Author loadAuthorById(Long id) {
        log.info("Getting author with ID: {}", id);
        Author author = authorRepository.findById(id).orElseThrow();

        log.info("Got Author: {}", author);

        return author;
    }


    public Author updateAuthor(Long id, @NotNull Author author) {
        Author updatedAuthor = authorRepository.findById(id).orElseThrow();

        updatedAuthor.setFirstName(author.getFirstName());
        updatedAuthor.setLastName(author.getLastName());
        updatedAuthor.setPenName(author.getPenName());

        return authorRepository.save(updatedAuthor);
    }


    public Author addAuthor(Author author) {
        if (author.getPenName() == null || author.getPenName().isBlank())
            author.setPenName(String.join(" ", author.getFirstName(), author.getLastName()));
        log.info("Saving author to DB: {}", author);

        return authorRepository.save(author);
    }

    public Collection<Author> loadAuthorByName(String name) {
        return authorRepository.searchAuthorByName(name);
    }

    public Collection<Author> loadAuthorsByIds(Collection<Long> authorIds){
        return authorRepository.findAllById(authorIds);
    }
}
