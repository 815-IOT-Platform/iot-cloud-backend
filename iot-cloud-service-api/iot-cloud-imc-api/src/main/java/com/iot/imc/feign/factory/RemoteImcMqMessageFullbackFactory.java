package com.iot.imc.feign.factory;

import com.iot.common.core.domain.R;
import com.iot.common.core.dto.MessageQueryDto;
import com.iot.imc.feign.RemoteImcMqMessageService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by rongshuai on 2020/6/23 18:39
 */
@Slf4j
@Component
public class RemoteImcMqMessageFullbackFactory implements FallbackFactory<RemoteImcMqMessageService> {
    @Override
    public RemoteImcMqMessageService create(Throwable throwable) {
        log.error(throwable.getMessage());
        return new RemoteImcMqMessageService() {
            @Override
            public R queryMessageKeyList(List<String> messageKeyList) {
                return null;
            }

            @Override
            public R queryMessageListWithPage(MessageQueryDto messageQueryDto) {
                return null;
            }
        };
    }
}
