package com.iot.imc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 提交巡检过程中的相关结果
 *
 * @author Bingyue Duan
 *
 * @version 1.0
 *
 * @date 2020/4/22 下午6:21
 */
@Data
@ApiModel
public class ItemResultDto implements Serializable {

    private static final long serialVersionUID = -3544078773416615277L;

    /**
     * 巡检任务子项的ID
     */
    @ApiModelProperty(value = "巡检任务子项ID")
    private Long itemId;

    /**
     * 巡检任务子项修改后的状态
     */
    @ApiModelProperty(value = "巡检任务子项修改后的状态")
    private Integer status;

    /**
     * 附件id
     */
    @ApiModelProperty(value = "巡检任务子项对应的附件id")
    private List<Long> attachmentIds;

    /**
     * 实际开始时间
     */
    @ApiModelProperty(value = "实际开始时间",example = "2019-8-24 11:11:11")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date actualStartTime;

    /**
     * 实际完成时间
     */
    @ApiModelProperty(value = "实际完成时间",example = "2019-8-24 11:11:11")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date actualFinishTime;
}
