package com.eazybytes.ticketing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(BusRouteId.class)
public class BusRoute {
    @Id
    private String busId;
    @Id
    private Long routeId;
}
