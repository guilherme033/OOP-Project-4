package com.example.demo.repositories;

import com.example.demo.entities.Attend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendRepository extends JpaRepository <Attend,Long>{
    
    @Query("SELECT at FROM Attend at " +
           "WHERE " +
           " ( LOWER(at.name)            LIKE   LOWER(CONCAT('%', :name,    '%'))) "
    )

    public Page <Attend> find(Pageable pageRequest, String name);
}