/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：TpcMqTagController.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.tpc.controller;


import com.iot.common.core.controller.BaseController;
import com.iot.common.core.domain.R;
import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.core.dto.UpdateStatusDto;
import com.iot.common.utils.bean.UpdateInfoUtil;
import com.iot.tpc.domain.TpcMqTag;
import com.iot.tpc.service.ITpcMqTagService;
import com.iot.tpc.vo.TpcMqTagVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * The class Tpc mq tag controller.
 *
 * @author ananops.com @gmail.com
 */
@RestController
@RequestMapping(value = "/tag", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - TpcMqTagController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TpcMqTagController extends BaseController {

	@Resource
	private ITpcMqTagService tpcMqTagService;

	/**
	 * 查询MQ Tag列表.
	 *
	 * @param tpcMqTag the tpc mq tag
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/queryTagListWithPage")
	@ApiOperation(httpMethod = "POST", value = "查询MQ-Tag列表")
	public R queryTagListWithPage(@ApiParam(name = "tag", value = "角色信息") @RequestBody TpcMqTag tpcMqTag) {

		logger.info("查询角色列表tpcMqTagQuery={}", tpcMqTag);
		List<TpcMqTagVo> list = tpcMqTagService.listWithPage(tpcMqTag);
		return R.data(new PageInfo<>(list));
	}

	/**
	 * 修改tag状态.
	 *
	 * @param updateStatusDto the update status dto
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/modifyStatusById")
	@ApiOperation(httpMethod = "POST", value = "修改MQ-Tag状态")
	public R modifyProducerStatusById(@ApiParam(value = "修改tag状态") @RequestBody UpdateStatusDto updateStatusDto) {
		logger.info("修改tag状态 updateStatusDto={}", updateStatusDto);
		Long roleId = updateStatusDto.getId();

		LoginAuthDto loginAuthDto = getLoginAuthDto();

		TpcMqTag tag = new TpcMqTag();
		UpdateInfoUtil.setModifyInfo(tag,loginAuthDto);
		tag.setId(roleId);
		tag.setStatus(updateStatusDto.getStatus());

		int result = tpcMqTagService.update(tag);
		return R.data(result);
	}

	/**
	 * 根据Tag ID删除TAG.
	 *
	 * @param id the id
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/deleteById/{id}")
	@ApiOperation(httpMethod = "POST", value = "根据ID删除TAG")
	public R deleteTagById(@ApiParam(value = "Tag ID") @PathVariable Long id) {
		logger.info("删除tag id={}", id);
		int result = tpcMqTagService.deleteTagById(id);
		return R.data(result);
	}
}
