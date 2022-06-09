package com.ecommerce.ecommerce.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.model.MensagemEmail;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender javaMailSender;

    public void enviar(MensagemEmail mensagemEmail) throws MessagingException{

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setFrom(mensagemEmail.getRemetente());
        helper.setSubject(mensagemEmail.getAssunto());
        helper.setText(mensagemEmail.getMensagem(), true);
        helper.setTo(mensagemEmail.getDestinatarios()
            .toArray(new String[mensagemEmail.getDestinatarios().size()])
        );

        javaMailSender.send(mimeMessage);
    }
}
