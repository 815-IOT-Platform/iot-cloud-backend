package com.iot.imc.service.impl;

import java.util.List;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.exception.BusinessException;
import com.iot.common.utils.DateUtils;
import com.iot.imc.manager.TaskManager;
import com.iot.imc.mq.producer.TaskMsgProducer;
import com.iot.sdk.domain.MqMessageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iot.imc.mapper.IotImcInspectionReviewMapper;
import com.iot.imc.domain.AnImcInspectionReview;
import com.iot.imc.service.IAnImcInspectionReviewService;
import com.iot.common.core.text.Convert;

/**
 * 巡检任务评论Service业务层处理
 * 
 * @author ananops
 * @date 2020-05-22
 */
@Service
public class AnImcInspectionReviewServiceImpl implements IAnImcInspectionReviewService 
{
    @Autowired
    private IotImcInspectionReviewMapper iotImcInspectionReviewMapper;

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private TaskMsgProducer taskMsgProducer;

    /**
     * 查询巡检任务评论
     * 
     * @param id 巡检任务评论ID
     * @return 巡检任务评论
     */
    @Override
    public AnImcInspectionReview selectAnImcInspectionReviewById(Long id)
    {
        return iotImcInspectionReviewMapper.selectAnImcInspectionReviewById(id);
    }

    /**
     * 查询巡检任务评论列表
     * 
     * @param anImcInspectionReview 巡检任务评论
     * @return 巡检任务评论
     */
    @Override
    public List<AnImcInspectionReview> selectAnImcInspectionReviewList(AnImcInspectionReview anImcInspectionReview)
    {
        return iotImcInspectionReviewMapper.selectAnImcInspectionReviewList(anImcInspectionReview);
    }

    /**
     * 新增巡检任务评论
     * 
     * @param anImcInspectionReview 巡检任务评论
     * @return 结果
     */
    @Override
    public int insertAnImcInspectionReview(AnImcInspectionReview anImcInspectionReview, LoginAuthDto user)
    {
        MqMessageData mqMessageData = taskMsgProducer.createImcReview(anImcInspectionReview,user);
        try {
            taskManager.sendReviewMqTestMsg(mqMessageData,anImcInspectionReview);
            return 1;
        } catch (Exception e) {
            throw new BusinessException("巡检任务评论mq消息发送失败");
        }
    }

    /**
     * 修改巡检任务评论
     * 
     * @param anImcInspectionReview 巡检任务评论
     * @return 结果
     */
    @Override
    public int updateAnImcInspectionReview(AnImcInspectionReview anImcInspectionReview)
    {
        anImcInspectionReview.setUpdateTime(DateUtils.getNowDate());
        return iotImcInspectionReviewMapper.updateAnImcInspectionReview(anImcInspectionReview);
    }

    /**
     * 删除巡检任务评论对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAnImcInspectionReviewByIds(String ids)
    {
        return iotImcInspectionReviewMapper.deleteAnImcInspectionReviewByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除巡检任务评论信息
     * 
     * @param id 巡检任务评论ID
     * @return 结果
     */
    public int deleteAnImcInspectionReviewById(Long id)
    {
        return iotImcInspectionReviewMapper.deleteAnImcInspectionReviewById(id);
    }
}
