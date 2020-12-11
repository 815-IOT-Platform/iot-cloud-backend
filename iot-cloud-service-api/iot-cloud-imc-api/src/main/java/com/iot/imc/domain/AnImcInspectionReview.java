package com.iot.imc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iot.common.annotation.Excel;
import com.iot.common.core.domain.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 巡检任务评论对象 an_imc_inspection_review
 * 
 * @author ananops
 * @date 2020-05-22
 */
public class AnImcInspectionReview extends BaseEntity
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
    private Long inspectionTaskId;

    /** 项目负责人ID */
    @Excel(name = "项目负责人ID")
    private Long principalId;

    /** 服务评级 */
    @Excel(name = "服务评级")
    private Integer score;

    /** 服务评论 */
    @Excel(name = "服务评论")
    private String contents;

    /** 验收内容 */
    @Excel(name = "验收内容")
    private String checkContens;

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
    public void setPrincipalId(Long principalId) 
    {
        this.principalId = principalId;
    }

    public Long getPrincipalId() 
    {
        return principalId;
    }
    public void setScore(Integer score) 
    {
        this.score = score;
    }

    public Integer getScore() 
    {
        return score;
    }
    public void setContents(String contents) 
    {
        this.contents = contents;
    }

    public String getContents() 
    {
        return contents;
    }
    public void setCheckContens(String checkContens) 
    {
        this.checkContens = checkContens;
    }

    public String getCheckContens() 
    {
        return checkContens;
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
            .append("principalId", getPrincipalId())
            .append("score", getScore())
            .append("contents", getContents())
            .append("remark", getRemark())
            .append("checkContens", getCheckContens())
            .toString();
    }
}
