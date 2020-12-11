package com.iot.imc.service;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.imc.domain.AnImcInspectionReview;
import java.util.List;

/**
 * 巡检任务评论Service接口
 * 
 * @author ananops
 * @date 2020-05-22
 */
public interface IAnImcInspectionReviewService 
{
    /**
     * 查询巡检任务评论
     * 
     * @param id 巡检任务评论ID
     * @return 巡检任务评论
     */
    public AnImcInspectionReview selectAnImcInspectionReviewById(Long id);

    /**
     * 查询巡检任务评论列表
     * 
     * @param anImcInspectionReview 巡检任务评论
     * @return 巡检任务评论集合
     */
    public List<AnImcInspectionReview> selectAnImcInspectionReviewList(AnImcInspectionReview anImcInspectionReview);

    /**
     * 新增巡检任务评论
     * 
     * @param anImcInspectionReview 巡检任务评论
     * @return 结果
     */
    public int insertAnImcInspectionReview(AnImcInspectionReview anImcInspectionReview, LoginAuthDto user);

    /**
     * 修改巡检任务评论
     * 
     * @param anImcInspectionReview 巡检任务评论
     * @return 结果
     */
    public int updateAnImcInspectionReview(AnImcInspectionReview anImcInspectionReview);

    /**
     * 批量删除巡检任务评论
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnImcInspectionReviewByIds(String ids);

    /**
     * 删除巡检任务评论信息
     * 
     * @param id 巡检任务评论ID
     * @return 结果
     */
    public int deleteAnImcInspectionReviewById(Long id);
}
