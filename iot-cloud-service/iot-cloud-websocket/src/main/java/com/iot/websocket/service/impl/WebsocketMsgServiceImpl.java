package com.iot.websocket.service.impl;

import com.iot.common.config.PcObjectMapper;
import com.iot.common.exception.BusinessException;
import com.iot.common.utils.bean.UpdateInfoUtil;
import com.iot.websocket.domain.AnWebsocketMsg;
import com.iot.websocket.dto.MsgDto;
import com.iot.websocket.dto.MsgStatusChangeDto;
import com.iot.websocket.dto.WsMsgQueryDto;
import com.iot.websocket.enums.WsMsgStatusEnum;
import com.iot.websocket.mapper.WebsocketMsgMapper;
import com.iot.websocket.service.WebsocketMsgService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rongshuai on 2020/6/5 17:55
 */
@Service
@Slf4j
public class WebsocketMsgServiceImpl implements WebsocketMsgService {

    @Resource
    private SimpMessagingTemplate template;

    @Resource
    private WebsocketMsgMapper websocketMsgMapper;

    @Override
    public void SendMsgToFrontend(MsgDto msgDto){
        try {
//            AnWebsocketMsg anWebsocketMsg = new AnWebsocketMsg();
//            UpdateInfoUtil.setInsertInfo(anWebsocketMsg,msgDto.getUser());
//            ObjectMapper objectMapper = PcObjectMapper.getObjectMapper();
//            String msg = objectMapper.writeValueAsString(msgDto.getMsg());
//            anWebsocketMsg.setMsg(msg);
//            anWebsocketMsg.setMsgType(msgDto.getMsgType());
//            anWebsocketMsg.setUserId(Long.valueOf(msgDto.getId()));
//            anWebsocketMsg.setStatus(WsMsgStatusEnum.WAITING_FOR_CONSUME.getStatusNum());
//            websocketMsgMapper.insert(anWebsocketMsg);
            String dest = "/queue/" + msgDto.getMsgType() + "/" + msgDto.getId();
            log.info("dest is #{}",dest);
            template.convertAndSend(dest,msgDto);
        } catch (Exception e) {
            throw new BusinessException("消息推送失败");
        }
    }

    @Override
    public List<AnWebsocketMsg> getWebsocketMsg(WsMsgQueryDto queryDto) {
        Long userId = queryDto.getUserId();
        Integer status = queryDto.getStatus();
        String msgType = queryDto.getMsgType();
        return websocketMsgMapper.getWebsocketMsg(userId,status,msgType);
    }

    @Override
    public int changeMsgStatus(MsgStatusChangeDto statusChangeDto) {
        Long id = statusChangeDto.getId();
        Integer status = statusChangeDto.getStatus();
        if (null != id && null != status) {
            if (status == WsMsgStatusEnum.WAITING_FOR_CONSUME.getStatusNum() || status == WsMsgStatusEnum.CONSUMED.getStatusNum()) {
                AnWebsocketMsg anWebsocketMsg = new AnWebsocketMsg();
                anWebsocketMsg.setStatus(status);
                anWebsocketMsg.setId(id);
                return websocketMsgMapper.updateByPrimaryKeySelective(anWebsocketMsg);
            } else {
                throw new BusinessException("参数异常");
            }
        } else {
            throw new BusinessException("参数异常");
        }
    }
}
