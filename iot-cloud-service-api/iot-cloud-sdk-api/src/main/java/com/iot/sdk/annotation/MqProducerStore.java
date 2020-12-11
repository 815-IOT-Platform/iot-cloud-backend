/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MqProducerStore.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.sdk.annotation;


import com.iot.sdk.enums.DelayLevelEnum;
import com.iot.sdk.enums.MqOrderTypeEnum;
import com.iot.sdk.enums.MqSendTypeEnum;

import java.lang.annotation.*;


/**
 * 保存生产者消息.
 *
 * @author ananops.com @gmail.com
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MqProducerStore {
	//WAIT_CONFIRM等待发送 SAVE_AND_SEND直接发送
	MqSendTypeEnum sendType() default MqSendTypeEnum.WAIT_CONFIRM;
	//PRDER(1)有序 DIS_ORDER(0)无序
	MqOrderTypeEnum orderType() default MqOrderTypeEnum.ORDER;
	//Rocketmq默认延时级别
	DelayLevelEnum delayLevel() default DelayLevelEnum.ZERO;
}
