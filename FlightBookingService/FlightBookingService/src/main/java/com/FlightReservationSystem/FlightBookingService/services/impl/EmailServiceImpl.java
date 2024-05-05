package com.FlightReservationSystem.FlightBookingService.services.impl;

import com.FlightReservationSystem.FlightBookingService.entities.Booking;
import com.FlightReservationSystem.FlightBookingService.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setText(message);
        mailMessage.setFrom("thomas.webeqt@gmail.com");
        mailMessage.setSubject(subject);
        mailSender.send(mailMessage);
    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String html) {
        try{
            MimeMessage mailMsg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMsg,true,"UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("thomas.webeqt@gmail.com");
            helper.setText(html);
            mailSender.send(mailMsg);
        }catch (MessagingException ex){
            ex.getStackTrace();
        }




    }
}
