package com.itmo.practice.repositories;

import com.itmo.practice.model.Available;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableRepository extends JpaRepository<Available, Long> {
    @Query("SELECT COUNT(*) FROM availability")
    Integer availabilityCount();

    @Modifying
    @Query(
            value =
                    "insert into availability (id, book_id, office_id, amount)\n" +
                    "values (:id, :book_id, :office_id, :amount)",
            nativeQuery = true)
    void insertAvailability(@Param("id") Long id, @Param("book_id")Long book_id,
                                   @Param("office_id") Long office_id, @Param("amount") Integer amount);

    @Query("UPDATE availability\n" +
            "    SET amount = amount + ?3\n" +
            "    WHERE book_id = ?1 AND\n" +
            "          office_id IN (SELECT  O.id\n" +
            "                        FROM  offices as O\n" +
            "                        WHERE O.name = ?2)")
    void increaseAmount(Long bookId, String officeName, Integer amount);
}