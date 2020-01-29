package demo.customerservice.service;

import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class OtpGenerator {

	private LoadingCache<String, String> cache;

	public OtpGenerator() {

		CacheLoader<String, String> loader = new CacheLoader<String, String>() {

			@Override
			public String load(String key) throws Exception {
				return key;
			}
		};
	
		cache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).build(loader);
		
	}

	public String generateOtp(String secret) {
		Random random = new Random();
		int otp = random.nextInt(900000)+100000;
		String otpPassword = otp + "";
		cache.put(secret, otpPassword);
		return otpPassword;
	}

	public String getOtp(String secretKey) {

			try {
				return cache.get(secretKey);
			} catch (ExecutionException e) {
				//new MessageCode("MessageCode,Text")
				e.printStackTrace();
			}

		return "cannot get otp code";
	}

	public void clearOtp(String secretKey) {
		cache.invalidate(secretKey);
	}
	
	public ConcurrentMap<String, String> getMap() {
		ConcurrentMap<String, String> map = cache.asMap();
		return map;
	}
}
