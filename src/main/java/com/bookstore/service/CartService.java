package com.bookstore.service;

import com.bookstore.model.entity.Book;
import com.bookstore.model.entity.Cart;
import com.bookstore.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final BookService bookService;

    public Cart loadCartByUserId(Long cartUserId){
        return cartRepository.findById(cartUserId).orElseThrow();
    }

    public Cart addBookToCart(Long cartUserId, Long bookId){
        Cart currentCart = cartRepository.findById(cartUserId).orElseThrow();
        Book book = bookService.loadBookById(bookId);
        Set<Book> books = currentCart.getBooks();
        books.add(book);
        currentCart.setBooks(books);

        return cartRepository.save(currentCart);
    }

    public Cart createCart(Long cartUserId){
        Cart newCart = Cart.builder().cartUserId(cartUserId).build();
        return cartRepository.save(newCart);
    }

    public void emptyCart(Long cartUserId){
        cartRepository.deleteById(cartUserId);
    }

    public Cart removeBookFromCart(Long cartUserId, Long bookId){
        Cart currentCart = cartRepository.findById(cartUserId).orElseThrow();
        Book book = bookService.loadBookById(bookId);
        Set<Book> books = currentCart.getBooks();
        books.remove(book);
        currentCart.setBooks(books);

        return cartRepository.save(currentCart);
    }

}
