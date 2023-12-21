package com.bookstore.service;

import com.bookstore.model.entity.Author;
import com.bookstore.model.entity.Book;
import com.bookstore.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthorService{


    private final AuthorRepository authorRepository;


    public Collection<Author> loadAllAuthors() {
        return authorRepository.findAll();
    }


    public Author loadAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow();
    }


    public Author updateAuthor(Long id, @NotNull Author author) {
        Author updatedAuthor = authorRepository.findById(id).orElseThrow();

        updatedAuthor.setFirstName(author.getFirstName());
        updatedAuthor.setMiddleName(author.getMiddleName());
        updatedAuthor.setLastName(author.getLastName());

        return authorRepository.save(updatedAuthor);
    }


    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Collection<Author> loadAuthorsByFirstNameOrLastName(String firstNameOrLastName) {
        return authorRepository.findAllByFirstNameOrLastName(firstNameOrLastName, firstNameOrLastName);
    }

    public Collection<Author> loadAuthorsByIds(Collection<Long> authorIds){
        return authorRepository.findAllById(authorIds);
    }
}
