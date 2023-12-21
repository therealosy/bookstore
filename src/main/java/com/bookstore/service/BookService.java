package com.bookstore.service;

import com.bookstore.model.entity.Author;
import com.bookstore.model.entity.Book;
import com.bookstore.model.entity.Genre;
import com.bookstore.model.request.SetBookAuthorsRequest;
import com.bookstore.model.request.SetBookGenresRequest;
import com.bookstore.model.request.UpdateBookRequest;
import com.bookstore.model.response.UpdateResponse;
import com.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    public Collection<Book> loadAllBooks() {
        return bookRepository.findAll();
    }

    public Book loadBookById(Long id) {
        log.info("Fetching book with id: {}", id);

        Book book = bookRepository.findById(id).orElseThrow();

        log.info("Found book: {}", book);

        return book;
    }

    public Book updateBook(Long id, @NotNull UpdateBookRequest request) {

        Book updatedBook = bookRepository.findById(id).orElseThrow();

        updatedBook.setTitle(request.getTitle());
        updatedBook.setPrice(request.getPrice());
        updatedBook.setYearPublished(request.getYearPublished());

        return updatedBook;
    }

    public UpdateResponse setAuthors(Long id, @NotNull SetBookAuthorsRequest request){

        Set<Author> authors = new HashSet<>(authorService.loadAuthorsByIds(request.getAuthorIds()));
        if (authors.isEmpty()){
            throw new NoSuchElementException("No Authors found");
        }

        log.info("Adding authors: {}", authors);

        Book book = bookRepository.findById(id).orElseThrow();
        book.setAuthors(authors);

        log.info("Added authors to book. Book is now: {}", book);

        bookRepository.save(book);

        return UpdateResponse.builder().message("Successfully Set Authors").build();
    }

    public UpdateResponse setGenres(Long id, @NotNull SetBookGenresRequest request){

        Set<Genre> genres = new HashSet<>(genreService.loadGenreByIds(request.getGenreIds()));
        if (genres.isEmpty()){
            throw new NoSuchElementException("No Genre found");
        }

        Book book = bookRepository.findById(id).orElseThrow();
        book.setGenres(genres);

        bookRepository.save(book);

        return UpdateResponse.builder().message("Successfully Set Authors").build();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Collection<Book> loadAllBooksByTitle(String title) {
        return bookRepository.findAllByTitle(title);
    }

    public Collection<Book> loadAllBooksByAuthor(String authorName){
        Collection<Author> authors = authorService.loadAuthorsByFirstNameOrLastName(authorName);

        return authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .toList();
    }

    public Collection<Book> loadAllBooksByGenre(String genreTitle){
        Collection<Genre> genres = genreService.loadGenresByTitle(genreTitle);

        return genres.stream()
                .flatMap(genre -> genre.getBooks().stream())
                .toList();
    }
    public Collection<Book> loadAllBooksByYear(Integer year){return  bookRepository.findAllByYearPublished(year);}

    public Collection<Book> searchBooksByTitle(String title){return bookRepository.searchByTitle(title);}
}
