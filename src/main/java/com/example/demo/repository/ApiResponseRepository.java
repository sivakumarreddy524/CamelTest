package com.example.demo.repository;

import com.example.demo.domain.ApiResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ApiResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApiResponseRepository extends JpaRepository<ApiResponse, Long> {
}
