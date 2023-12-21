package com.bookstore.controller;

import com.bookstore.model.enums.ResponseMessage;
import com.bookstore.model.response.GenericResponse;
import com.bookstore.repository.GenreRepository;
import com.bookstore.service.GenreService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Genre Endpoints", description = "Endpoints for viewing available Genres")
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/{genreId}")
    public ResponseEntity<Object> getGenreById(@PathVariable Long genreId){
        try {
            return ResponseEntity.ok(GenericResponse.builder()
                    .message(ResponseMessage.SUCCESS)
                    .responseData(genreService.loadGenreById(genreId))
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

    @GetMapping("/")
    public ResponseEntity<Object> getAllGenres(){
        try {
            return ResponseEntity.ok(GenericResponse.builder()
                    .message(ResponseMessage.SUCCESS)
                    .responseData(genreService.loadAllGenres())
                    .build());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }
}
