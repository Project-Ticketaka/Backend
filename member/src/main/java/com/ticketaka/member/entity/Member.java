package com.ticketaka.member.entity;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(nullable = false)
    private Date date;

    @Column(length = 20,nullable = false)
    private String phone;

    @Column(length=4, nullable = false)
    private String gender;

    @Column(nullable = false )
    private Boolean isadult;

    @Column(nullable = false)
    private String refresh_token;
}
