package com.bookTrackerProject.bookMarkIt.controller;

import com.bookTrackerProject.bookMarkIt.domain.BookDTO;
import com.bookTrackerProject.bookMarkIt.service.GoogleBooksService;
import com.google.api.services.books.v1.model.Volumes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private GoogleBooksService googleBooksService;

    @GetMapping("/search")
    public List<BookDTO> searchBooks(@RequestParam String query) {
        return googleBooksService.searchBooks(query);
    }

    @GetMapping("/{id}")
    public BookDTO searchBookById(@PathVariable String id) {
        return googleBooksService.getFullBookDetails(id);
    }

    @GetMapping("/searchBookDetails")
    public Volumes searchBookDetails(@RequestParam String query) {
        return googleBooksService.searchBookDetails(query);
    }
}
