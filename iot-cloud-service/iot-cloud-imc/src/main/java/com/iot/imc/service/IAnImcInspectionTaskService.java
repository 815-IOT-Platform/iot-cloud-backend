package com.iot.imc.service;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.core.service.IService;
import com.iot.imc.domain.AnImcInspectionTask;
import com.iot.imc.dto.*;
import com.iot.system.dto.FileUploadDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 巡检任务表Service接口
 * 
 * @author ananops
 * @date 2020-05-22
 */
public interface IAnImcInspectionTaskService extends IService<AnImcInspectionTask>
{
    /**
     * 查询巡检任务表
     * 
     * @param id 巡检任务表ID
     * @return 巡检任务表
     */
    public ImcInspectionTaskDto selectAnImcInspectionTaskById(Long id);

    /**
     * 查询巡检任务表列表
     * 
     * @param anImcInspectionTask 巡检任务表
     * @return 巡检任务表集合
     */
    public List<AnImcInspectionTask> selectAnImcInspectionTaskList(AnImcInspectionTask anImcInspectionTask);

    /**
     * 新增巡检任务表
     * 
     * @param  imcAddInspectionTaskDto 巡检任务表
     * @return 结果
     */
    public ImcAddInspectionTaskDto insertAnImcInspectionTask(ImcAddInspectionTaskDto imcAddInspectionTaskDto, LoginAuthDto user);

    /**
     * 修改巡检任务表
     * 
     * @param anImcInspectionTask 巡检任务表
     * @return 结果
     */
    public int updateAnImcInspectionTask(AnImcInspectionTask anImcInspectionTask);

    /**
     * 批量删除巡检任务表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnImcInspectionTaskByIds(String ids);

    /**
     * 删除巡检任务表信息
     * 
     * @param id 巡检任务表ID
     * @return 结果
     */
    public int deleteAnImcInspectionTaskById(Long id);

    /**
     * 更改巡检任务状态
     * @param imcTaskChangeStatusDto
     * @param user
     * @return
     */
    public ImcTaskChangeStatusDto modifyTaskStatus(ImcTaskChangeStatusDto imcTaskChangeStatusDto,LoginAuthDto user);

    /**
     * 判断巡检任务是否完成
     * @param taskId
     * @return
     */
    public boolean isTaskFinish(Long taskId);

    /**
     * 根据项目id查询对应的巡检任务列表
     * @param taskQueryDto
     * @return
     */
    public PageInfo getTaskByProjectId(TaskQueryDto taskQueryDto);

    /**
     *根据用户id获取对应的巡检任务（可分页）
     * @param taskQueryDto
     * @return
     */
    public PageInfo getTaskByUserId(TaskQueryDto taskQueryDto);

    /**
     * 根据服务商id查询对应的全部巡检任务
     * @param taskQueryDto
     * @return
     */
    public List<AnImcInspectionTask> getTaskByFacilitatorId(TaskQueryDto taskQueryDto);

    /**
     * 根据甲方负责人id查询全部的巡检任务
     * @param taskQueryDto
     * @return
     */
    public List<AnImcInspectionTask> getTaskByPrincipalId(TaskQueryDto taskQueryDto);

    /**
     * 根据用户id和角色获取全部的巡检任务数目
     * @param taskQueryDto
     * @return
     */
    public int getImcTaskNumberByUserIdAndRole(TaskQueryDto taskQueryDto);

    /**
     * 查询全部未分配工程师的巡检任务
     * @param taskQueryDto
     * @return
     */
    public PageInfo getAllUnDistributedTask(TaskQueryDto taskQueryDto);

    /**
     * 获取全部当前服务商已完成的巡检任务
     * @param taskQueryDto
     * @return
     */
    public PageInfo getAllFinishedTaskByFacilitatorId(TaskQueryDto taskQueryDto);

    /**
     * 获取任务对应的全部操作日志
     * @param taskId
     * @return
     */
    public List<TaskLogDto> getTaskLogs(Long taskId);

    /**
     * 生成任务报表
     * @param taskId
     * @param user
     * @return
     */
    public FileUploadDto generateImcTaskPdf (Long taskId, LoginAuthDto user);

    /**
     * 获取任务报表
     * @param taskId
     * @param user
     * @return
     */
    public String getTaskReport (Long taskId, LoginAuthDto user);
}
