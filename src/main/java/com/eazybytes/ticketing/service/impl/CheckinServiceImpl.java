package com.eazybytes.ticketing.service.impl;

import com.eazybytes.ticketing.dto.BustRoute;
import com.eazybytes.ticketing.dto.CheckinDto;
import com.eazybytes.ticketing.dto.ResponseDto;
import com.eazybytes.ticketing.entity.Ticket;
import com.eazybytes.ticketing.exception.InvalidInputException;
import com.eazybytes.ticketing.exception.ResourceNoFoundException;
import com.eazybytes.ticketing.repositorty.TicketRepository;
import com.eazybytes.ticketing.service.ICheckinService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CheckinServiceImpl implements ICheckinService {
    private final TicketRepository ticketRepository;

    public CheckinServiceImpl(TicketRepository ticketRepository) {

        this.ticketRepository = ticketRepository;
    }
    
    /**
     * @param checkinDto - will hold ticketId and busId
     * @return - Returns status and message
     */
    public ResponseDto checkinValidation(CheckinDto checkinDto){

        Ticket ticket = ticketRepository.findById(checkinDto.getTicketId())
                .orElseThrow(()->new ResourceNoFoundException(
                        "Ticket","ticketId",checkinDto.getTicketId()));


        if(!ticketNotExpired(ticket.getPurchasedDate())){
            ticket.setStatus("expired");
            ticketRepository.save(ticket);
            throw  new InvalidInputException("Ticket has expired.");
        }

        if(ticket.getStatus().equalsIgnoreCase("used")){
            throw  new InvalidInputException(
                    "The ticket was used on "+formatDate(ticket.getUsedDate()));
        }

        if(ticket.getStatus().equalsIgnoreCase("refunded")){
            throw  new InvalidInputException(
                    "The ticket was refunded on "+formatDate(ticket.getUpdatedAt()));
        }


        List<BustRoute> isValidRoute =   BustRoute.getARoute().stream().filter(
                busRout->busRout.getBusId().equals(checkinDto.getBusId())
                        && busRout.getRouteId().equals(ticket.getRoutId())
        ).toList();

        if(isValidRoute.isEmpty()){
            throw new InvalidInputException(
                    "The ticketId "+ checkinDto.getTicketId()+" is not for this route");
        }
        ticket.setStatus("used");
        ticket.setBusId(checkinDto.getBusId());
        ticket.setUsedDate(new Date());
        ticketRepository.save(ticket);

        return new ResponseDto(200,"Checkin successful. Thank you for using our services.");
    }


}
