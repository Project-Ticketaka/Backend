package com.ticketaka.member.service;

import com.ticketaka.member.dto.LoginRequestDto;
import com.ticketaka.member.dto.SignupRequestDto;
import com.ticketaka.member.dto.TokenInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface MemberService {
    ResponseEntity<String> signUp(SignupRequestDto dto);
    ResponseEntity<TokenInfo> login(LoginRequestDto dto);
}
