package com.eazybytes.ticketing.service.impl;

import com.eazybytes.ticketing.dto.TicketDto;
import com.eazybytes.ticketing.entity.Route;
import com.eazybytes.ticketing.entity.Ticket;
import com.eazybytes.ticketing.exception.InvalidInputException;
import com.eazybytes.ticketing.exception.ResourceNoFoundException;
import com.eazybytes.ticketing.mapper.TicketMapper;
import com.eazybytes.ticketing.repositorty.RouteRepository;
import com.eazybytes.ticketing.repositorty.TicketRepository;
import com.eazybytes.ticketing.service.ICheckinService;
import com.eazybytes.ticketing.service.ITicket;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class TicketImpl implements ITicket {
    private final TicketRepository ticketRepository;
    private final RouteRepository routeRepository;
    private final ICheckinService iCheckinService;

    @Value("${wallet.bal}")
    private double walletBalance;


    public TicketImpl(TicketRepository ticketRepository, RouteRepository routeRepository, ICheckinService iCheckinService) {
        this.ticketRepository = ticketRepository;
        this.routeRepository = routeRepository;
        this.iCheckinService = iCheckinService;
    }

    /**
     * @param routeId - routeId
     * @return - Returns ticked info
     */
    @Override
    public TicketDto createTicket(Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(()->new ResourceNoFoundException(
                        "Route","routeId",routeId.toString())
                );

     Ticket tk = ticketRepository
             .findByRoutIdAndPhoneNumberAndStatus (
                        routeId,"1234","valid");
     if (tk != null) {
        throw  new InvalidInputException(
                "You have unused ticket on this route. TicketId: " + tk.getTicketId()
        );
     }

        if(walletBalance < route.getPrice()){
            throw new  RuntimeException("Your wallet balance is insufficient.");
        }
         walletBalance -= route.getPrice();

        Ticket ticket = TicketMapper.mapToTicket(new Ticket(),route);
        ticketRepository.save(ticket);
       return TicketMapper.mapToTicketDto(ticket,route);
    }

    /**
     * @param phoneNumber - customer's phone number
     * @return Returns ticket info with TicketDto
     */
    @Override
    public List<TicketDto> getTicketPhoneNumber(String phoneNumber) {
        List<Ticket> ticket = ticketRepository.findByPhoneNumberAndStatus(phoneNumber,"valid");

        if(ticket.isEmpty()){
            throw  new ResourceNoFoundException("Ticket","phoneNumber",phoneNumber);
        }
        List<TicketDto> ticketDtoList = new ArrayList<>();
        ticket.stream().forEach(tk -> {
            Route route = routeRepository.findByRouteId(tk.getRoutId());
            ticketDtoList.add(TicketMapper.mapToTicketDto(tk, route));
        });

        return ticketDtoList;
    }

    /**
     * @param ticketId - ticket id
     * @return Returns boolean values indicating success or fail
     */
    @Override
    public boolean cancelTicket(String ticketId) {
        Ticket ticket = ticketRepository.findByTicketIdAndStatus(ticketId,"valid")
                .orElseThrow(()->new InvalidInputException(ticketId + " is not a valid ticket ID or not eligible for refund."));

        if(!iCheckinService.ticketNotExpired(ticket.getPurchasedDate())){
            ticket.setStatus("expired");
            ticketRepository.save(ticket);
            throw  new InvalidInputException("Ticket has expired and not eligible for refund.");
        }

        walletBalance += ticket.getAmount();
        ticket.setStatus("refunded");
        ticketRepository.save(ticket);
        return true;
    }


}
