package demo.customerservice.model;

public class OtpRequestAndResponse {
	private String key;
	private String otp_code;
	private int remaining;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOtp_code() {
		return otp_code;
	}

	public void setOtp_code(String otp_code) {
		this.otp_code = otp_code;
	}

	public int getRemaining() {
		return remaining;
	}

	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

}
