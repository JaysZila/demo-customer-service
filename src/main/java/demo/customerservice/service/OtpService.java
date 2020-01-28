package demo.customerservice.service;

import java.util.concurrent.ConcurrentMap;

import javax.mail.MessagingException;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.customerservice.model.OtpRequestAndResponse;

@Service
public class OtpService {
	@Autowired
	private OtpGenerator otpGenerator;
	@Autowired
	private EmailService emailService;

	private final int REMAINING = 3;

	public OtpRequestAndResponse generateOtpPassword(String secretKey) {
		OtpRequestAndResponse otpObj = new OtpRequestAndResponse();
		otpObj.setKey(secretKey);
		otpObj.setRemaining(REMAINING);

		String otp_code = otpGenerator.generateOtp(secretKey);
		otpObj.setOtp_code(otp_code);
		try {
			emailService.sendEmailWithAttachment(otp_code, REMAINING);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return otpObj;
	}

	public String getOtpCode(String secretKey) {
		String otp_code = otpGenerator.getOtp(secretKey);
		if (otp_code == secretKey) {
			return "otp code has already expired";
		}
		return otp_code;
	}

	public boolean validate(String secretKey, String inputOtp, int remaining) {
		if (remaining > 0) {
			String otpPass = otpGenerator.getOtp(secretKey);
			if (otpPass.contentEquals(inputOtp)) {
				otpGenerator.clearOtp(secretKey);
				return true;
			}
			remaining--;

			return false;
		}
		otpGenerator.clearOtp(secretKey);
		return false;

	}

	public String checkAvailableKey(String secretKey) {
		if (otpGenerator.getOtp(secretKey) == null) {
			return "key has null value";
		}

		return otpGenerator.getOtp(secretKey);

	}

	public String clearOtp(String secretKey) {
		otpGenerator.clearOtp(secretKey);
		return "cleared otp";
	}

	public ConcurrentMap<String, String> getConCurrentMap() {
		return otpGenerator.getMap();
	}
}
