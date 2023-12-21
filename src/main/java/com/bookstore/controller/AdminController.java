package com.bookstore.controller;

import com.bookstore.model.entity.Author;
import com.bookstore.model.entity.Book;
import com.bookstore.model.entity.Genre;
import com.bookstore.model.entity.User;
import com.bookstore.model.enums.ResponseMessage;
import com.bookstore.model.request.SetBookAuthorsRequest;
import com.bookstore.model.request.SetBookGenresRequest;
import com.bookstore.model.request.UpdateBookRequest;
import com.bookstore.model.request.UpdateRoleRequest;
import com.bookstore.model.response.GenericResponse;
import com.bookstore.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Management Endpoints", description = "Endpoints for admin management of the BookStore API")
public class AdminController {

    private final UserService userService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final VerificationService verificationService;
    @PatchMapping("/users/{userId}/update-role")
    public ResponseEntity<GenericResponse> updateUserRole(@PathVariable Long userId, @RequestBody UpdateRoleRequest request){
        try {
            
            return ResponseEntity.accepted().body(
                    GenericResponse.builder()
                            .message(ResponseMessage.ACCEPTED)
                            .responseData(userService.updateUserRole(userId, request))
                            .build()
            );
        }catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<Collection<User>> getAllUsers(){
        return ResponseEntity.ok(userService.loadAllUsers());
    }

    @PostMapping("/books")
    public ResponseEntity<GenericResponse> addBook(@RequestBody Book book){
        try {
            return ResponseEntity.status(201).body(
                    GenericResponse.builder()
                            .message(ResponseMessage.CREATED)
                            .responseData(bookService.addBook(book))
                            .build());
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @PostMapping("/authors")
    public ResponseEntity<GenericResponse> addAuthor(@RequestBody Author author){
        try {
            return ResponseEntity.status(201).body(
                    GenericResponse.builder()
                            .message(ResponseMessage.CREATED)
                            .responseData(authorService.addAuthor(author))
                            .build());
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @PostMapping("/genres")
    public ResponseEntity<GenericResponse> addGenre(@RequestBody Genre genre){
        try {
            return ResponseEntity.status(201).body(GenericResponse.builder()
                    .message(ResponseMessage.CREATED)
                    .responseData(genreService.addGenre(genre))
                    .build());
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @PatchMapping("/books/{bookId}/update")
    public ResponseEntity<GenericResponse> updateBook(@PathVariable Long bookId, @RequestBody UpdateBookRequest request){
        try {
            return ResponseEntity.accepted().body(
                    GenericResponse.builder()
                            .message(ResponseMessage.ACCEPTED)
                            .responseData(bookService.updateBook(bookId, request))
                            .build()
            );
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(
                    GenericResponse.builder()
                            .message(e.getMessage())
                            .responseData(null)
                            .build()
            );
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build()
            );
        }
    }

    @PutMapping("/books/{bookId}/authors")
    public ResponseEntity<GenericResponse> updateBookAuthors(@PathVariable Long bookId, @RequestBody SetBookAuthorsRequest request){
        try {
            return ResponseEntity.accepted().body(GenericResponse.builder()
                    .message(ResponseMessage.ACCEPTED)
                    .responseData(bookService.setAuthors(bookId, request))
                    .build()
            );
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(
                    GenericResponse.builder()
                            .message(e.getMessage())
                            .responseData(null)
                            .build()
            );
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @PutMapping("/books/{bookId}/genres")
    public ResponseEntity<GenericResponse> updateBookAuthors(@PathVariable Long bookId, @RequestBody SetBookGenresRequest request){
        try {
            return ResponseEntity.accepted().body(
                    GenericResponse.builder()
                            .message(ResponseMessage.ACCEPTED)
                            .responseData(bookService.setGenres(bookId, request))
                            .build());
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(
                    GenericResponse.builder()
                            .message(e.getMessage())
                            .responseData(null)
                            .build()
            );
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @PatchMapping("/authors/{authorId}/update")
    public ResponseEntity<GenericResponse> updateAuthor(@PathVariable Long authorId, @RequestBody Author author){
        try {
            return ResponseEntity.accepted().body(
                    GenericResponse.builder()
                    .message(ResponseMessage.ACCEPTED)
                    .responseData(authorService.updateAuthor(authorId, author))
                    .build()
            );
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(
                    GenericResponse.builder()
                            .message(e.getMessage())
                            .responseData(null)
                            .build()
            );
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @PatchMapping("/genres/{genreId}/update")
    public ResponseEntity<GenericResponse> updateGenre(@PathVariable Long genreId, @RequestBody Genre genre){
        try {
            return ResponseEntity.accepted().body(
                    GenericResponse.builder()
                    .message(ResponseMessage.ACCEPTED)
                    .responseData(genreService.updateGenre(genreId, genre))
                    .build());
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(
                    GenericResponse.builder()
                            .message(e.getMessage())
                            .responseData(null)
                            .build()
            );
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }
}
