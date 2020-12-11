
package com.iot.tpc.job.dataflow;


import com.iot.common.utils.DateUtil;
import com.iot.elastic.lite.JobParameter;
import com.iot.elastic.lite.annotation.ElasticJobConfig;
import com.iot.elastic.lite.job.AbstractBaseDataflowJob;
import com.iot.tpc.dto.MessageTaskQueryDto;
import com.iot.tpc.enums.JobTaskStatusEnum;
import com.iot.tpc.enums.MqSendStatusEnum;
import com.iot.tpc.service.ITpcMqMessageService;
import com.iot.tpc.service.ImcRpcService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 处理待确认的消息数据.
 *
 * @author ananops.com @gmail.com
 */
@Slf4j
@Component
@ElasticJobConfig(cron = "0 0/10 * * * ?", jobParameter = "fetchNum=1000")
public class HandleWaitingConfirmMessageJob extends AbstractBaseDataflowJob<String> {
	@Autowired
	private ITpcMqMessageService tpcMqMessageService;

	@Autowired
	private ImcRpcService imcRpcService;

	@Value("${ananops.message.handleTimeout}")
	private int timeOutMinute;
	private static final String PID_CLOUD_IMC = "PID_CLOUD_IMC";

	/**
	 * Fetch job data list.
	 *
	 * @param jobParameter the job parameter
	 *
	 * @return the list
	 */
	@Override
	protected List<String> fetchJobData(JobParameter jobParameter) {
		MessageTaskQueryDto query = new MessageTaskQueryDto();
		query.setCreateTimeBefore(DateUtil.getBeforeTime(timeOutMinute));
		query.setMessageStatus(MqSendStatusEnum.WAIT_SEND.sendStatus());
		query.setFetchNum(jobParameter.getFetchNum());
		query.setShardingItem(jobParameter.getShardingItem());
		query.setShardingTotalCount(jobParameter.getShardingTotalCount());
		query.setTaskStatus(JobTaskStatusEnum.TASK_CREATE.status());
		query.setProducerGroup(PID_CLOUD_IMC);
		return tpcMqMessageService.queryWaitingConfirmMessageKeyList(query);
	}

	/**
	 * Process job data.
	 *
	 * @param messageKeyList the message key list
	 */
	@Override
	protected void processJobData(List<String> messageKeyList) {
		if (messageKeyList == null) {
			return;
		}
		List<String> resendMessageList = imcRpcService.queryWaitingConfirmMessageKeyList(messageKeyList);
		if (resendMessageList == null) {
			resendMessageList = Lists.newArrayList();
		}
		messageKeyList.removeAll(resendMessageList);
		tpcMqMessageService.handleWaitingConfirmMessage(messageKeyList, resendMessageList);
	}
}
