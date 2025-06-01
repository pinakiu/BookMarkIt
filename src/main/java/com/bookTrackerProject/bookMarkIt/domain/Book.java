package com.bookTrackerProject.bookMarkIt.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "market_id")
    private UUID marketId;

    @Column(name = "google_books_id")
    private String googleBooksId;
    private String title;
    private String author;
    @Column(name = "cover_url")
    private String coverUrl;
    private String isbn;
    private String description;
    @Column(name = "cover_url_backup")
    private String coverUrlBackup;
}
