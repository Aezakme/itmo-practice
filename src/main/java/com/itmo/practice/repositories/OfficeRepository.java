package com.itmo.practice.repositories;

import com.itmo.practice.model.Book;
import com.itmo.practice.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long>{

    @Query("SELECT b " +
            "FROM books b " +
            "INNER JOIN availability a on b.id = a.book_id " +
            "INNER JOIN offices o on a.office_id = o.id " +
            "AND o.id = ?1")
    List<Book> booksByOfficeId(Long id);
}
