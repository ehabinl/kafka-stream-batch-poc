package com.example.demo;

import org.apache.kafka.common.serialization.Deserializer;

/**
 * 
 * @author ehakawati
 *
 */
public final class BinLogDeserializer implements Deserializer<BinlogMessage> {

	@Override
	public BinlogMessage deserialize(String topic, byte[] data) {

		return JsonUtils.decode(new String(data), BinlogMessage.class);
	}

}
