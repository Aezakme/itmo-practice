package com.itmo.practice.repositories;

import com.itmo.practice.entity.Availability;
import com.itmo.practice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into availability (book_id, office_id, amount)values(:bookId, :officeId, :amount)",
            nativeQuery = true)
    void insertAvailability(@Param("bookId") Long bookId, @Param("officeId") Long officeId, @Param("amount") Integer amount);

    @Query("SELECT b " +
            "FROM books b " +
            "INNER JOIN availability a on b.id = a.book_id " +
            "INNER JOIN offices o on a.office_id = o.id " +
            "AND o.id = ?1")
    List<Book> booksByOfficeId(Long id);

    @Query("SELECT a " +
            "FROM availability a " +
            "WHERE a.book_id=:bookId and a.office_id=:officeId")
    Availability findByBookIdAndOfficeId(@Param("bookId") Long bookId, @Param("officeId") Long officeId);

    @Query("SELECT a " +
            "FROM availability a " +
            "WHERE a.book_id=:bookId" )
    List<Availability> findByBookId(@Param("bookId") Long bookId);
}