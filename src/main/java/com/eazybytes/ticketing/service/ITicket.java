package com.eazybytes.ticketing.service;

import com.eazybytes.ticketing.dto.TicketDto;

import java.util.List;

public interface ITicket {

    /**
     * @param routeId  - routeId
     * @return - Returns ticked info
     */
    TicketDto createTicket(Long routeId);

    /**
     * @param phoneNumber - customer's phone number
     * @return Returns ticket info with TicketDto
     */
    List<TicketDto> getTicketPhoneNumber(String phoneNumber);

    /**
     * @param ticketId - ticket id
     * @return Returns boolean values indicating success or fail
     */
    boolean cancelTicket(String ticketId);


}
