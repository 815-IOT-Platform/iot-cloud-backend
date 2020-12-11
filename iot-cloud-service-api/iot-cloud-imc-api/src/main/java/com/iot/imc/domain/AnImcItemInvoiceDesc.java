package com.iot.imc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iot.common.annotation.Excel;
import com.iot.common.core.domain.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 巡检记录对应的设备对象 an_imc_item_invoice_desc
 * 
 * @author ananops
 * @date 2020-05-22
 */
public class AnImcItemInvoiceDesc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表单模板ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 单据ID */
    @Excel(name = "单据ID")
    private Long invoiceId;

    /** 巡检内容 */
    @Excel(name = "巡检内容")
    private String itemContent;

    /** 本次巡检情况 */
    @Excel(name = "本次巡检情况")
    private String itemState;

    /** 处理结果 */
    @Excel(name = "处理结果")
    private String itemResult;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

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
    public void setInvoiceId(Long invoiceId) 
    {
        this.invoiceId = invoiceId;
    }

    public Long getInvoiceId() 
    {
        return invoiceId;
    }
    public void setItemContent(String itemContent) 
    {
        this.itemContent = itemContent;
    }

    public String getItemContent() 
    {
        return itemContent;
    }
    public void setItemState(String itemState) 
    {
        this.itemState = itemState;
    }

    public String getItemState() 
    {
        return itemState;
    }
    public void setItemResult(String itemResult) 
    {
        this.itemResult = itemResult;
    }

    public String getItemResult() 
    {
        return itemResult;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
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
            .append("invoiceId", getInvoiceId())
            .append("itemContent", getItemContent())
            .append("itemState", getItemState())
            .append("itemResult", getItemResult())
            .append("sort", getSort())
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
