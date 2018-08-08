package br.com.hoptech.vaaguruapi.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.hoptech.vaaguruapi.domain.Invitation;
import br.com.hoptech.vaaguruapi.domain.Rower;

public interface EmailService {
    
    void sendEmail(SimpleMailMessage msg);
    
    void sendHtmlEmail(MimeMessage msg);

    void sendNewPassawordEmail(Rower rower, String newPass);

    void sendInvitationEmail(Invitation obj);

    void sendInvitationHtmlEmail(Invitation obj);
    
}
