package com.ticketaka.member.security.jwt;

import com.ticketaka.member.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtUtils jwtUtils;
    private final RedisService redisService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest rq = (HttpServletRequest) request;
        // 1. Request Header 에서 JWT 토큰 추출
        String accessToken = jwtUtils.getAccessToken(rq);

        // 2. validateToken 으로 accessToken의  유효성 검사 -> 유효할 경우 pass
        if (accessToken != null && jwtUtils.validateToken(accessToken)) {
            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext 에 저장
            Authentication authentication = jwtUtils.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        else{
            // 3.accessToken 이 유효하지않은 경우 -> refreshToken 의 유효성 검사
            String refreshToken = jwtUtils.getRefreshToken(rq);
            // Refresh 토큰이 유효하지 않은 경우 RefreshToken 의 유효성 검사 실행
            if (refreshToken != null && jwtUtils.validateRefreshToken(refreshToken)) {
                // Refresh 토큰이 유효한 경우 AccessToken 을 재발급하고 해당 AccessToken 을 재발급
                // Refresh 토큰을 이용하여 redis 에서 memberId 를 가져오고 해당 memberId 를 통하여 AccessToken 발급
                accessToken = jwtUtils.generateAccessToken(redisService.getMemberId(refreshToken));
                Authentication authentication = jwtUtils.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        chain.doFilter(request, response);
    }


}
