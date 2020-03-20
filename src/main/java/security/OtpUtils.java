package security;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import database.SessionManager;
import model.Otp;
import model.User;


@Component(value = "otpUtils")
public class OtpUtils {
	@Value("${app.otp.secret}")
	private String SECRET;
	
	public static final Integer EXPIRE_TIME = 5;

	public String generateOtp(User user, String ipAddress, Integer mode) throws Exception {
		Otp otp = new Otp();
		Date creationDate = new Date();
		Calendar c = Calendar.getInstance();
        c.setTime(creationDate);
        c.add(Calendar.DATE, 7);
		Date expiryDate = c.getTime();
		
		String token = null;
		if (mode == 1) {
			token = UUID.randomUUID().toString();
		} else if (mode == 0) {
			Random random = new Random();
			Integer randomNum = 100000 + random.nextInt(900000);
			token = randomNum.toString();
		} else {
			throw new Exception("Invalid communication mode for OTP.");
		}
		
		Session s = SessionManager.getSession("");
		
		Transaction tx = null;
		try {
			tx = s.beginTransaction();

			otp.setMode(mode);
			otp.setOtpKey(token);
			otp.setCompleted(false);
			otp.setCreationDate(creationDate);
			otp.setExpiryDate(expiryDate);
			otp.setInitator(user.getId());
			otp.setIpAddress(ipAddress);
		    System.out.println("Saving: " + ipAddress);
			
			s.save(otp);
			
			if (tx.isActive())
			    tx.commit();
		
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			s.close();
		}
		
		return token;
	}
	
	public User validateOtp(String token, String ipAddress) {
		Date currentDate = new Date();
		Session s = SessionManager.getSession("");
		
		Otp otp = null;
		Transaction tx = null;
		User u = null;
		
		try {
			otp = s.createQuery("FROM Otp WHERE otp_key = :key", Otp.class)
					.setParameter("key", token).getSingleResult();
				
			if (otp.getCompleted() || currentDate.after(otp.getExpiryDate()) || !otp.getIpAddress().equals(ipAddress)) {
			    System.out.println(otp.getCompleted());
			    System.out.println(currentDate.after(otp.getExpiryDate()));
			    System.out.println(otp.getIpAddress());
			    System.out.println(ipAddress);
			} else {
				u = s.createQuery("FROM User WHERE id = :id AND status = 1", User.class)
						.setParameter("id", otp.getInitator()).getSingleResult();
				
				tx = s.beginTransaction();

				otp.setCompleted(true);
				s.update(otp);

				if (tx.isActive())
				    tx.commit();
			}

		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
			return null;

		} finally {
			s.close();
		}
		
		return u;
	}
}
