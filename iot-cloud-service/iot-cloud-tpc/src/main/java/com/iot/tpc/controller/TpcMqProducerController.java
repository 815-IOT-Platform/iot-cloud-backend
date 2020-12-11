/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：TpcMqProducerController.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.tpc.controller;

import com.iot.common.core.controller.BaseController;
import com.iot.common.core.domain.R;
import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.core.dto.UpdateStatusDto;
import com.iot.common.utils.bean.UpdateInfoUtil;
import com.iot.tpc.domain.TpcMqProducer;
import com.iot.tpc.dto.AddMqProducerDto;
import com.iot.tpc.service.ITpcMqProducerService;
import com.iot.tpc.vo.TpcMqProducerVo;
import com.iot.tpc.vo.TpcMqPublishVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 生产者管理.
 *
 * @author ananops.com @gmail.com
 */
@RestController
@RequestMapping(value = "/producer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - TpcMqProducerController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TpcMqProducerController extends BaseController {

	@Resource
	private ITpcMqProducerService tpcMqProducerService;

	@PostMapping(value = "/addProducer")
	@ApiOperation(httpMethod = "POST", value = "创建一个生产者")
	public R addProducer(@ApiParam(name = "producer", value = "Mq生产者") @RequestBody AddMqProducerDto addMqProducerDto){
		LoginAuthDto loginAuthDto = super.getLoginAuthDto();
		logger.info("创建生产者addMqProducerDto={}",addMqProducerDto);
		return R.data(tpcMqProducerService.addProducer(addMqProducerDto,loginAuthDto));
	}

	/**
	 * 查询生产者列表.
	 *
	 * @param tpcMqProducer the tpc mq producer
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/queryProducerVoListWithPage")
	@ApiOperation(httpMethod = "POST", value = "查询生产者列表")
	public R queryProducerList(@ApiParam(name = "producer", value = "Mq生产者") @RequestBody TpcMqProducer tpcMqProducer) {

		logger.info("查询生产者列表tpcMqTopicQuery={}", tpcMqProducer);
		List<TpcMqProducerVo> list = tpcMqProducerService.listProducerVoWithPage(tpcMqProducer);
		return R.data(list);
	}

	/**
	 * 查询发布者列表.
	 *
	 * @param tpcMqProducer the tpc mq producer
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/queryPublishListWithPage")
	@ApiOperation(httpMethod = "POST", value = "查询发布者列表")
	public R queryPublishListWithPage(@ApiParam(name = "producer", value = "Mq生产者") @RequestBody TpcMqProducer tpcMqProducer) {
		logger.info("查询Mq发布列表tpcMqTopicQuery={}", tpcMqProducer);
		List<TpcMqPublishVo> list = tpcMqProducerService.listPublishVoWithPage(tpcMqProducer);
		return R.data(new PageInfo<>(list));
	}

	/**
	 * 修改生产者状态.
	 *
	 * @param updateStatusDto the update status dto
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/modifyStatusById")
	@ApiOperation(httpMethod = "POST", value = "修改生产者状态")
	public R modifyProducerStatusById(@ApiParam(value = "修改producer状态") @RequestBody UpdateStatusDto updateStatusDto) {
		logger.info("修改producer状态 updateStatusDto={}", updateStatusDto);
		Long roleId = updateStatusDto.getId();

		LoginAuthDto loginAuthDto = getLoginAuthDto();

		TpcMqProducer producer = new TpcMqProducer();
		UpdateInfoUtil.setModifyInfo(producer,loginAuthDto);
		producer.setId(roleId);
		producer.setStatus(updateStatusDto.getStatus());

		int result = tpcMqProducerService.update(producer);
		return R.data(result);
	}

	/**
	 * 根据生产者ID删除生产者.
	 *
	 * @param id the id
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/deleteById/{id}")
	@ApiOperation(httpMethod = "POST", value = "根据生产者ID删除生产者")
	public R deleteProducerById(@PathVariable Long id) {
		logger.info("删除producer id={}", id);
		int result = tpcMqProducerService.deleteProducerById(id);
		return R.data(result);
	}
}
