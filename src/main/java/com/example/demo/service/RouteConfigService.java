package com.example.demo.service;

import com.example.demo.domain.RouteConfig;
import com.example.demo.repository.RouteConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link RouteConfig}.
 */
@Service
@Transactional
public class RouteConfigService {

    private final Logger log = LoggerFactory.getLogger(RouteConfigService.class);

    private final RouteConfigRepository routeConfigRepository;

    public RouteConfigService(RouteConfigRepository routeConfigRepository) {
        this.routeConfigRepository = routeConfigRepository;
    }

    /**
     * Save a routeConfig.
     *
     * @param routeConfig the entity to save.
     * @return the persisted entity.
     */
    public RouteConfig save(RouteConfig routeConfig) {
        log.debug("Request to save RouteConfig : {}", routeConfig);
        return routeConfigRepository.save(routeConfig);
    }

    /**
     * Get all the routeConfigs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RouteConfig> findAll() {
        log.debug("Request to get all RouteConfigs");
        return routeConfigRepository.findAll();
    }

    /**
     * Get one routeConfig by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RouteConfig> findOne(Long id) {
        log.debug("Request to get RouteConfig : {}", id);
        return routeConfigRepository.findById(id);
    }

    /**
     * Delete the routeConfig by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RouteConfig : {}", id);
        routeConfigRepository.deleteById(id);
    }

    public RouteConfig findByName(String name) {
        return routeConfigRepository.findByRouteName(name);
    }
}
