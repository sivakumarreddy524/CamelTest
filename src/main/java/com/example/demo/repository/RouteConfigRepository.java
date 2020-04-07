package com.example.demo.repository;

import com.example.demo.domain.RouteConfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RouteConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RouteConfigRepository extends JpaRepository<RouteConfig, Long> {

    RouteConfig findByRouteName(String name);

}
