package com.bookstore.service;

import com.bookstore.model.entity.Author;
import com.bookstore.model.entity.Book;
import com.bookstore.model.entity.Genre;
import com.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    public Collection<Book> loadAllBooks() {
        return bookRepository.findAll();
    }

    public Book loadBookById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public Book updateBook(Long id, Book book) {
        return null;
    }

    public void setAuthors(Long id, Set<Author> authors){
        Book book = bookRepository.findById(id).orElseThrow();
        book.setAuthors(authors);

        bookRepository.save(book);
    }

    public void setGenres(Long id, Set<Genre> genres){
        Book book = bookRepository.findById(id).orElseThrow();
        book.setGenres(genres);

        bookRepository.save(book);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book loadBookByTitle(String title) {
        return bookRepository.findByTitle(title).orElseThrow();
    }

    public Collection<Book> loadBookByYear(Integer year){return  bookRepository.findAllByYear(year);}

    public Collection<Book> searchBookByTitle(String title){return bookRepository.searchByTitle(title);}
}
