package com.iot.imc.feign;

import com.iot.common.constant.ServiceNameConstants;
import com.iot.common.core.domain.R;
import com.iot.common.core.dto.MessageQueryDto;
import com.iot.imc.feign.factory.RemoteImcMqMessageFullbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by rongshuai on 2020/6/23 18:38
 */
@FeignClient(name = ServiceNameConstants.IMC_SERVICE, fallbackFactory = RemoteImcMqMessageFullbackFactory.class)
public interface RemoteImcMqMessageService {

    @PostMapping(value = "imcApi/message/queryMessageKeyList")
    R queryMessageKeyList(@RequestParam("messageKeyList") List<String> messageKeyList);

    @PostMapping(value = "imcApi/message/queryMessageListWithPage")
    R queryMessageListWithPage(@RequestBody MessageQueryDto messageQueryDto);
}
