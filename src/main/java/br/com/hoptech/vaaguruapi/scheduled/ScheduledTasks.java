package br.com.hoptech.vaaguruapi.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(fixedRate = 5000)
    //https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
    //http://www.nncron.ru/help/EN/working/cron-format.htm
    @Scheduled(cron="0 0/10 9,19 * * *")
    public void reportCurrentTime() {
	LOGGER.info("The time is now {}", dateFormat.format(new Date()));
    }
}
