package com.iot.tpc.service.impl;

import com.iot.common.core.domain.R;
import com.iot.common.exception.BusinessException;
import com.iot.imc.feign.RemoteImcMqMessageService;
import com.iot.tpc.service.ImcRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rongshuai on 2020/6/23 18:24
 */
@Service
@Slf4j
public class ImcRpcServiceImpl implements ImcRpcService {
    @Autowired
    private RemoteImcMqMessageService remoteImcMqMessageService;

    @Override
    public List<String> queryWaitingConfirmMessageKeyList(List<String> messageKeyList) {
        R result = remoteImcMqMessageService.queryMessageKeyList(messageKeyList);
        if (result == null) {
            log.error("queryWaitingConfirmMessageKeyList 失败 result is null");
            throw new BusinessException("微服务不在线,或者网络超时");
        }
        return (List<String>) result.get("data");
    }

}
