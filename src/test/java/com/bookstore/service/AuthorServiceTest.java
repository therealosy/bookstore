package com.bookstore.service;

import com.bookstore.model.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class AuthorServiceTest {
    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    @BeforeEach
    public void setUp(){
        authorRepository = mock(AuthorRepository.class);
        authorService = new AuthorService(authorRepository);
    }

    @Test
    public void testLoadAllAuthors(){
        List<Author> mockAuthors = new ArrayList<>();
        mockAuthors.add(Author.builder().authorId(1L).firstName("Author 1").lastName("Author 1").penName("Author 1").books(new ArrayList<>()).build());
        mockAuthors.add(Author.builder().authorId(2L).firstName("Author 2").lastName("Author 2").penName("Author 2").books(new ArrayList<>()).build());

        when(authorRepository.findAll()).thenReturn(mockAuthors);

        List<Author> authors = (List<Author>) authorService.loadAllAuthors();
        assertEquals(2, authors.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void testLoadAuthorById() {
        Author author = new Author();
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        Author result = authorService.loadAuthorById(1L);
        assertEquals(author, result);
        verify(authorRepository, times(1)).findById(1L);
    }
}
