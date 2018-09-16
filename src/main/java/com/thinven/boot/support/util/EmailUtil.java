package com.thinven.boot.support.util;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public abstract class EmailUtil {

	public static void sendmail(JavaMailSender mailSender, String fromemail, String fromname, String mailtitle,
			String mailtext, String tomail, String toname) {
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, "UTF-8");

			messageHelper.setFrom(fromemail, fromname);
			messageHelper.setSubject(mailtitle);

			messageHelper.setText(mailtext, true);

			messageHelper.setTo(new InternetAddress(tomail, toname, "UTF-8"));

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mailSender.send(msg);
	}

}
