/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：RedisService.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.system.service;

import java.util.concurrent.TimeUnit;

/**
 * The interface Redis service.
 *
 * @author ananops.com@gmail.com
 */
public interface RedisService {

	/**
	 * Gets key.
	 *
	 * @param key the key
	 *
	 * @return the key
	 */
	String getKey(String key);

	/**
	 * Delete key.
	 *
	 * @param key the key
	 */
	void deleteKey(String key);

	/**
	 * Sets key.
	 *
	 * @param key   the key
	 * @param value the value
	 */
	void setKey(String key, String value);

	/**
	 * Sets key.
	 *
	 * @param key     the key
	 * @param value   the value
	 * @param timeout the timeout
	 * @param unit    the unit
	 */
	void setKey(String key, String value, final long timeout, final TimeUnit unit);
}
