package com.eazybytes.ticketing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class  BustRoute{
    private String busId;
    private Long routeId;

    public static List<BustRoute> getARoute(){
        List<BustRoute> routes = new ArrayList<>();
        routes.add(new BustRoute("busE10", 1L));
        routes.add(new BustRoute("busE11", 1L));
        routes.add(new BustRoute("busE10", 2L));
        routes.add(new BustRoute("busE11", 2L));
       return routes;
    }
}
