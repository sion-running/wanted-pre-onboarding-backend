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

    @Query("SELECT j FROM Job j JOIN FETCH j.company WHERE j.company.name LIKE %:companyName%")
    List<Job> findByCompanyNameLike(@Param("companyName") String companyName);

    @Query("SELECT j FROM Job j JOIN FETCH j.company WHERE j.position LIKE %:position%")
    List<Job> findByPositionLike(@Param("position") String position);

    @Query("SELECT j FROM Job j JOIN FETCH j.company WHERE j.skills LIKE %:skills%")
    List<Job> findBySkillsLike(@Param("skills") String skills);
}
