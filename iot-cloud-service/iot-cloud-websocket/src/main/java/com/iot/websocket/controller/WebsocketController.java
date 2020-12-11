package com.iot.websocket.controller;

import com.iot.common.core.controller.BaseController;
import com.iot.common.core.domain.R;
import com.iot.websocket.dto.MsgDto;
import com.iot.websocket.dto.MsgStatusChangeDto;
import com.iot.websocket.dto.WsMsgQueryDto;
import com.iot.websocket.service.WebsocketMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rongshuai on 2020/6/5 16:31
 */
@RestController
@RequestMapping("websocket")
@Api("websocket")
public class WebsocketController extends BaseController {

    @Autowired
    private WebsocketMsgService websocketMsgService;

    @ApiOperation(value = "create")
    @PostMapping("create")
    public void createWebsocketMsg (@RequestBody MsgDto msgDto) {
        logger.info("msgDto is {}",msgDto);
        websocketMsgService.SendMsgToFrontend(msgDto);
    }

    @ApiOperation(value = "getWsMsg")
    @PostMapping("getWsMsg")
    public R getWsMsg(@RequestBody WsMsgQueryDto queryDto) {
        logger.info("queryDto is {}",queryDto);
        return result(websocketMsgService.getWebsocketMsg(queryDto));
    }

    @ApiOperation(value = "changeWsMsgStatus")
    @PostMapping("changeWsMsgStatus")
    public R changeWsMsgStatus(@RequestBody MsgStatusChangeDto statusChangeDto) {
        logger.info("statusChangeDto is {}",statusChangeDto);
        return R.data(websocketMsgService.changeMsgStatus(statusChangeDto));
    }
}
