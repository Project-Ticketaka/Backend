package com.ticketaka.member.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequestDto {

    private String member_email;
    private String member_password;


}
