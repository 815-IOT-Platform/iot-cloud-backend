package com.iot.imc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iot.common.annotation.Excel;
import com.iot.common.core.domain.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 巡检任务子项对象 an_imc_inspection_item
 * 
 * @author ananops
 * @date 2020-05-22
 */
public class AnImcInspectionItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long creatorId;

    /** 最后操作人ID */
    @Excel(name = "最后操作人ID")
    private Long lastOperatorId;

    /** 从属的巡检任务的ID */
    @Excel(name = "从属的巡检任务的ID")
    private Long inspectionTaskId;

    /** 计划开始时间 */
    @Excel(name = "计划开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scheduledStartTime;

    /** 实际开始时间 */
    @Excel(name = "实际开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualStartTime;

    /** 实际完成时间 */
    @Excel(name = "实际完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualFinishTime;

    /** 计划完成天数 */
    @Excel(name = "计划完成天数")
    private Long days;

    /** 巡检周期（月） */
    @Excel(name = "巡检周期", readConverterExp = "月=")
    private Long frequency;

    /** 巡检子项内容描述 */
    @Excel(name = "巡检子项内容描述")
    private String description;

    /** 巡检子项的巡检状态 */
    @Excel(name = "巡检子项的巡检状态")
    private Integer status;

    /** 巡检子项的位置，纬度 */
    @Excel(name = "巡检子项的位置，纬度")
    private Double itemLatitude;

    /** 巡检子项的位置，经度 */
    @Excel(name = "巡检子项的位置，经度")
    private Double itemLongitude;

    /** 巡检子项结果描述 */
    @Excel(name = "巡检子项结果描述")
    private String result;

    /** 巡检子项的名称 */
    @Excel(name = "巡检子项的名称")
    private String itemName;

    /** 巡检子项对应的维修工 */
    @Excel(name = "巡检子项对应的维修工")
    private Long maintainerId;

    /** 巡检任务子项已经执行的次数 */
    @Excel(name = "巡检任务子项已经执行的次数")
    private Integer count;

    /** 巡检任务子项对应的网点 */
    @Excel(name = "巡检任务子项对应的网点")
    private String location;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }
    public void setCreatorId(Long creatorId) 
    {
        this.creatorId = creatorId;
    }

    public Long getCreatorId() 
    {
        return creatorId;
    }
    public void setLastOperatorId(Long lastOperatorId) 
    {
        this.lastOperatorId = lastOperatorId;
    }

    public Long getLastOperatorId() 
    {
        return lastOperatorId;
    }
    public void setInspectionTaskId(Long inspectionTaskId) 
    {
        this.inspectionTaskId = inspectionTaskId;
    }

    public Long getInspectionTaskId() 
    {
        return inspectionTaskId;
    }
    public void setScheduledStartTime(Date scheduledStartTime) 
    {
        this.scheduledStartTime = scheduledStartTime;
    }

    public Date getScheduledStartTime() 
    {
        return scheduledStartTime;
    }
    public void setActualStartTime(Date actualStartTime) 
    {
        this.actualStartTime = actualStartTime;
    }

    public Date getActualStartTime() 
    {
        return actualStartTime;
    }
    public void setActualFinishTime(Date actualFinishTime) 
    {
        this.actualFinishTime = actualFinishTime;
    }

    public Date getActualFinishTime() 
    {
        return actualFinishTime;
    }
    public void setDays(Long days) 
    {
        this.days = days;
    }

    public Long getDays() 
    {
        return days;
    }
    public void setFrequency(Long frequency) 
    {
        this.frequency = frequency;
    }

    public Long getFrequency() 
    {
        return frequency;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setItemLatitude(Double itemLatitude) 
    {
        this.itemLatitude = itemLatitude;
    }

    public Double getItemLatitude() 
    {
        return itemLatitude;
    }
    public void setItemLongitude(Double itemLongitude) 
    {
        this.itemLongitude = itemLongitude;
    }

    public Double getItemLongitude() 
    {
        return itemLongitude;
    }
    public void setResult(String result) 
    {
        this.result = result;
    }

    public String getResult() 
    {
        return result;
    }
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }
    public void setMaintainerId(Long maintainerId) 
    {
        this.maintainerId = maintainerId;
    }

    public Long getMaintainerId() 
    {
        return maintainerId;
    }
    public void setCount(Integer count) 
    {
        this.count = count;
    }

    public Integer getCount() 
    {
        return count;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("version", getVersion())
            .append("createBy", getCreateBy())
            .append("creatorId", getCreatorId())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("lastOperatorId", getLastOperatorId())
            .append("updateTime", getUpdateTime())
            .append("inspectionTaskId", getInspectionTaskId())
            .append("scheduledStartTime", getScheduledStartTime())
            .append("actualStartTime", getActualStartTime())
            .append("actualFinishTime", getActualFinishTime())
            .append("days", getDays())
            .append("frequency", getFrequency())
            .append("description", getDescription())
            .append("status", getStatus())
            .append("itemLatitude", getItemLatitude())
            .append("itemLongitude", getItemLongitude())
            .append("result", getResult())
            .append("itemName", getItemName())
            .append("maintainerId", getMaintainerId())
            .append("count", getCount())
            .append("location", getLocation())
            .append("remark", getRemark())
            .toString();
    }
}
