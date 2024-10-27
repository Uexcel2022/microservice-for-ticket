package com.eazybytes.ticketing.repositorty;

import com.eazybytes.ticketing.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    Ticket findByRoutIdAndPhoneNumberAndStatus(
            long routeId, String phoneNumber, String status);
    List<Ticket> findByPhoneNumberAndStatus(String phoneNumber, String status);

    Optional<Ticket> findByTicketIdAndStatus(String ticketId, String status);
}
