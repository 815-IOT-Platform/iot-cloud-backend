package com.iot.imc.controller;

import com.iot.common.core.controller.BaseController;
import com.iot.common.core.domain.R;
import com.iot.common.core.dto.MessageQueryDto;
import com.iot.sdk.service.MqMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by rongshuai on 2020/6/23 18:27
 */
@RestController
@RequestMapping("imcApi")
@Api("imc消息状态同步Feign api")
public class IotImcMqMessageFeignController extends BaseController {
    @Autowired
    private MqMessageService mqMessageService;

    @ApiOperation(value = "查询含有的messageKey")
    @PostMapping(value = "message/queryMessageKeyList")
    public R queryMessageKeyList(@RequestParam("messageKeyList") List<String> messageKeyList) {
        logger.info("查询消息KEY. messageKeyList={}", messageKeyList);
        return R.data(mqMessageService.queryMessageKeyList(messageKeyList));
    }

    @ApiOperation(value = "查询含有的messageKey分页")
    @PostMapping(value = "message/queryMessageListWithPage")
    public R queryMessageListWithPage(@RequestBody MessageQueryDto messageQueryDto) {
        logger.info("查询消息KEY. messageQueryDto={}", messageQueryDto);
        return mqMessageService.queryMessageListWithPage(messageQueryDto);
    }

}
