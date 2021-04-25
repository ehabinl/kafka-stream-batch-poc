package com.example.demo;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author ehakawati
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BinlogMessage {

	private String database;
	private String table;
	private String type;
	private Map<String, Object> data;
	private Map<String, Object> old;

	@JsonProperty("ts")
	private long timestamp;

	@JsonProperty("xid")
	private long transactionId;

}