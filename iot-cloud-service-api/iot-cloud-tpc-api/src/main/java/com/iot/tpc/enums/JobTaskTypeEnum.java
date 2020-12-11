/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：JobTaskTypeEnum.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.tpc.enums;


/**
 * The enum Job task type enum.
 *
 * @author ananops.com @gmail.com
 */
public enum JobTaskTypeEnum {

	/**
	 * Mq send message job task type enum.
	 */
	MQ_SEND_MESSAGE("MQ_SEND_MESSAGE", "发送可靠消息"),;
	/**
	 * The Type.
	 */
	String type;

	/**
	 * The Value.
	 */
	String value;

	JobTaskTypeEnum(String type, String value) {
		this.type = type;
		this.value = value;
	}

	public String type() {
		return type;
	}

	public String value() {
		return value;
	}

}
