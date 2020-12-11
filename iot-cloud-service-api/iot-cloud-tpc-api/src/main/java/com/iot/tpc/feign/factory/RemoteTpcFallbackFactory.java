package com.iot.tpc.feign.factory;

import com.iot.common.core.domain.R;
import com.iot.tpc.dto.TpcMqMessageDto;
import com.iot.tpc.feign.RemoteTpcService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by rongshuai on 2020/6/17 21:46
 */
@Slf4j
@Component
public class RemoteTpcFallbackFactory implements FallbackFactory<RemoteTpcService> {
    @Override
    public RemoteTpcService create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new RemoteTpcService()
        {
            @Override
            public R saveMessageWaitingConfirm(@RequestBody TpcMqMessageDto mqMessageDto) {
                return null;
            }

            @Override
            public R confirmAndSendMessage(@RequestParam("messageKey") String messageKey) {
                return null;
            }

            @Override
            public R saveAndSendMessage(@RequestBody TpcMqMessageDto mqMessageDto) {
                return null;
            }

            @Override
            public R directSendMessage(@RequestBody TpcMqMessageDto mqMessageDto) {
                return null;
            }

            @Override
            public R deleteMessageByMessageKey(@RequestParam("messageKey") String messageKey) {
                return null;
            }

            @Override
            public R confirmReceiveMessage(@RequestParam("cid") final String cid, @RequestParam("messageKey") final String messageKey) {
                return null;
            }

            @Override
            public R confirmConsumedMessage(@RequestParam("cid") final String cid, @RequestParam("messageKey") final String messageKey) {
                return null;
            }
        };
    }
}
