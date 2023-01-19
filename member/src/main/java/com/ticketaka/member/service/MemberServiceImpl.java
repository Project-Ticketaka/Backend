package com.ticketaka.member.service;

import com.ticketaka.member.dto.LoginRequestDto;
import com.ticketaka.member.dto.SignupRequestDto;
import com.ticketaka.member.dto.TokenInfo;
import com.ticketaka.member.entity.Member;
import com.ticketaka.member.repository.MemberRepository;
import com.ticketaka.member.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final JwtUtils jwtUtils;
    @Override
    public ResponseEntity<String> signUp(SignupRequestDto dto) {
        // entity 로 변경 후 save
        try{
            memberRepository.save(dto.toEntity());
            return ResponseEntity.ok("SUCCESS_SIGNUP");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("중복된 닉네임");
        }
    }

//    @Override
//    public ResponseEntity<TokenInfo> login(LoginRequestDto dto) {
//        Optional<Member> member = memberRepository.findByEmail(dto.getMember_email());
//        if(!member.isPresent()){
//            //없을 때 예외처리
//        }
//        // 있을때 정상 로직 처리 -> token 발급 및 토큰정보를 반환, Refresh 토큰을 Redis 에 저장
//        jwtUtils.generateToken();
//
//
//    }
}
