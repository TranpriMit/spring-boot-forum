package com.qingwenwei.event;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

	private final String username;

	public OnRegistrationCompleteEvent(String username, String appUrl, Locale locale) {
		super(username);
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
