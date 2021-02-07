package com.iot.device.manager.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.iot.common.core.mq.MqMessage;
import com.iot.common.redis.util.RedisUtils;
import com.iot.device.config.DictConfig;
import com.iot.device.dto.DeviceDto;
import com.iot.device.util.JacksonUtil;
import com.iot.websocket.dto.MsgDto;
import com.iot.websocket.feign.RemoteWebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huqiaoqian on 2020/10/28
 */
@Slf4j
@Service
public class TopicConsumer {
    @Autowired
    private RemoteWebSocketService remoteWebSocketService;
    @Autowired
    private DictConfig dictConfig;
    @Autowired
    private RedisUtils redisUtils;

    public void handlerSendMqMsg(String body, String topicName, String tags, String keys){
        log.info("handlerSendMqMsg:body={},topicName={},tags={},keys={}",body,topicName,tags,keys);
        DeviceDto deviceDto;
        try {
            deviceDto = JacksonUtil.parseJson(body, DeviceDto.class);
        } catch (IOException e) {
            log.error("发送短信MQ出现异常={}", e.getMessage(), e);
            throw new IllegalArgumentException("JSON转换异常", e);
        }
        if(deviceDto==null){
            log.error("消息体为空");
            return;
        }
        MsgDto<DeviceDto> msgDto = new MsgDto<>();
        Map<String,String> properties=deviceDto.getProperties();
        Map<String,String> dictPro=new HashMap<>();
        if(properties!=null&&dictConfig!=null&&dictConfig.getDeviceDict()!=null){
            properties.forEach((k,v) -> {
                dictPro.put(dictConfig.getDeviceDict().getOrDefault(k,k),v);
            });
        }
        deviceDto.setProperties(dictPro);
        msgDto.setMsg(deviceDto);
        msgDto.setMsgType("Device");
        msgDto.setId(deviceDto.getDeviceName());
        remoteWebSocketService.createWebsocketMsg(msgDto);
        //TODO:将设备数据传给规则，进行处理

        // 同时存储到 Redis 中
        // 注意设置唯一 key，并且与设备相对应
        // 用 deviceName 作为 key
        System.out.println(deviceDto);
        Map<String, Object> map = new HashMap();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put(df.format(new Date()),deviceDto);
        redisUtils.set("device_data_" + deviceDto.getDeviceName(), JSON.toJSONString(map));
        System.out.println("-------------------查看Redis中的数据---------------------");
        System.out.println(redisUtils.get("device_data_ble-watch"));
        System.out.println("--------------------------------------------------------");
    }
}
