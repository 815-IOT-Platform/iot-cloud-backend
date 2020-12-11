package com.iot.system.service;

import com.iot.system.domain.MailSendLog;

import java.util.Date;
import java.util.List;

/**
 * Created By ChengHao On 2020/6/3
 */
public  interface IMailSendLogService {
    /**
     * 更新消息状态
     * @param msgId
     * @param status
     * @return
     */
     Integer updateMailSendLogStatus(String msgId, Integer status);

    /**
     * 添加消息日志
     * @param mailSendLog
     * @return
     */
   Integer insert(MailSendLog mailSendLog) ;

    /**
     * 获取未投递成功的消息
     * @return
     */
    List<MailSendLog> getMailSendLogsByStatus() ;

    /**
     * 更新重试次数
     * @param msgId
     * @param date
     * @return
     */
    Integer updateCount(String msgId, Date date);
}
