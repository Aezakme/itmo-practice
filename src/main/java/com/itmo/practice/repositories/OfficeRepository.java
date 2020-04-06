package com.itmo.practice.repositories;

import com.itmo.practice.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long>{

    Long findIdByName(String office_name);
}
