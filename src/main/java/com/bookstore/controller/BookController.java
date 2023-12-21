package com.bookstore.controller;

import com.bookstore.model.enums.ResponseMessage;
import com.bookstore.model.response.GenericResponse;
import com.bookstore.service.AuthorService;
import com.bookstore.service.BookService;
import com.bookstore.service.GenreService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Book Endpoints", description = "Endpoints for Viewing and Searching for Books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;


    @GetMapping("/")
    private ResponseEntity<GenericResponse> getAllBooks(){
        try {
            return ResponseEntity.ok(GenericResponse.builder()
                    .message(ResponseMessage.SUCCESS)
                    .responseData(bookService.loadAllBooks())
                    .build());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<GenericResponse> getBookById(@PathVariable Long bookId){
        try {
            return ResponseEntity.ok(GenericResponse.builder()
                    .message(ResponseMessage.SUCCESS)
                    .responseData(bookService.loadBookById(bookId))
                    .build());
        }catch (NoSuchElementException e){
            return ResponseEntity.status(404).body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<GenericResponse> searchBooks(@RequestParam(required = false) String title,
                                              @RequestParam(required = false) String author,
                                              @RequestParam(required = false) String genre,
                                              @RequestParam(required = false) Integer year){
        try {
            if (title != null)
                return ResponseEntity.ok(GenericResponse.builder()
                        .message(ResponseMessage.SUCCESS)
                        .responseData(bookService.searchBooksByTitle(title))
                        .build());
            if (author != null)
                return ResponseEntity.ok(GenericResponse.builder()
                        .message(ResponseMessage.SUCCESS)
                        .responseData(bookService.loadAllBooksByAuthor(author))
                        .build());
            if(genre != null)
                return ResponseEntity.ok(GenericResponse.builder()
                        .message(ResponseMessage.SUCCESS)
                        .responseData(bookService.loadAllBooksByGenre(genre))
                        .build());
            if (year != null)
                return ResponseEntity.ok(GenericResponse.builder()
                        .message(ResponseMessage.SUCCESS)
                        .responseData(bookService.loadAllBooksByYear(year))
                        .build());

            return ResponseEntity.badRequest().body(GenericResponse.builder()
                    .message("No parameters supplied")
                    .responseData(null)
                    .build());

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }
}
