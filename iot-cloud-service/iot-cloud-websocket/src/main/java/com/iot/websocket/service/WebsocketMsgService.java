package com.iot.websocket.service;

import com.iot.websocket.domain.AnWebsocketMsg;
import com.iot.websocket.dto.MsgDto;
import com.iot.websocket.dto.MsgStatusChangeDto;
import com.iot.websocket.dto.WsMsgQueryDto;

import java.util.List;

/**
 * Created by rongshuai on 2020/6/5 17:55
 */
public interface WebsocketMsgService {

    void SendMsgToFrontend(MsgDto msg);

    List<AnWebsocketMsg> getWebsocketMsg(WsMsgQueryDto queryDto);

    int changeMsgStatus(MsgStatusChangeDto statusChangeDto);
}
