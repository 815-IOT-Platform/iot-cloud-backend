/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MessageTaskQueryDto.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.tpc.dto;


import lombok.Data;

/**
 * The class Message task query dto.
 *
 * @author ananops.com @gmail.com
 */
@Data
public class MessageTaskQueryDto {
	private int yn;
	private Integer taskStatus;
	private int messageStatus;
	private int fetchNum;
	private int shardingItem;
	private int shardingTotalCount;
	private String producerGroup;
	private String createTimeBefore;

}
