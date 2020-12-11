/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：TpcMessageMainController.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.tpc.controller;


import com.iot.common.core.controller.BaseController;
import com.iot.common.core.domain.R;
import com.iot.common.core.dto.MessageQueryDto;
import com.iot.common.utils.StringUtils;
import com.iot.tpc.service.ITpcMqMessageService;
import com.iot.tpc.vo.TpcMessageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 异常管理.
 *
 * @author ananops.com @gmail.com
 */
@RestController
@RequestMapping(value = "/message", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - TpcMessageMainController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TpcMessageMainController extends BaseController {
	@Resource
	private ITpcMqMessageService tpcMqMessageService;

//	/**
//	 * 异常日志列表.
//	 *
//	 * @param messageQueryDto the message query dto
//	 *
//	 * @return the wrapper
//	 */
//	@PostMapping(value = "/queryRecordListWithPage")
//	@ApiOperation(httpMethod = "POST", value = "分页查询各中心落地消息记录")
//	public R queryRecordListWithPage(@ApiParam(name = "tpcMessageQueryDto") @RequestBody MessageQueryDto messageQueryDto) {
//		logger.info("分页查询各中心落地消息记录. messageQueryDto={}", messageQueryDto);
//		return tpcMqMessageService.queryRecordListWithPage(messageQueryDto);
//	}

	/**
	 * Resend message by id wrapper.
	 *
	 * @param messageId the message id
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/resendMessageById/{messageId}")
	@ApiOperation(httpMethod = "POST", value = "重发消息")
	public R resendMessageById(@PathVariable Long messageId) {
		logger.info("重发消息. messageId={}", messageId);
		tpcMqMessageService.resendMessageByMessageId(messageId);
		return R.ok();
	}

	/**
	 * Query reliable list with page wrapper.
	 *
	 * @param messageQueryDto the message query dto
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/queryReliableListWithPage")
	@ApiOperation(httpMethod = "POST", value = "分页查询可靠消息")
	public R queryReliableListWithPage(@ApiParam(name = "tpcMessageQueryDto") @RequestBody MessageQueryDto messageQueryDto) {
		logger.info("分页查询可靠消息. tpcMessageQueryDto={}", messageQueryDto);
		PageHelper.startPage(messageQueryDto.getPageNum(), messageQueryDto.getPageSize());
		messageQueryDto.setIsAsc("desc");
		List<TpcMessageVo> list = tpcMqMessageService.listReliableMessageVo(messageQueryDto);
		PageInfo<TpcMessageVo> pageInfo = new PageInfo<>(list);
		if (StringUtils.isNotEmpty(list)) {
			Map<Long, TpcMessageVo> messageVoMap = this.trans2Map(list);
			List<Long> messageIdList = new ArrayList<>(messageVoMap.keySet());

			List<TpcMessageVo> mqConfirmVoList = tpcMqMessageService.listReliableMessageVo(messageIdList);
			for (TpcMessageVo vo : mqConfirmVoList) {
				Long subscribeId = vo.getId();
				if (!messageVoMap.containsKey(subscribeId)) {
					continue;
				}
				TpcMessageVo tpcMessageVo = messageVoMap.get(subscribeId);
				tpcMessageVo.setMqConfirmVoList(vo.getMqConfirmVoList());
			}
			pageInfo.setList(new ArrayList<>(messageVoMap.values()));
		}
		return R.data(pageInfo);
	}

	private Map<Long, TpcMessageVo> trans2Map(List<TpcMessageVo> tpcMessageVoList) {
		Map<Long, TpcMessageVo> resultMap = new TreeMap<>((o1, o2) -> {
			o1 = o1 == null ? 0 : o1;
			o2 = o2 == null ? 0 : o2;
			return o2.compareTo(o1);
		});
		for (TpcMessageVo vo : tpcMessageVoList) {
			resultMap.put(vo.getId(), vo);
		}
		return resultMap;
	}
}
