package com.ticketaka.member.service;

import com.ticketaka.member.dto.LoginRequestDto;
import com.ticketaka.member.dto.SignupRequestDto;
import com.ticketaka.member.dto.TokenInfo;
import com.ticketaka.member.entity.Member;
import com.ticketaka.member.repository.MemberRepository;
import com.ticketaka.member.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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

    private final RedisTemplate<String,String> redisTemplate;
    @Override
    public ResponseEntity<String> signUp(SignupRequestDto dto) {
        // entity 로 변경 후 save
        try{
            memberRepository.save(dto.toEntity());
            return ResponseEntity.ok("SUCCESS_SIGNUP");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("DUPLICATE_NICKNAME");
        }
    }

    @Override
    public ResponseEntity<TokenInfo> login(LoginRequestDto dto) {
        Optional<Member> member = memberRepository.findByEmail(dto.getEmail());
        if(!member.isPresent()){
            //없을 때 예외처리
        }
        // 있을때 정상 로직 처리 ->
        // access token 발급 및 refresh token 발급(따로 발급받는걸로) Refresh 토큰을 Redis 에 저장
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        Long memberId =  member.get().getId();
        TokenInfo tokenInfo = jwtUtils.generateToken(memberId);

        //4. refresh 토크을 Redis 에 저장
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(memberId.toString(),tokenInfo.getRefreshToken());
        log.info("redis: {}", valueOperations.get(memberId.toString()));
        log.info(String.valueOf(tokenInfo));
        // 5. 이후 토큰값을 반환
        return ResponseEntity.ok(tokenInfo);
    }
}
