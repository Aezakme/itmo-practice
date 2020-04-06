package com.itmo.practice.repositories;

import com.itmo.practice.entity.Book;
import com.itmo.practice.entity.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT new com.itmo.practice.entity.BookInfo(B.title, B.author, O.name, O.address, A.amount)\n" +
            "               FROM books AS B\n" +
            "               INNER JOIN availability as A ON A.book_id = B.id\n" +
            "               INNER JOIN offices as O ON A.office_id = O.id\n" +
            "               WHERE A.amount > 0 AND B.id = ?1")
    List<BookInfo> findAvailabilityById(Long id);

    @Override
    Optional<Book> findById(Long aLong);

    List<Book> findBooksByTitleAndAuthor(String title, String author);

    @Modifying
    @Transactional
    @Query(value = "insert into books (title, author, description, year) values (:title, :author, :description, :year)", nativeQuery = true)
    void insertBook(@Param("title") String title, @Param("author") String author, @Param("description") String description, @Param("year") Date year);

}