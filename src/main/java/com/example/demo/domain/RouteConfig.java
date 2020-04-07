package com.example.demo.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A RouteConfig.
 */
@Entity
@Table(name = "route_config")
public class RouteConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "route_name", nullable = false)
    private String routeName;

    @NotNull
    @Column(name = "route_path", nullable = false)
    private String routePath;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public RouteConfig routeName(String routeName) {
        this.routeName = routeName;
        return this;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRoutePath() {
        return routePath;
    }

    public RouteConfig routePath(String routePath) {
        this.routePath = routePath;
        return this;
    }

    public void setRoutePath(String routePath) {
        this.routePath = routePath;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RouteConfig)) {
            return false;
        }
        return id != null && id.equals(((RouteConfig) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RouteConfig{" +
            "id=" + getId() +
            ", routeName='" + getRouteName() + "'" +
            ", routePath='" + getRoutePath() + "'" +
            "}";
    }
}
