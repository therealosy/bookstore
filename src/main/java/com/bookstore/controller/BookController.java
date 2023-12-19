package com.bookstore.controller;

import com.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private BookService bookService;
}
