/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MqConsumerStore.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.sdk.annotation;

import java.lang.annotation.*;


/**
 * 保存消费者消息.
 *
 * @author ananops.com @gmail.com
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MqConsumerStore {

	boolean storePreStatus() default true;
}
