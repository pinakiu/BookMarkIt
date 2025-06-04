package com.bookTrackerProject.bookMarkIt.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class UserAddUpdateBookRequest {
    private UUID bookId;
    private String status;
    private Integer progress;
    private Float rating;
    private String review;
    private String googleBooksId; // sent from client, used to find/create book
}
