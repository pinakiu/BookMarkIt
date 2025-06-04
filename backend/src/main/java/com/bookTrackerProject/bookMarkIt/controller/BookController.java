package com.bookTrackerProject.bookMarkIt.controller;

import com.bookTrackerProject.bookMarkIt.domain.BookDTO;
import com.bookTrackerProject.bookMarkIt.service.GoogleBooksService;
import com.bookTrackerProject.bookMarkIt.service.UserBooksService;
import com.google.api.services.books.v1.model.Volumes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private GoogleBooksService googleBooksService;
    @Autowired
    private UserBooksService userBooksService;

    @GetMapping("/search") //query='bookname'
    public ResponseEntity<List<BookDTO>> searchBooks(@RequestParam String query) {
        return ResponseEntity.ok(googleBooksService.searchBooks(query));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> searchBookById(@PathVariable String id) {
        return ResponseEntity.ok(googleBooksService.getFullBookDetails(id));
    }

    @GetMapping("/searchBookDetails")
    public ResponseEntity<Volumes> searchBookDetails(@RequestParam String query) {
        return ResponseEntity.ok(googleBooksService.searchBookDetails(query));
    }

}
