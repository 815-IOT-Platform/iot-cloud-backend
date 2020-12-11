package com.iot.system.service.impl;

import java.util.List;

import com.iot.common.constant.UserConstants;
import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.exception.BusinessException;
import com.iot.common.utils.bean.UpdateInfoUtil;
import com.iot.common.zk.generator.UniqueIdGenerator;
import com.iot.system.domain.SysDept;
import com.iot.system.domain.SysUser;
import com.iot.system.dto.CompanyRegisterDto;
import com.iot.system.dto.UserRegisterDto;
import com.iot.system.service.ISysUserService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.iot.system.mapper.SysCompanyMapper;
import com.iot.system.domain.SysCompany;
import com.iot.system.service.ISysCompanyService;
import com.iot.common.core.text.Convert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 企业管理 Service业务层处理
 *
 * @author ananops
 * @date 2020-05-25
 */
@Service
@Slf4j
public class SysCompanyServiceImpl implements ISysCompanyService {
    @Resource
    private SysCompanyMapper sysCompanyMapper;

    @Resource
    private ISysUserService sysUserService;

    /**
     * 查询企业
     *
     * @param id 企业ID
     * @return 企业
     */
    @Override
    public SysCompany selectSysCompanyById(Long id) {
        return sysCompanyMapper.selectSysCompanyById(id);
    }

    /**
     * 查询企业列表
     *
     * @param sysCompany 企业
     * @return 企业
     */
    @Override
    public List<SysCompany> selectSysCompanyList(SysCompany sysCompany) {
        return sysCompanyMapper.selectSysCompanyList(sysCompany);
    }

    /**
     * 新增企业
     *
     * @param sysCompany 企业
     * @return 结果
     */
    @Override
    public int insertSysCompany(SysCompany sysCompany, LoginAuthDto user) {
        UpdateInfoUtil.setInsertInfo(sysCompany, user);
        sysCompany.setDeptId(user.getDeptId());
        return sysCompanyMapper.insertSysCompany(sysCompany);
    }

    /**
     * 修改企业
     *
     * @param sysCompany 企业
     * @return 结果
     */
    @Override
    public int updateSysCompany(SysCompany sysCompany, LoginAuthDto user) {
        UpdateInfoUtil.setModifyInfo(sysCompany, user);
        return sysCompanyMapper.updateSysCompany(sysCompany);
    }

    /**
     * 删除企业对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysCompanyByIds(String ids) {
        return sysCompanyMapper.deleteSysCompanyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除企业信息
     *
     * @param id 企业ID
     * @return 结果
     */
    public int deleteSysCompanyById(Long id) {
        return sysCompanyMapper.deleteSysCompanyById(id);
    }

    @Override
    public SysCompany selectSysCompanyByDeptId(SysDept sysDept) {
        String ancestors = sysDept.getAncestors();
        Long deptId = sysDept.getDeptId();
        if (ancestors != null && !ancestors.equals("0")) {
            String[] ancestor = ancestors.split(",");
            deptId = Long.valueOf(ancestor[1]);
        }
        log.info("deptId: " + String.valueOf(deptId));
        return sysCompanyMapper.selectSysCompanyByDeptId(deptId);
    }

    @Override
    public void register(CompanyRegisterDto company) {
        // 校验注册信息
        validateRegisterInfo(company);

        // 构建UAC User注册Dto
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        SysCompany sysCompany = new SysCompany();
        Long companyId = UniqueIdGenerator.generateId();
        try {
            // 绑定用户注册信息
            BeanUtils.copyProperties(company,userRegisterDto);
            userRegisterDto.setLoginName(company.getCompanyName());
            userRegisterDto.setPhonenumber(company.getPhonenumber());
            userRegisterDto.setCompanyName(company.getCompanyName());
            userRegisterDto.setCompanyId(companyId);

            // 绑定公司注册信息
            BeanUtils.copyProperties(company,sysCompany);
            sysCompany.setId(companyId);
        } catch (Exception e) {
            log.error("服务商Dto与用户Dto属性拷贝异常");
            e.printStackTrace();
        }
        log.info("注册用户. userRegisterDto={}", userRegisterDto);
        sysUserService.register(userRegisterDto);
        SysUser userByEmail = sysUserService.selectUserByEmail(userRegisterDto.getEmail());
        log.info("注册用户成功userId:{}",userByEmail.getUserId());

        // 构造创建对象信息
        LoginAuthDto loginAuthDto = new LoginAuthDto();
        loginAuthDto.setUserId(userByEmail.getUserId());
        loginAuthDto.setUserName(userByEmail.getUserName());
        loginAuthDto.setUserName(company.getCompanyName());
        loginAuthDto.setLoginName(company.getCompanyName());

        sysCompany.setUserId(userByEmail.getUserId());
        log.info("注册组织. sysCompany={}", sysCompany);
        this.insertSysCompany(sysCompany,loginAuthDto);
    }

    /**
     * 校验注册信息
     *
     * @param companyRegisterDto 注册的对象
     */
    private void validateRegisterInfo(CompanyRegisterDto companyRegisterDto) {
        String mobileNo = companyRegisterDto.getPhonenumber();

        Preconditions.checkArgument(!StringUtils.isEmpty(companyRegisterDto.getCompanyName()), "登录名不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(companyRegisterDto.getEmail()), "邮箱不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(companyRegisterDto.getCompanyCode()), "社会编码不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(mobileNo), "手机号不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(companyRegisterDto.getPassword()), "密码不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(companyRegisterDto.getConfirmPwd()), "确认密码不能为空");
        //TODO 短信验证码校验暂时不启用
        //Preconditions.checkArgument(!StringUtils.isEmpty(companyRegisterDto.getPhoneSmsCode()), "短信验证码不能为空");
        Preconditions.checkArgument(companyRegisterDto.getPassword().equals(companyRegisterDto.getConfirmPwd()), "两次密码不一致");

        SysUser sysUser = new SysUser();
        sysUser.setLoginName(companyRegisterDto.getCompanyName());
        sysUser.setPhonenumber(companyRegisterDto.getPhonenumber());
        sysUser.setEmail(companyRegisterDto.getEmail());


        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(sysUserService.checkLoginNameUnique(sysUser.getLoginName()))) {
            throw new BusinessException("新增用户'" + sysUser.getLoginName() + "'失败，登录账号已存在");
        } else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(sysUser))) {
            throw new BusinessException("新增用户'" + sysUser.getLoginName() + "'失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(sysUserService.checkEmailUnique(sysUser))) {
            throw new BusinessException("新增用户'" + sysUser.getLoginName() + "'失败，邮箱账号已存在");
        }
    }

}
