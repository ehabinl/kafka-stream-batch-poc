package com.example.demo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

@SpringBootApplication
@EnableBinding(BinlogProcessor.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		//System.out.println(JsonUtils.encode(new BinlogMessage()));
	}

	@StreamListener(BinlogProcessor.BINLOG)
	public void listen(List<BinlogMessage> message, @Header(KafkaHeaders.ACKNOWLEDGMENT) Acknowledgment ack) {
		System.out.println(message);
		ack.acknowledge();
	}

	
}
