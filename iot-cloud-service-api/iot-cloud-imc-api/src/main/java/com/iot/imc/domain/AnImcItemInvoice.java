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
 * 巡检记录对象 an_imc_item_invoice
 * 
 * @author ananops
 * @date 2020-05-22
 */
public class AnImcItemInvoice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表单模板ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 模板ID */
    @Excel(name = "模板ID")
    private Long templateId;

    /** 巡检子项ID */
    @Excel(name = "巡检子项ID")
    private Long inspcItemId;

    /** 点位名称 */
    @Excel(name = "点位名称")
    private String pointName;

    /** 点位地址 */
    @Excel(name = "点位地址")
    private String pointAddress;

    /** 巡检单位ID */
    @Excel(name = "巡检单位ID")
    private Long inspcCompanyId;

    /** 巡检单位名称 */
    @Excel(name = "巡检单位名称")
    private String inspcCompany;

    /** 巡检结论 */
    @Excel(name = "巡检结论")
    private String inspcResult;

    /** 巡检日期 */
    @Excel(name = "巡检日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspcDate;

    /** 用户确认 */
    @Excel(name = "用户确认")
    private String userConfirm;

    /** 巡检工程师ID */
    @Excel(name = "巡检工程师ID")
    private Long engineerId;

    /** 巡检工程师名称 */
    @Excel(name = "巡检工程师名称")
    private String engineer;

    /** 单据状态Y(已完成)/N（未完成） */
    @Excel(name = "单据状态Y(已完成)/N", readConverterExp = "未=完成")
    private String status;

    /** 逻辑删除 */
    @Excel(name = "逻辑删除")
    private String dr;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long creatorId;

    /** 最后操作人ID */
    @Excel(name = "最后操作人ID")
    private Long lastOperatorId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }
    public void setInspcItemId(Long inspcItemId) 
    {
        this.inspcItemId = inspcItemId;
    }

    public Long getInspcItemId() 
    {
        return inspcItemId;
    }
    public void setPointName(String pointName) 
    {
        this.pointName = pointName;
    }

    public String getPointName() 
    {
        return pointName;
    }
    public void setPointAddress(String pointAddress) 
    {
        this.pointAddress = pointAddress;
    }

    public String getPointAddress() 
    {
        return pointAddress;
    }
    public void setInspcCompanyId(Long inspcCompanyId) 
    {
        this.inspcCompanyId = inspcCompanyId;
    }

    public Long getInspcCompanyId() 
    {
        return inspcCompanyId;
    }
    public void setInspcCompany(String inspcCompany) 
    {
        this.inspcCompany = inspcCompany;
    }

    public String getInspcCompany() 
    {
        return inspcCompany;
    }
    public void setInspcResult(String inspcResult) 
    {
        this.inspcResult = inspcResult;
    }

    public String getInspcResult() 
    {
        return inspcResult;
    }
    public void setInspcDate(Date inspcDate) 
    {
        this.inspcDate = inspcDate;
    }

    public Date getInspcDate() 
    {
        return inspcDate;
    }
    public void setUserConfirm(String userConfirm) 
    {
        this.userConfirm = userConfirm;
    }

    public String getUserConfirm() 
    {
        return userConfirm;
    }
    public void setEngineerId(Long engineerId) 
    {
        this.engineerId = engineerId;
    }

    public Long getEngineerId() 
    {
        return engineerId;
    }
    public void setEngineer(String engineer) 
    {
        this.engineer = engineer;
    }

    public String getEngineer() 
    {
        return engineer;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDr(String dr) 
    {
        this.dr = dr;
    }

    public String getDr() 
    {
        return dr;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("templateId", getTemplateId())
            .append("inspcItemId", getInspcItemId())
            .append("pointName", getPointName())
            .append("pointAddress", getPointAddress())
            .append("inspcCompanyId", getInspcCompanyId())
            .append("inspcCompany", getInspcCompany())
            .append("inspcResult", getInspcResult())
            .append("inspcDate", getInspcDate())
            .append("userConfirm", getUserConfirm())
            .append("engineerId", getEngineerId())
            .append("engineer", getEngineer())
            .append("status", getStatus())
            .append("dr", getDr())
            .append("version", getVersion())
            .append("createBy", getCreateBy())
            .append("creatorId", getCreatorId())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("lastOperatorId", getLastOperatorId())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
