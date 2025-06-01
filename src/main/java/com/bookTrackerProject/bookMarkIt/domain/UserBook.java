package com.bookTrackerProject.bookMarkIt.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_books")
public class UserBook {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private String status; // 'WANT_TO_READ', 'CURRENTLY READING', 'READ'

    private Integer progress;

    private Float rating;

    private String review;

    private LocalDateTime addedAt;

    private LocalDateTime updatedAt;

}
