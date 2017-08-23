package com.callcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.callcenter.dispatcher.Dispatcher;
import com.callcenter.factory.ReportFactory;

@SpringBootApplication
public class CallCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallCenterApplication.class, args);
		
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.dispatchCall();
		ReportFactory.report(dispatcher);

	}
}
