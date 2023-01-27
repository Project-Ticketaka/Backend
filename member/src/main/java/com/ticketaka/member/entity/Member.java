package com.ticketaka.member.entity;

import com.ticketaka.member.dto.response.InfoResponseDto;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "member_name", length = 20, nullable = false)
    private String name;

    @Column( name ="member_email", length = 30, nullable = false,unique = true)
    private String email;

    @Column(name = "member_password", length = 20, nullable = false)
    private String password;

    @Column(name = "member_birth", nullable = false)
    private LocalDate birth;

    @Column(name = "member_phone", length = 20,nullable = false)
    private String phone;

    @Column(name= "member_gender", length=8, nullable = false)
//    @Enumerated(EnumType.STRING)
    private String gender; // enum 으로 바꿔야겟다

    @Column(name = "member_isadult", nullable = false )
    private Boolean isadult;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @Column(nullable = false)
//    private String refresh_token;

    public InfoResponseDto toInfoResponseDto(){
        return InfoResponseDto.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .birth(birth)
                .build();
    }

}
