package com.ticketaka.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mebmer")
@RequiredArgsConstructor
public class ReservationController {

    @GetMapping("/info")
    public String getMemberInfo(){
        return "info";
    }
    @GetMapping("/reservation")
    public String getReservationInfo(){
        return "reservation";
    }
    @GetMapping("/reservations")
    public String getAllReservationInfo(){
        return "allReservations";
    }
}
