package com.curso.mc.service;

import org.springframework.mail.SimpleMailMessage;

import com.curso.mc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);

}
