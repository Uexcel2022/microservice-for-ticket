package com.eazybytes.ticketing.repositorty;

import com.eazybytes.ticketing.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutRepository extends JpaRepository<Route,Long> {
}
