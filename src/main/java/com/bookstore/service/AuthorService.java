package com.bookstore.service;

import com.bookstore.model.entity.Author;
import com.bookstore.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthorService{


    private final AuthorRepository authorRepository;


    public Collection<Author> getAllAuthors() {
        return authorRepository.findAll();
    }


    public Author loadAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow();
    }


    public Author updateAuthor(Long id, Author author) {
        Author updatedAuthor = authorRepository.findById(id).orElseThrow();

        updatedAuthor.setFirstName(author.getFirstName());
        updatedAuthor.setMiddleName(author.getMiddleName());
        updatedAuthor.setLastName(author.getLastName());

        return authorRepository.save(updatedAuthor);
    }


    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author loadAuthorByFirstNameOrLastName(String firstNameOrLastName) {
        return authorRepository.findByFirstNameOrLastName(firstNameOrLastName, firstNameOrLastName).orElseThrow();
    }
}
