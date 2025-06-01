package com.bookTrackerProject.bookMarkIt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBookResponse {
    private String status;
    private Integer progress;
    private Float rating;
    private String review;
    private LocalDateTime addedAt;
    private LocalDateTime updatedAt;
    private Book book;
}
