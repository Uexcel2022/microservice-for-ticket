package com.eazybytes.ticketing.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class RouteDto {
    private String origin;
    private String destination;
    private double price;
}
