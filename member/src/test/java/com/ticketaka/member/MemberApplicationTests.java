package com.ticketaka.member;

import com.ticketaka.member.security.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberApplicationTests {
	@Autowired
	JwtUtils utils;
	@Test
	void contextLoads() {
	}

	@Test
	void refreshToken(){
		Claims claims = utils.parseClaims("eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzI4NDYyNzN9.PTp_f8fpJs6oUCrOXeA838O4GD8BEDvw-Jxk9M4xWk0");
		System.out.println(claims);
	}

}
