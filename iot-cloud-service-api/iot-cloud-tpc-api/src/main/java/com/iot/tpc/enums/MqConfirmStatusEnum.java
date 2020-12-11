/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MqConfirmStatusEnum.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.tpc.enums;

/**
 * The enum Mq confirm status enum.
 *
 * @author ananops.com @gmail.com
 */
public enum MqConfirmStatusEnum {
	/**
	 * 未确认.
	 */
	UN_CONFIRMED(10, "未确认"),

	/**
	 * 已确认.
	 */
	CONFIRMED(20, "已确认"),

	/**
	 * 已消费
	 */
	CONSUMED(30, "已消费");

	private int confirmStatus;

	private String value;

	MqConfirmStatusEnum(int confirmStatus, String value) {
		this.confirmStatus = confirmStatus;
		this.value = value;
	}

	/**
	 * Confirm status int.
	 *
	 * @return the int
	 */
	public int confirmStatus() {
		return confirmStatus;
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
