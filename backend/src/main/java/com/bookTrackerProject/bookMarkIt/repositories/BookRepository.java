package com.bookTrackerProject.bookMarkIt.repositories;

import com.bookTrackerProject.bookMarkIt.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    Book findByGoogleBooksId(String googleBooksId);
}
