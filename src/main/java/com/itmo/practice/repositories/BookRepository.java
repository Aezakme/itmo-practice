package com.itmo.practice.repositories;

import com.itmo.practice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT new com.itmo.practice.repositories.BookInfo(B.title, B.author, O.name, O.address, A.amount)\n" +
            "               FROM books AS B\n" +
            "               INNER JOIN availability as A ON A.book_id = B.id\n" +
            "               INNER JOIN offices as O ON A.office_id = O.id\n" +
            "               WHERE amount > 0 AND B.id = ?1")
    List<BookInfo> getInfoById(Long id);

    @Modifying
    @Query(
            value =
                    "insert into books (id, title, author)\n" +
                    "values (:id, :title, :author)",
            nativeQuery = true)
    void insertBook(@Param("id") Long id, @Param("title") String title,
                           @Param("author") String author);

    @Query("SELECT COUNT(*) FROM books")
    Integer uniqueBookCount();
}