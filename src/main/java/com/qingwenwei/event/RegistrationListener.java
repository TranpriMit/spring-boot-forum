package com.qingwenwei.event;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.qingwenwei.persistence.dao.UserMapper;
import com.qingwenwei.persistence.dao.VerificationTokenMapper;
import com.qingwenwei.persistence.model.User;
import com.qingwenwei.persistence.model.VerificationToken;
import com.qingwenwei.util.EmailService;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	private static final String VERIFICATION_FROM_EMAIL_ADDR = "";
	
	private static final String VERIFICATION_EMAIL_SUBJECT = "";
	
	private static final String VERIFICATION_EMAIL_TEXT = "";
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrationListener.class);
	
	@Autowired
	private VerificationTokenMapper verificationTokenMapper;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}
	
	private void confirmRegistration(final OnRegistrationCompleteEvent event) {
		logger.info("confirmRegistration() >> " + event);
		String username = event.getUsername();
		this.createUserVerificationToken(username);
	}
	
	private void createUserVerificationToken(String username) {
		String token = UUID.randomUUID().toString(); // token string
		User user = this.userMapper.findByUsername(username);
		VerificationToken verificationToken = new VerificationToken(user, token);
		System.out.println("Saved verification token >> " + verificationToken);
		this.verificationTokenMapper.save(verificationToken);
		
		// send verification email
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom(VERIFICATION_FROM_EMAIL_ADDR);
		email.setSubject(VERIFICATION_EMAIL_SUBJECT);
		email.setText(VERIFICATION_EMAIL_TEXT);
		
		
	}
}