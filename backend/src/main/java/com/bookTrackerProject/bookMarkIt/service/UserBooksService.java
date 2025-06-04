package com.bookTrackerProject.bookMarkIt.service;

import com.bookTrackerProject.bookMarkIt.domain.*;
import com.bookTrackerProject.bookMarkIt.repositories.BookRepository;
import com.bookTrackerProject.bookMarkIt.repositories.UserBooksRepository;
import com.bookTrackerProject.bookMarkIt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserBooksService {
    @Autowired private GoogleBooksService googleBooksService;
    @Autowired private UserRepository userRepository;
    @Autowired private UserBooksRepository userBooksRepository;
    @Autowired private BookRepository bookRepository;

    public List<UserBookResponse> getAllBooks(String username, String status, Float rating) {
        UUID userId = getUserIdFromUsername(username);
        if (userId == null) return Collections.emptyList();

        return userBooksRepository.findByUserAndOptionalFilters(userId, status, rating).stream()
                .map(userBook -> UserBookResponse.builder()
                        .status(userBook.getStatus())
                        .progress(userBook.getProgress())
                        .rating(userBook.getRating())
                        .review(userBook.getReview())
                        .book(userBook.getBook())
                        .build())//applies method getBook to each element of stream
                .collect(Collectors.toList());
    }

    public Book addBookToUserLibrary(UserAddUpdateBookRequest userAddUpdateBookRequest, String username) {
        Book book = getOrCreateBook(userAddUpdateBookRequest.getGoogleBooksId());
        User user = getUserByUsername(username);

        if (userBooksRepository.findByUserAndBook(user, book).isPresent())
            return book;

        UserBook userBook = UserBook.builder()
                .user(user)
                .book(book)
                .status(userAddUpdateBookRequest.getStatus())
                .progress(userAddUpdateBookRequest.getProgress())
                .rating(userAddUpdateBookRequest.getRating())
                .review(userAddUpdateBookRequest.getReview())
                .addedAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userBooksRepository.save(userBook);
        return book;

    }

    public UserBookResponse updateUserBook(String bookId, UserAddUpdateBookRequest request, String username) {
//        Book book = getOrCreateBook(request.getGoogleBooksId());
        Book book = getBookById(bookId);
        User user = getUserByUsername(username);
        //rn, getting googlebooksid and looking up book
        //want to do: just sent marketid and get book that way
        UserBook userBook = userBooksRepository.findByUserAndBook(user, book)
                .orElseThrow(() -> new RuntimeException("UserBook entry not found"));

        userBook.setStatus(request.getStatus());
        userBook.setProgress(request.getProgress());
        userBook.setReview(request.getReview());
        userBook.setRating(request.getRating());
        userBook.setUpdatedAt(LocalDateTime.now());

        UserBook updated = userBooksRepository.save(userBook);

        return UserBookResponse.builder()
                .status(updated.getStatus())
                .progress(updated.getProgress())
                .rating(updated.getRating())
                .review(updated.getReview())
//                .addedAt(updated.getAddedAt())
//                .updatedAt(updated.getUpdatedAt())
                .book(book)
                .build();
    }

    public Book deleteUserBook(String bookId, String username) {
        User user = getUserByUsername(username);
        Book book = getBookById(bookId);

        UserBook userBook = userBooksRepository.findByUserAndBook(user, book)
                .orElseThrow(() -> new RuntimeException("UserBook entry not found"));
        userBooksRepository.delete(userBook);
        return book;
    }

//---------- Helpers ----------------
private Book getOrCreateBook(String googleId) {
    Book existing = bookRepository.findByGoogleBooksId(googleId);
    if (existing != null) return existing;

    BookDTO dto = googleBooksService.getFullBookDetails(googleId);
    Book newBook = Book.builder()
            .title(dto.getTitle())
            .author(String.join(",", dto.getAuthor()))
            .googleBooksId(dto.getId())
            .coverUrl(dto.getImageLinks().getThumbnail())
            .coverUrlBackup(dto.getImageLinks().getSmallThumbnail())
            .description(dto.getDescription())
            .build();

    return bookRepository.save(newBook);
}

    private UUID getUserIdFromUsername(String username) {
        return userRepository.findByUsername(username).map(User::getId).orElse(null);
    }

    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Book getBookById(String bookId) {
        return bookRepository.findById(UUID.fromString(bookId))
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }
}


