package com.eazybytes.ticketing.mapper;

import com.eazybytes.ticketing.dto.TicketDto;
import com.eazybytes.ticketing.entity.Route;
import com.eazybytes.ticketing.entity.Ticket;

import java.time.LocalDate;
import java.util.Random;


public class TicketMapper {
  public static TicketDto mapToTicketDto(Ticket ticket, Route route) {
       TicketDto dto = new TicketDto();
       dto.setName("Nelson Mandela");
       dto.setTicketId(ticket.getTicketId());
       dto.setOrigin(route.getOrigin());
       dto.setDestination(route.getDestination());
       dto.setAmount(route.getPrice());
       dto.setPurchasedDate(ticket.getPurchasedDate());
       dto.setExpiryDate(
               ticket.getPurchasedDate().plusDays(350));
       return dto;
   }

   public static  Ticket mapToTicket(Ticket ticket, Route route) {
//      long customerId = new Random().nextLong(1000);
       ticket.setAmount(route.getPrice());
       ticket.setPurchasedDate(LocalDate.now());
       ticket.setRoutId(route.getRouteId());
       ticket.setStatus("valid");
       ticket.setPhoneNumber("1234");
       return ticket;
    }

}
