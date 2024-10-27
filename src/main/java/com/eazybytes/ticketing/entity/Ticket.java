package com.eazybytes.ticketing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
@Entity
public class Ticket extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketId;
    private Long  routId;
    private String phoneNumber;
    private double  amount;
    private String  status;
    private LocalDate purchasedDate;
    private Date usedDate;
    private String  busId;
}
