package com.iot.system.service.impl;

import com.iot.system.domain.MailSendLog;
import com.iot.system.mapper.MailSendLogMapper;
import com.iot.system.service.IMailSendLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created By ChengHao On 2020/6/3
 */
@Service
public class IMailSendLogServiceImpl implements IMailSendLogService {
    @Resource
    MailSendLogMapper mailSendLogMapper;

    /**
     * 更新消息状态
     * @param msgId
     * @param status
     * @return
     */
    public Integer updateMailSendLogStatus(String msgId, Integer status) {
        return mailSendLogMapper.updateMailSendLogStatus(msgId, status);
    }

    /**
     * 添加消息日志
     * @param mailSendLog
     * @return
     */
    public Integer insert(MailSendLog mailSendLog) {
        return mailSendLogMapper.insert(mailSendLog);
    }

    /**
     * 获取未投递成功的消息
     * @return
     */
    public List<MailSendLog> getMailSendLogsByStatus() {
        return mailSendLogMapper.getMailSendLogsByStatus();
    }

    /**
     * 更新重试次数
     * @param msgId
     * @param date
     * @return
     */
    public Integer updateCount(String msgId, Date date) {
        return mailSendLogMapper.updateCount(msgId,date);
    }
}
