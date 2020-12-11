package com.iot.system.mapper;

import com.iot.common.core.dao.BaseMapper;
import com.iot.system.domain.SysCompany;

import java.util.List;

/**
 * 公司Mapper接口
 *
 * @author ananops
 * @date 2020-05-25
 */
public interface SysCompanyMapper extends BaseMapper<SysCompany> {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public SysCompany selectSysCompanyById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param sysCompany 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysCompany> selectSysCompanyList(SysCompany sysCompany);

    /**
     * 新增【请填写功能名称】
     *
     * @param sysCompany 【请填写功能名称】
     * @return 结果
     */
    public int insertSysCompany(SysCompany sysCompany);

    /**
     * 修改【请填写功能名称】
     *
     * @param sysCompany 【请填写功能名称】
     * @return 结果
     */
    public int updateSysCompany(SysCompany sysCompany);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSysCompanyById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCompanyByIds(String[] ids);

    SysCompany selectSysCompanyByDeptId(Long deptId);
}
