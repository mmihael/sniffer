package ml.mmrc.sniffer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SnifferApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnifferApplication.class, args);
	}

}
