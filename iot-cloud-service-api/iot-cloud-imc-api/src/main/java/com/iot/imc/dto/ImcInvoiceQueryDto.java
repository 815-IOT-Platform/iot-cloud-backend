package com.iot.imc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Bingyue Duan
 * @version 1.0
 * @date 2020/4/21 下午8:25
 */
@Data
@ApiModel
public class ImcInvoiceQueryDto implements Serializable {

    private static final long serialVersionUID = -5054903520448589311L;

    /**
     * 巡检子项Id
     */
    @ApiModelProperty(value = "巡检任务子项ID")
    private Long itemId;

    /**
     * 巡检单状态
     */
    @ApiModelProperty(value = "巡检单状态")
    private String status;
}
