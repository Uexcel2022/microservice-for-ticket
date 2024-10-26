package com.eazybytes.ticketing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter @Setter
@Entity
public class Ticket extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String  tickId;
    private String  routId;
    private String  amount;
    private String  status;
    private LocalDate purchasedDate;
    private LocalTime usedDate;
    private String  busId;
}
