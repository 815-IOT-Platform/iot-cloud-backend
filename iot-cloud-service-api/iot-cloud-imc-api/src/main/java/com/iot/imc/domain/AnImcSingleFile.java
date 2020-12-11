package com.iot.imc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iot.common.annotation.Excel;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 单文件关系表对象 an_imc_single_file
 * 
 * @author ananops
 * @date 2020-06-10
 */
public class AnImcSingleFile implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 唯一ID（任务、子项均可） */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueId;

    /** 附件地址 */
    @Excel(name = "附件地址")
    private String url;

    public void setUniqueId(Long uniqueId) 
    {
        this.uniqueId = uniqueId;
    }

    public Long getUniqueId() 
    {
        return uniqueId;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uniqueId", getUniqueId())
            .append("url", getUrl())
            .toString();
    }
}
