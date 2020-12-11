/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：TpcMqConfirmVo.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.tpc.vo;


import com.iot.common.core.dto.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The class Tpc mq confirm.
 *
 * @author ananops.com @gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TpcMqConfirmVo extends BaseVo {
	private static final long serialVersionUID = 8497361355200965902L;

	/**
	 * 任务ID
	 */
	private Long messageId;

	/**
	 * 消息唯一标识
	 */
	private String messageKey;

	/**
	 * 消费者组编码
	 */
	private String consumerCode;

	/**
	 * 消费的数次
	 */
	private Integer consumeCount;

	/**
	 * 状态, 10 - 未确认 ; 20 - 已确认; 30 已消费
	 */
	private Integer status;
}