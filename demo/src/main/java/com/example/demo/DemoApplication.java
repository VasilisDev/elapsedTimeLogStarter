package com.example.demo;

import gr.bill.springbootelapsedtimestarter.elapsedtime.logging.ElapsedLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DemoApplication {

	@Component
	public static class Example {
		@ElapsedLog
		public int sum(List<Integer> args) {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException ignored) { }

			return args.stream().mapToInt(Integer::valueOf).sum();
		}
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
		applicationContext.getBean(Example.class).sum(List.of(50,50,100,200));
	}
}
