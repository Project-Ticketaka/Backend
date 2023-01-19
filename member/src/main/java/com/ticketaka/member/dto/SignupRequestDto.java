package com.ticketaka.member.dto;

import com.ticketaka.member.entity.Gender;
import com.ticketaka.member.entity.Member;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Slf4j
@Data
public class SignupRequestDto {
    private String email;
    private String password;
    private String name;
    private String birth;
    private String phone;

    private String gender;

    public Member toEntity(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREA);
        log.info(this.birth);
        LocalDate birth = LocalDate.parse(this.birth, formatter);
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .birth(birth)
                .phone(phone)
                .gender(gender)
                .isadult(LocalDate.now().getYear()- birth.getYear()>=19?true:false) // 지금 연도와 출생년도 비교, 19이상이면 성인
                .build();
    }
}
