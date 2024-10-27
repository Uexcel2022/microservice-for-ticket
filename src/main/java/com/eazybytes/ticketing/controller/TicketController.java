package com.eazybytes.ticketing.controller;

import com.eazybytes.ticketing.dto.ResponseDto;
import com.eazybytes.ticketing.dto.TicketDto;
import com.eazybytes.ticketing.service.ITicket;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketController {

    private final ITicket iticket;

    public TicketController(ITicket iticket) {
        this.iticket = iticket;
    }

    @PostMapping("/create")
    public ResponseEntity<TicketDto> createTicket(@RequestParam Long routeId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iticket.createTicket(routeId));
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<TicketDto>> getTicket(@RequestParam String phoneNumber) {
       return ResponseEntity.ok().body(iticket.getTicketPhoneNumber(phoneNumber));
    }

    @PutMapping("/cancel")
    public ResponseEntity<ResponseDto> cancelTicket(String ticketId){
        boolean success = iticket.cancelTicket(ticketId);
        if(success) {
            return ResponseEntity.ok().body(new ResponseDto(200,
                    "Ticket cancelled successfully."));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(417, "Request failed."));
    }
}
