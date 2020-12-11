package com.iot.system.service;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.system.domain.SysCompany;
import com.iot.system.domain.SysDept;
import com.iot.system.dto.CompanyRegisterDto;

import java.util.List;

/**
 * 企业管理 Service接口
 *
 * @author ananops
 * @date 2020-05-25
 */
public interface ISysCompanyService {
    /**
     * 查询企业
     *
     * @param id 企业ID
     * @return 企业
     */
    SysCompany selectSysCompanyById(Long id);

    /**
     * 查询企业列表
     *
     * @param sysCompany 企业
     * @return 企业集合
     */
    List<SysCompany> selectSysCompanyList(SysCompany sysCompany);

    /**
     * 新增企业
     *
     * @param sysCompany 企业
     * @return 结果
     */
    int insertSysCompany(SysCompany sysCompany, LoginAuthDto user);

    /**
     * 修改企业
     *
     * @param sysCompany 企业
     * @return 结果
     */
    int updateSysCompany(SysCompany sysCompany, LoginAuthDto user);

    /**
     * 批量删除企业
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSysCompanyByIds(String ids);

    /**
     * 删除企业信息
     *
     * @param id 企业ID
     * @return 结果
     */
    int deleteSysCompanyById(Long id);

    /**
     * 根据部门获取公司
     * @param sysDept
     * @return
     */
    SysCompany selectSysCompanyByDeptId(SysDept sysDept);

    void register(CompanyRegisterDto company);
}
