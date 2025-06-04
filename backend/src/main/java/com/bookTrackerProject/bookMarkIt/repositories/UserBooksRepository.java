package com.bookTrackerProject.bookMarkIt.repositories;

import com.bookTrackerProject.bookMarkIt.domain.Book;
import com.bookTrackerProject.bookMarkIt.domain.User;
import com.bookTrackerProject.bookMarkIt.domain.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserBooksRepository extends JpaRepository<UserBook, UUID> {

    @Query("SELECT ub FROM UserBook ub WHERE ub.user.id = :userId "
            + "AND (:status IS NULL OR ub.status = :status) "
            + "AND (:rating IS NULL OR ub.rating = :rating)")
    List<UserBook> findByUserAndOptionalFilters(
            @Param("userId") UUID userId,
            @Param("status") String status,
            @Param("rating") Float rating
    );
    //findall()
    //findbyId(UUID id)
    //save(Book book)
    //deleteById (UUID id)
    //existsById (UUID id)
    //count()
    //
    //example use custom queries
    //List<Book> findByTitle(String title)
    //Optional<Book> findByGoogleBooksId(String googleBooksId)
    //List<Book> findByMyRatingGreaterThanEqual(Integer rating)
    Optional<UserBook> findByUserAndBook(User user, Book book);


}
