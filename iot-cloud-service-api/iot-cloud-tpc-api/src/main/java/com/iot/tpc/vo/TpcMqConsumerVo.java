/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：TpcMqConsumerVo.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.tpc.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * The class Mdc mq consumer vo.
 *
 * @author ananops.com @gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TpcMqConsumerVo extends TpcMqSubscribeVo {
	private static final long serialVersionUID = 8833595265432073801L;

	/**
	 * 订阅Topic集合
	 */
	private List<TpcMqTopicVo> mqTopicVoList;

}
