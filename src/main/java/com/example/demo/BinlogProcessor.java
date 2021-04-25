package com.example.demo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface BinlogProcessor {

	static final String BINLOG = "binlog";

	@Input(BINLOG)
	SubscribableChannel input();

}
