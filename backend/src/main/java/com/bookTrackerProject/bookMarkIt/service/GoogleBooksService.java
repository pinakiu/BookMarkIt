package com.bookTrackerProject.bookMarkIt.service;

import com.bookTrackerProject.bookMarkIt.domain.BookDTO;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.books.v1.Books;
import com.google.api.services.books.v1.model.Volume;
import com.google.api.services.books.v1.model.Volumes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleBooksService  {
    private final Books books;

    @Value("${google.books.api.key}")
    private String apiKey;

    public GoogleBooksService() throws Exception {
        HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        books = new Books.Builder(transport, jsonFactory, request -> {})
                .setApplicationName("bookMarkIt")
                .build();
    }

    public List<BookDTO> searchBooks(String query) {
        List<BookDTO> results = new ArrayList<>();

        try {
            Books.Volumes.List request = books.volumes().list(query);
            request.setKey(apiKey);

            Volumes volumes = request.execute();

            if (volumes.getItems() != null) {
                for (Volume volume : volumes.getItems()) {
                    Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();

                    BookDTO book = new BookDTO();
                    book.setId(volume.getId());
                    book.setTitle(volumeInfo.getTitle());
                    book.setAuthor(volumeInfo.getAuthors());
                    book.setImageLinks(volumeInfo.getImageLinks());
                    book.setAverageRating(volumeInfo.getAverageRating());
                    book.setPublishedDate(volumeInfo.getPublishedDate());
                    book.setGenres(volumeInfo.getCategories());
                    results.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    public BookDTO getFullBookDetails(String id) {
        BookDTO book = new BookDTO();

        try {
            Volume volume = books.volumes().get(id).setKey(apiKey).execute();

            if (volume == null || volume.getVolumeInfo() == null)
                return book;

            Volume.VolumeInfo vInfo = volume.getVolumeInfo();

            book.setId(volume.getId());
            book.setTitle(vInfo.getTitle());
            book.setAuthor(vInfo.getAuthors());
            book.setAverageRating(vInfo.getAverageRating());
            book.setPublishedDate(vInfo.getPublishedDate());
            book.setGenres(vInfo.getCategories());
            book.setImageLinks(vInfo.getImageLinks());
            book.setDescription(vInfo.getDescription());
            book.setIndustryIdentifiers(vInfo.getIndustryIdentifiers());
            book.setMaturityRating(vInfo.getMaturityRating());
            book.setLanguage(vInfo.getLanguage());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;
    }

    public Volumes searchBookDetails(String query) {
        Volumes results = new Volumes();

        try {
            Books.Volumes.List request = books.volumes().list(query);
            request.setKey(apiKey);

            Volumes volumes = request.execute();

            if (volumes.getItems() != null) {
                results = volumes;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

}
