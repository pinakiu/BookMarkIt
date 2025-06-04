package com.bookTrackerProject.bookMarkIt.domain;

import com.google.api.services.books.v1.model.Volume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String id;
    private String title;
    private List<String> author;
    private String publishedDate;
    private Double averageRating;
    private List<String> genres;
    private Volume.VolumeInfo.ImageLinks imageLinks;

    private String description;
    private List<Volume.VolumeInfo.IndustryIdentifiers> industryIdentifiers;
    private String maturityRating;
    private String language;
}
