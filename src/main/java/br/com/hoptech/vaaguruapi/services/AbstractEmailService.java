package br.com.hoptech.vaaguruapi.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.hoptech.vaaguruapi.domain.Rower;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

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

}
