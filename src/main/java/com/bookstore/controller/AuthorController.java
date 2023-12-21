package com.bookstore.controller;

import com.bookstore.model.enums.ResponseMessage;
import com.bookstore.model.response.GenericResponse;
import com.bookstore.service.AuthorService;
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
@RequestMapping("/authors")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Author Endpoints", description = "Endpoints for viewing Authors")
public class AuthorController {

    private final AuthorService authorService;
    @GetMapping("/{authorId}")
    public ResponseEntity<GenericResponse> getAuthorById(@PathVariable Long authorId){
        try {
            return ResponseEntity.ok(GenericResponse.builder()
                            .message(ResponseMessage.SUCCESS)
                            .responseData(authorService.loadAuthorById(authorId))
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
    public ResponseEntity<GenericResponse> getAllAuthors(){
        try {
            return ResponseEntity.ok(GenericResponse.builder()
                    .message(ResponseMessage.SUCCESS)
                    .responseData(authorService.loadAllAuthors())
                    .build());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(GenericResponse.builder()
                    .message(e.getMessage())
                    .responseData(null)
                    .build());
        }
    }
}
