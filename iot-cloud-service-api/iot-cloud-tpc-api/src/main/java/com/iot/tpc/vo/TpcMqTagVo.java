/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：TpcMqTagVo.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.tpc.vo;

import com.iot.common.core.dto.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The class Mdc mq tag vo.
 *
 * @author ananops.com @gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TpcMqTagVo extends BaseVo {
	private static final long serialVersionUID = -4176095424582783954L;
	/**
	 * 主题ID
	 */
	private Long topicId;

	/**
	 * 主题编码
	 */
	private String topicCode;

	/**
	 * 主题名称
	 */
	private String topicName;

	/**
	 * 城市编码
	 */
	private String tagCode;

	/**
	 * 区域编码
	 */
	private String tagName;

	/**
	 * 状态, 0生效,10,失效
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;
}
