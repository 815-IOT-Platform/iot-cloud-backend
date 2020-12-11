/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MqOrderTypeEnum.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.sdk.enums;

/**
 * The enum Mq send type enum.
 *
 * @author ananops.com @gmail.com
 */
public enum MqOrderTypeEnum {
	/**
	 * 有序.
	 */
	ORDER(1),
	/**
	 * 无序.
	 */
	DIS_ORDER(0);

	/**
	 * The Order type.
	 */
	int orderType;

	MqOrderTypeEnum(final int orderType) {
		this.orderType = orderType;
	}

	/**
	 * Order type int.
	 *
	 * @return the int
	 */
	public int orderType() {
		return orderType;
	}
}
