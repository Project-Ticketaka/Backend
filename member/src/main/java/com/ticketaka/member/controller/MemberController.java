package com.ticketaka.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("member")
@RequiredArgsConstructor
public class MemberController {



    @GetMapping("/login")
    public String checkDuplicateMember(){
        return "duplicate";
    }
    @PostMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("logout")
    public String logout(){
        return "logout";
    }


}
