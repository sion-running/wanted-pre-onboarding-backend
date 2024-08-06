package com.wanted.recruitment.repository;

import com.wanted.recruitment.model.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByJobIdAndUserId(Long jobId, Long userId);
}
