package com.example.demo.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A ApiResponse.
 */
@Entity
@Table(name = "api_response")
public class ApiResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "time_stam", nullable = false)
    private LocalDate timeStam;

    @NotNull
    @Column(name = "api_response", nullable = false)
    private String apiResponse;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTimeStam() {
        return timeStam;
    }

    public ApiResponse timeStam(LocalDate timeStam) {
        this.timeStam = timeStam;
        return this;
    }

    public void setTimeStam(LocalDate timeStam) {
        this.timeStam = timeStam;
    }

    public String getApiResponse() {
        return apiResponse;
    }

    public ApiResponse apiResponse(String apiResponse) {
        this.apiResponse = apiResponse;
        return this;
    }

    public void setApiResponse(String apiResponse) {
        this.apiResponse = apiResponse;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiResponse)) {
            return false;
        }
        return id != null && id.equals(((ApiResponse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
            "id=" + getId() +
            ", timeStam='" + getTimeStam() + "'" +
            ", apiResponse='" + getApiResponse() + "'" +
            "}";
    }
}
