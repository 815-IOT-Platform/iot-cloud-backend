package com.iot.imc.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rongshuai on 2020/4/4 16:56
 */
@Data
@ApiModel
public class ImcTaskReportDto implements Serializable {
    private static final long serialVersionUID = 2675550183412596596L;

    /**
     * 巡检任务的报告信息
     */
    private ImcInspectionTaskDto imcInspectionTaskDto;

    /**
     * 巡检任务对应的全部子项的报告信息
     */
    private List<ImcInspectionItemDto> imcInspectionItemDtos;
}
