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
 * 巡检任务子项日志对象 an_imc_inspection_item_log
 * 
 * @author ananops
 * @date 2020-05-22
 */
public class AnImcInspectionItemLog extends BaseEntity
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

    /** 对应的巡检任务ID */
    @Excel(name = "对应的巡检任务ID")
    private Long taskId;

    /** 对应的状态类型 */
    @Excel(name = "对应的状态类型")
    private Integer status;

    /** 对应的时间戳 */
    @Excel(name = "对应的时间戳", width = 30, dateFormat = "yyyy-MM-dd")
    private Date statusTimestamp;

    /** 当前操作的描述（有可能不存在） */
    @Excel(name = "当前操作的描述", readConverterExp = "有=可能不存在")
    private String movement;

    /** 对应的巡检任务子项ID */
    @Excel(name = "对应的巡检任务子项ID")
    private Long itemId;

    /** $column.columnComment */
    @Excel(name = "对应的巡检任务子项ID")
    private String os;

    /** $column.columnComment */
    @Excel(name = "对应的巡检任务子项ID")
    private String browser;

    /** $column.columnComment */
    @Excel(name = "对应的巡检任务子项ID")
    private String ipAddress;

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
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setStatusTimestamp(Date statusTimestamp) 
    {
        this.statusTimestamp = statusTimestamp;
    }

    public Date getStatusTimestamp() 
    {
        return statusTimestamp;
    }
    public void setMovement(String movement) 
    {
        this.movement = movement;
    }

    public String getMovement() 
    {
        return movement;
    }
    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }
    public void setOs(String os) 
    {
        this.os = os;
    }

    public String getOs() 
    {
        return os;
    }
    public void setBrowser(String browser) 
    {
        this.browser = browser;
    }

    public String getBrowser() 
    {
        return browser;
    }
    public void setIpAddress(String ipAddress) 
    {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() 
    {
        return ipAddress;
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
            .append("taskId", getTaskId())
            .append("status", getStatus())
            .append("statusTimestamp", getStatusTimestamp())
            .append("movement", getMovement())
            .append("itemId", getItemId())
            .append("os", getOs())
            .append("browser", getBrowser())
            .append("ipAddress", getIpAddress())
            .append("remark", getRemark())
            .toString();
    }
}
