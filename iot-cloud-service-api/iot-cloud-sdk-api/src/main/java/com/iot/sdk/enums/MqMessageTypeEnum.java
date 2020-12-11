/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MqMessageTypeEnum.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.sdk.enums;

/**
 * The enum Mq message type enum.
 *
 * @author ananops.com @gmail.com
 */
public enum MqMessageTypeEnum {
	/**
	 * 生产者.
	 */
	PRODUCER_MESSAGE(10, "生产者"),
	/**
	 * 消费者.
	 */
	CONSUMER_MESSAGE(20, "消费者");

	private int messageType;

	private String value;

	MqMessageTypeEnum(int messageType, String value) {
		this.messageType = messageType;
		this.value = value;
	}

	/**
	 * Message type int.
	 *
	 * @return the int
	 */
	public int messageType() {
		return messageType;
	}

	/**
	 * Value string.
	 *
	 * @return the string
	 */
	public String value() {
		return value;
	}

}
