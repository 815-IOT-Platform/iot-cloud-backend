/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MqSendTypeEnum.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.sdk.enums;

/**
 * The enum Mq send type enum.
 *
 * @author ananops.com @gmail.com
 */
public enum MqSendTypeEnum {
	/**
	 * 等待确认.
	 */
	WAIT_CONFIRM,

	/**
	 * 直接发送.
	 */
	SAVE_AND_SEND,

	/**
	 * 直接发送
	 */
	//TODO 消费切口 有问题, 日后修复 暂时不可用
	@Deprecated
	DIRECT_SEND
}
