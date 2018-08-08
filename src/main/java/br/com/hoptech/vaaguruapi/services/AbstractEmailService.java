package br.com.hoptech.vaaguruapi.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.hoptech.vaaguruapi.domain.Invitation;
import br.com.hoptech.vaaguruapi.domain.Rower;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;
    
    @Override
    public void sendInvitationEmail(Invitation obj) {
	SimpleMailMessage sm = prepareSimpleMailMessageFromInvitation(obj);
	sendEmail(sm);
    }
    
    @Override
    public void sendInvitationHtmlEmail(Invitation obj) {
	try {
	    MimeMessage mm = prepareMimeMessageFromInvitation(obj);
	    sendHtmlEmail(mm);
	} catch (MessagingException e) {
	    sendInvitationEmail(obj);
	}
    }
    
    @Override
    public void sendNewPassawordEmail(Rower rower, String newPass) {
	SimpleMailMessage sm = prepareNewPasswordEmail(rower, newPass);
	sendEmail(sm);	
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Rower rower, String newPass) {
	SimpleMailMessage sm = new SimpleMailMessage();
	sm.setTo(rower.getEmail());
	sm.setFrom(sender);
	sm.setSubject("Solicitação de nova senha atendida");
	sm.setSentDate(new Date(System.currentTimeMillis()));
	sm.setText("Nova senha: " + newPass);
	return sm;
    }
    
    protected SimpleMailMessage prepareSimpleMailMessageFromInvitation(Invitation obj) {
	SimpleMailMessage sm = new SimpleMailMessage();
	sm.setTo(obj.getInvitedEmail());
	sm.setFrom(sender);
	sm.setSubject("VAAGURU - Convite para participar do Time " + obj.getTeam().getName());
	sm.setSentDate(new Date(System.currentTimeMillis()));
	sm.setText(obj.toString());
	return sm;
    }
    
    protected MimeMessage prepareMimeMessageFromInvitation(Invitation obj) throws MessagingException {
	MimeMessage mm = javaMailSender.createMimeMessage();
	MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
	mmh.setTo(obj.getInvitedEmail());
	mmh.setFrom(sender);
	mmh.setSubject("VAAGURU - Convite para participar do Time " + obj.getTeam().getName());
	mmh.setSentDate(new Date(System.currentTimeMillis()));
	mmh.setText(htmlFromTemplateInvitation(obj), true);
	return mm;
    }

    protected String htmlFromTemplateInvitation(Invitation obj) {
	Context context = new Context();
	context.setVariable("invitation", obj);
	return templateEngine.process("email/invitation", context);
    }

}
