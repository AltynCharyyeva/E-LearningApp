package com.example.part2.controller;


import com.example.part2.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {


    @Autowired
    private EmailService mailService;


    @PostMapping("/send")
    public String sendMail(){
            mailService.sendEmail("altyncharyyeva8@gmail.com", "Text body", "Text subject");
        System.out.println("here?");
            return "Successfully send the mail";
    }
}
