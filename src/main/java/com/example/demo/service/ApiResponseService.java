package com.example.demo.service;


import com.example.demo.domain.ApiResponse;
import com.example.demo.repository.ApiResponseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ApiResponse}.
 */
@Service
@Transactional
public class ApiResponseService {

    private final Logger log = LoggerFactory.getLogger(ApiResponseService.class);

    private final ApiResponseRepository apiResponseRepository;

    public ApiResponseService(ApiResponseRepository apiResponseRepository) {
        this.apiResponseRepository = apiResponseRepository;
    }

    /**
     * Save a apiResponse.
     *
     * @param apiResponse the entity to save.
     * @return the persisted entity.
     */
    public ApiResponse save(ApiResponse apiResponse) {
        log.debug("Request to save ApiResponse : {}", apiResponse);
        return apiResponseRepository.save(apiResponse);
    }

    /**
     * Get all the apiResponses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ApiResponse> findAll() {
        log.debug("Request to get all ApiResponses");
        return apiResponseRepository.findAll();
    }

    /**
     * Get one apiResponse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ApiResponse> findOne(Long id) {
        log.debug("Request to get ApiResponse : {}", id);
        return apiResponseRepository.findById(id);
    }

    /**
     * Delete the apiResponse by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ApiResponse : {}", id);
        apiResponseRepository.deleteById(id);
    }
}
