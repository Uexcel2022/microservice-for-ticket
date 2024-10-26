package com.eazybytes.ticketing.mapper;

import com.eazybytes.ticketing.dto.TicketDto;
import com.eazybytes.ticketing.entity.Route;
import com.eazybytes.ticketing.entity.Ticket;

import java.time.LocalDate;

public class TicketMapper {
    TicketDto mapToTicketDto(Ticket ticket, Route route) {
        TicketDto dto = new TicketDto();
        dto.setName("Nelson Mandela");
        dto.setTickId(ticket.getTickId());
        dto.setOrigin(route.getOrigin());
        dto.setOrigin(route.getDestination());
        dto.setAmount(route.getPrice());
        dto.setExpiryDate(LocalDate.parse("Valid till: "+
                ticket.getPurchasedDate().plusDays(350)));

        return dto;
    }
}
