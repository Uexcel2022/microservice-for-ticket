package com.eazybytes.ticketing.service;

import com.eazybytes.ticketing.dto.CheckinDto;
import com.eazybytes.ticketing.dto.ResponseDto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public interface ICheckinService {


    /**
     * @param checkinDto  - will hold ticketId and busId
     * @return Returns response status and message
     */
     ResponseDto checkinValidation(CheckinDto checkinDto);


    /**
     * @param purchasedDate  - ticket purchased date
     * @return Return boolean value indicating ticked expired or not
     */
     default boolean ticketNotExpired(LocalDate purchasedDate){
        return purchasedDate.plusDays(367).isAfter(LocalDate.now());

    }

    default String formatDate(Date usedDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
        return formatter.format(usedDate);
    }


}
