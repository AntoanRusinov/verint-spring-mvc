package service;

import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	 @Scheduled(fixedRate = 3000l)
//	@Scheduled(cron = "0/3 * * * * *")
	public void doSomethingScheduled() {
		System.out.println("I'm a scheduler... and I'm activated now "
				+ new DateTime(System.currentTimeMillis()).toString() + " !");
	}

}