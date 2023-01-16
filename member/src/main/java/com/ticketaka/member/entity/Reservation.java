package com.ticketaka.member.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;


}
