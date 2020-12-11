package com.iot.system.domain;

import lombok.Data;
import com.iot.common.annotation.Excel;
import com.iot.common.core.domain.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 公司对象 sys_company
 *
 * @author ananops
 * @date 2020-05-25
 */
@Data
public class SysCompany extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 公司id，主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 部门ID
     */
    @Excel(name = "部门ID")
    private Long deptId;

    /**
     * 公司编码
     */
    @Excel(name = "公司编码")
    private String companyCode;

    /**
     * 公司名称
     */
    @Excel(name = "公司名称")
    private String companyName;

    /**
     * 公司类型
     */
    @Excel(name = "公司类型")
    private String type;

    /**
     * 法人姓名
     */
    @Excel(name = "法人姓名")
    private String legalPersonName;

    /**
     * 法人联系方式
     */
    @Excel(name = "法人联系方式")
    private String legalPersonPhone;

    /**
     * 法人身份证号
     */
    @Excel(name = "法人身份证号")
    private String legalPersonNumber;

    /**
     * 主体行业
     */
    @Excel(name = "主体行业")
    private String mainWork;

    /**
     * 国家地区
     */
    @Excel(name = "国家地区")
    private String country;

    /**
     * 邮政编码
     */
    @Excel(name = "邮政编码")
    private String zipCode;

    /**
     * 基本户账户名称
     */
    @Excel(name = "基本户账户名称")
    private String accountName;

    /**
     * 基本账户账号
     */
    @Excel(name = "基本账户账号")
    private String accountNumber;

    /**
     * 营业执照类别
     */
    @Excel(name = "营业执照类别")
    private String licenseType;

    /**
     * 注册资本
     */
    @Excel(name = "注册资本")
    private String registeredCaptial;

    /**
     * 有效期
     */
    @Excel(name = "有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expirationDate;

    /**
     * 经营范围
     */
    @Excel(name = "经营范围")
    private String businessScope;

    /**
     * 供应产品类别
     */
    @Excel(name = "供应产品类别")
    private String productCategory;

    /**
     * 法人身份证照片url
     */
    @Excel(name = "法人身份证照片url")
    private String legalPersonidPhoto;

    /**
     * 营业执照照片url
     */
    @Excel(name = "营业执照照片url")
    private String businessLicensePhoto;

    /**
     * 账户开户许可证照片url
     */
    @Excel(name = "账户开户许可证照片url")
    private String accountOpeningLicense;

    /**
     * 组织地址
     */
    @Excel(name = "组织地址")
    private String compayAddress;

    /**
     * 省名称
     */
    @Excel(name = "省名称")
    private String provinceName;

    /**
     * 省编码
     */
    @Excel(name = "省编码")
    private Long provinceId;

    /**
     * 城市名称
     */
    @Excel(name = "城市名称")
    private String cityName;

    /**
     * 城市编码
     */
    @Excel(name = "城市编码")
    private Long cityId;

    /**
     * 区名称
     */
    @Excel(name = "区名称")
    private String areaName;

    /**
     * 区编码
     */
    @Excel(name = "区编码")
    private Long areaId;

    /**
     * 街道名称
     */
    @Excel(name = "街道名称")
    private String streetName;

    /**
     * 街道编码
     */
    @Excel(name = "街道编码")
    private Long streetId;

    /**
     * 详细地址
     */
    @Excel(name = "详细地址")
    private String detailAddress;

    /**
     * 公司状态（0正常 1停用）
     */
    @Excel(name = "公司状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 版本号
     */
    @Excel(name = "版本号")
    private Long version;

    /**
     * 创建人ID
     */
    @Excel(name = "创建人ID")
    private Long creatorId;

    /**
     * 最后操作人ID
     */
    @Excel(name = "最后操作人ID")
    private Long lastOperatorId;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;


}
