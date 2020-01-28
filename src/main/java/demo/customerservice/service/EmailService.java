package demo.customerservice.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private OtpGenerator otpService;

	public void sendEmail() {
		SimpleMailMessage msg = new SimpleMailMessage();
		List<String> emailList = new ArrayList<String>();
		emailList.add("methaporn24988@gmail.com");
		emailList.add("methaporn.jz@mail.kmutt.ac.th");

		for (String email : emailList) {
			msg.setTo(email);
			msg.setSubject("Verify Your Account");
			msg.setFrom("methaporn.jz@mail.kmutt.ac.th");
			msg.setText(new File("src/main/resources/image/kmutt_poster").getAbsolutePath());
			msg.setSentDate(new Date());
			javaMailSender.send(msg);
		}
	}

	public String sendEmailWithAttachment(String otp,int remaining) throws MessagingException {
//Random String 
//		int leftLimit = 97; // letter 'a'
//		int rightLimit = 122; // letter 'z'
//		int targetStringLength = 6;
//		Random random = new Random();
//
//		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
//				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		String text = "<h1>Verify Your account</h1>\r\n" + "<div>\r\n" + otp + "</div>";
		List<String> emailList = new ArrayList<String>();
//		emailList.add("methaporn24988@gmail.com");
		emailList.add("methaporn.jz@mail.kmutt.ac.th");
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		FileSystemResource file = new FileSystemResource(
				new File("src/main/resources/image/kmutt_poster.jpg").getAbsolutePath());
		try {
			for (String email : emailList) {
				msg.setSubject("[SAMOSIT 2019] Please verify your account");
				msg.setFrom(new InternetAddress("pr@wipcamp.com", false));
				helper.setTo(email);
				helper.setText(text, true);
				helper.addAttachment("kmutt_poster.jpg", file);
				javaMailSender.send(msg);
				return "Send email succesfully";
			}
		} catch (SendFailedException e) {
			return "Sent email to destination failed" ;
		}
		return "Success";

	}

}