package com.wanted.recruitment.repository;

import com.wanted.recruitment.model.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT j FROM Job j JOIN FETCH j.company")
    List<Job> findAllWithCompany();

    @Query("SELECT j FROM Job j JOIN FETCH j.company WHERE j.id = :id")
    Job findByIdWithCompany(@Param("id") Long id);
}
