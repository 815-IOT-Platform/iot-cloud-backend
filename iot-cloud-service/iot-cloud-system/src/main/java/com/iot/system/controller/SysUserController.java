package com.iot.system.controller;

import java.util.Set;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.system.service.ISysCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iot.common.annotation.LoginUser;
import com.iot.common.auth.annotation.HasPermissions;
import com.iot.common.constant.UserConstants;
import com.iot.common.core.controller.BaseController;
import com.iot.common.core.domain.R;
import com.iot.common.log.annotation.OperLog;
import com.iot.common.log.enums.BusinessType;
import com.iot.common.utils.RandomUtil;
import com.iot.system.domain.SysUser;
import com.iot.system.service.ISysMenuService;
import com.iot.system.service.ISysUserService;
import com.iot.system.util.PasswordUtil;

import cn.hutool.core.convert.Convert;

/**
 * 用户 提供者
 *
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("user")
@Api("用户管理")
public class SysUserController extends BaseController {
    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private ISysCompanyService sysCompanyService;

    /**
     * 查询用户
     */
    @ApiOperation(value = "查询用户")
    @GetMapping("get/{userId}")
    public SysUser get(@PathVariable("userId") Long userId) {
        return sysUserService.selectUserById(userId);
    }


    @GetMapping("info")
    public SysUser info(@LoginUser SysUser sysUser) {
        sysUser.setButtons(sysMenuService.selectPermsByUserId(sysUser.getUserId()));
        return sysUser;
    }

    /**
     * 查询用户
     */
    @ApiOperation(value = "查询用户")
    @GetMapping("find/{username}")
    public SysUser findByUsername(@PathVariable("username") String username) {
        SysUser sysUser = sysUserService.selectUserByLoginName(username);
//        SysCompany sysCompany = sysCompanyService.selectSysCompanyByDeptId(sysUser.getDept());
//        if (sysCompany != null) {
//            sysUser.setCompanyId(sysCompany.getId());
//            sysUser.setCompanyName(sysCompany.getCompanyName());
//        }

        return sysUser;
    }

    /**
     * 查询拥有当前角色的所有用户
     */
    @ApiOperation(value = "查询拥有当前角色的所有用户")
    @GetMapping("hasRoles")
    public Set<Long> hasRoles(String roleIds) {
        Long[] arr = Convert.toLongArray(roleIds);
        return sysUserService.selectUserIdsHasRoles(arr);
    }

    /**
     * 查询所有当前部门中的用户
     */
    @ApiOperation(value = "查询所有当前部门中的用户")
    @GetMapping("inDepts")
    public Set<Long> inDept(String deptIds) {
        Long[] arr = Convert.toLongArray(deptIds);
        return sysUserService.selectUserIdsInDepts(arr);
    }

    /**
     * 查询用户列表
     */
    @ApiOperation(value = "查询用户列表")
    @GetMapping("list")
    public R list(SysUser sysUser) {
        startPage();
        return result(sysUserService.selectUserList(sysUser));
    }

    /**
     * 新增保存用户
     */
    @ApiOperation(value = "新增保存用户")
    @HasPermissions("system:user:add")
    @PostMapping("save")
    @OperLog(title = "用户管理", businessType = BusinessType.INSERT)
    public R addSave(@RequestBody SysUser sysUser) {
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(sysUserService.checkLoginNameUnique(sysUser.getLoginName()))) {
            return R.error("新增用户'" + sysUser.getLoginName() + "'失败，登录账号已存在");
        } else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(sysUser))) {
            return R.error("新增用户'" + sysUser.getLoginName() + "'失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(sysUserService.checkEmailUnique(sysUser))) {
            return R.error("新增用户'" + sysUser.getLoginName() + "'失败，邮箱账号已存在");
        }
        sysUser.setSalt(RandomUtil.randomStr(6));
        sysUser.setPassword(
                PasswordUtil.encryptPassword(sysUser.getLoginName(), sysUser.getPassword(), sysUser.getSalt()));
        sysUser.setCreateBy(getLoginName());
        LoginAuthDto loginAuthDto = super.getLoginAuthDto();
        if (loginAuthDto != null) {
            sysUser.setCompanyId(loginAuthDto.getCompanyId());
            sysUser.setCompanyName(loginAuthDto.getCompanyName());
        }
        return toAjax(sysUserService.insertUser(sysUser));
    }

    /**
     * 修改保存用户
     */
    @ApiOperation(value = " 修改保存用户")
    @HasPermissions("system:user:edit")
    @OperLog(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("update")
    public R editSave(@RequestBody SysUser sysUser) {
        if (null != sysUser.getUserId() && SysUser.isAdmin(sysUser.getUserId())) {
            return R.error("不允许修改超级管理员用户");
        } else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(sysUser))) {
            return R.error("修改用户'" + sysUser.getLoginName() + "'失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(sysUserService.checkEmailUnique(sysUser))) {
            return R.error("修改用户'" + sysUser.getLoginName() + "'失败，邮箱账号已存在");
        }
        return toAjax(sysUserService.updateUser(sysUser));
    }

    /**
     * 修改用户信息
     *
     * @param sysUser
     * @return
     * @author zmr
     */
    @ApiOperation(value = "修改用户信息")
    @HasPermissions("system:user:edit")
    @PostMapping("update/info")
    @OperLog(title = "用户管理", businessType = BusinessType.UPDATE)
    public R updateInfo(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.updateUserInfo(sysUser));
    }

    /**
     * 记录登陆信息
     *
     * @param sysUser
     * @return
     * @author zmr
     */
    @PostMapping("update/login")
    public R updateLoginRecord(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.recordLoginInfo(sysUser));
    }

    @HasPermissions("system:user:resetPwd")
    @OperLog(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    public R resetPwdSave(@RequestBody SysUser user) {
        if (null != user.getUserId() && SysUser.isAdmin(user.getUserId())) {
            return R.error("不允许修改超级管理员用户");
        }
        user.setSalt(RandomUtil.randomStr(6));
        user.setPassword(PasswordUtil.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        return toAjax(sysUserService.resetUserPwd(user));
    }

    /**
     * 修改状态
     *
     * @param user
     * @return
     * @author zmr
     */
    @ApiOperation(value = "修改状态")
    @HasPermissions("system:user:edit")
    @PostMapping("status")
    @OperLog(title = "用户管理", businessType = BusinessType.UPDATE)
    public R status(@RequestBody SysUser user) {
        if (null != user.getUserId() && SysUser.isAdmin(user.getUserId())) {
            return R.error("不允许修改超级管理员用户");
        }
        return toAjax(sysUserService.changeStatus(user));
    }

    /**
     * 删除用户
     *
     * @throws Exception
     */
    @ApiOperation(value = "删除用户")
    @HasPermissions("system:user:remove")
    @OperLog(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("remove")
    public R remove(String ids) throws Exception {
        return toAjax(sysUserService.deleteUserByIds(ids));
    }
}
