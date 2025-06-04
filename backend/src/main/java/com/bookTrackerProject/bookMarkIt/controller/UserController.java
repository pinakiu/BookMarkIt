package com.bookTrackerProject.bookMarkIt.controller;

import com.bookTrackerProject.bookMarkIt.domain.Book;
import com.bookTrackerProject.bookMarkIt.domain.UserAddUpdateBookRequest;
import com.bookTrackerProject.bookMarkIt.domain.UserBookResponse;
import com.bookTrackerProject.bookMarkIt.service.UserBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/books")
public class UserController {
    @Autowired
    private UserBooksService userBooksService;

    private String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/")
    public ResponseEntity<List<UserBookResponse>> getUserLibrary(
           @RequestParam(required = false) String status,
           @RequestParam(required = false) Float rating
    ) {
        List<UserBookResponse> books  = userBooksService.getAllBooks(getUsername(), status, rating);
        return books.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(books);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBookToLibrary(@RequestBody UserAddUpdateBookRequest request) {
        return ResponseEntity.ok(userBooksService.addBookToUserLibrary(request, getUsername()));
    }

    @PutMapping("/progress/{id}")
    public ResponseEntity<UserBookResponse> updateBookProgress(@PathVariable String id, @RequestBody UserAddUpdateBookRequest request) {
        return ResponseEntity.ok(userBooksService.updateUserBook(id, request, getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable String id) {
        return ResponseEntity.ok(userBooksService.deleteUserBook(id, getUsername()));
    }

}
