package com.iot.system.service.impl;

import java.util.*;
import java.util.concurrent.TimeUnit;

import com.iot.common.redis.util.RedisKeyUtil;
import com.iot.common.utils.RandomUtil;
import com.iot.system.dto.UserRegisterDto;
import com.iot.system.manager.UserManager;
import com.iot.system.service.RedisService;
import com.iot.system.util.PasswordUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.common.annotation.DataScope;
import com.iot.common.constant.UserConstants;
import com.iot.common.core.text.Convert;
import com.iot.common.exception.BusinessException;
import com.iot.common.utils.StringUtils;
import com.iot.common.utils.security.Md5Utils;
import com.iot.system.domain.SysPost;
import com.iot.system.domain.SysRole;
import com.iot.system.domain.SysUser;
import com.iot.system.domain.SysUserPost;
import com.iot.system.domain.SysUserRole;
import com.iot.system.mapper.SysPostMapper;
import com.iot.system.mapper.SysRoleMapper;
import com.iot.system.mapper.SysUserMapper;
import com.iot.system.mapper.SysUserPostMapper;
import com.iot.system.mapper.SysUserRoleMapper;
import com.iot.system.service.ISysConfigService;
import com.iot.system.service.ISysUserService;

import cn.hutool.core.util.ArrayUtil;

import javax.annotation.Resource;

/**
 * 用户 业务层处理
 * 
 * @author ananops
 */
@Service
public class SysUserServiceImpl implements ISysUserService
{
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Resource
    private SysUserMapper       userMapper;

    @Autowired
    private SysRoleMapper       roleMapper;

    @Autowired
    private SysPostMapper       postMapper;

    @Autowired
    private SysUserPostMapper   userPostMapper;

    @Autowired
    private SysUserRoleMapper   userRoleMapper;

    @Autowired
    private ISysConfigService   configService;

    @Autowired
    private UserManager userManager;

    @Value("${ananops.auth.active-user-url}")
    private String activeUserUrl;

    @Resource
    private RedisService redisService;
    /**
     * 根据条件分页查询用户列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SysUser> selectUserList(SysUser user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 根据条件分页查询已分配用户角色列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SysUser> selectAllocatedList(SysUser user)
    {
        return userMapper.selectAllocatedList(user);
    }

    /**
     * 根据条件分页查询未分配用户角色列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SysUser> selectUnallocatedList(SysUser user)
    {
        return userMapper.selectUnallocatedList(user);
    }

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByLoginName(String userName)
    {
        return userMapper.selectUserByLoginName(userName);
    }

    /**
     * 通过手机号码查询用户
     * 
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByPhoneNumber(String phoneNumber)
    {
        return userMapper.selectUserByPhoneNumber(phoneNumber);
    }

    /**
     * 通过邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByEmail(String email)
    {
        return userMapper.selectUserByEmail(email);
    }

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId)
    {
        return userMapper.selectUserById(userId);
    }

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long userId)
    {
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 删除用户与岗位表
        userPostMapper.deleteUserPostByUserId(userId);
        return userMapper.deleteUserById(userId);
    }

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids) throws BusinessException
    {
        Long[] userIds = Convert.toLongArray(ids);
        for (Long userId : userIds)
        {
            if (SysUser.isAdmin(userId))
            {
                throw new BusinessException("不允许删除超级管理员用户");
            }
        }
        return userMapper.deleteUserByIds(userIds);
    }

    /**
     * 新增保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertUser(SysUser user)
    {
        // 新增用户信息
        int rows = userMapper.insertUser(user);
        // 新增用户岗位关联
        insertUserPost(user);
        // 新增用户与角色管理
        insertUserRole(user);
        return rows;
    }

    /**
     * 修改保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUser(SysUser user)
    {
        Long userId = user.getUserId();
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 新增用户与角色管理
        insertUserRole(user);
        // 删除用户与岗位关联
        userPostMapper.deleteUserPostByUserId(userId);
        // 新增用户与岗位管理
        insertUserPost(user);
        return userMapper.updateUser(user);
    }

    @Override
    public int recordLoginInfo(SysUser user) {
        return userMapper.recordLoginInfo(user);
    }


    /**
     * 修改用户个人详细信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(SysUser user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户密码
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetUserPwd(SysUser user)
    {
        return updateUserInfo(user);
    }

    /**
     * 新增用户角色信息
     * 
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user)
    {
        List<Long> roles = user.getRoleIds();
        if (StringUtils.isNotNull(roles))
        {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<SysUserRole>();
            for (Long roleId : roles)
            {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(roleId);
                ur.setCompanyId(user.getCompanyId());
                list.add(ur);
            }
            if (list.size() > 0)
            {
                userRoleMapper.batchUserRole(list);
            }
        }
    }

    /**
     * 新增用户岗位信息
     * 
     * @param user 用户对象
     */
    public void insertUserPost(SysUser user)
    {
        Long[] posts = user.getPostIds();
        if (StringUtils.isNotNull(posts))
        {
            // 新增用户与岗位管理
            List<SysUserPost> list = new ArrayList<SysUserPost>();
            for (Long postId : posts)
            {
                SysUserPost up = new SysUserPost();
                up.setUserId(user.getUserId());
                up.setPostId(postId);
                list.add(up);
            }
            if (list.size() > 0)
            {
                userPostMapper.batchUserPost(list);
            }
        }
    }

    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 用户名
     * @return
     */
    @Override
    public String checkLoginNameUnique(String loginName)
    {
        int count = userMapper.checkLoginNameUnique(loginName);
        if (count > 0)
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkPhoneUnique(SysUser user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = userMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkEmailUnique(SysUser user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = userMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.USER_EMAIL_NOT_UNIQUE;
        }
        return UserConstants.USER_EMAIL_UNIQUE;
    }

    /**
     * 查询用户所属角色组
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(Long userId)
    {
        List<SysRole> list = roleMapper.selectRolesByUserId(userId);
        StringBuffer idsStr = new StringBuffer();
        for (SysRole role : list)
        {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 查询用户所属岗位组
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(Long userId)
    {
        List<SysPost> list = postMapper.selectPostsByUserId(userId);
        StringBuffer idsStr = new StringBuffer();
        for (SysPost post : list)
        {
            idsStr.append(post.getPostName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 导入用户数据
     * 
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(userList) || userList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (SysUser user : userList)
        {
            try
            {
                // 验证是否存在这个用户
                SysUser u = userMapper.selectUserByLoginName(user.getLoginName());
                if (StringUtils.isNull(u))
                {
                    user.setPassword(Md5Utils.hash(user.getLoginName() + password));
                    user.setCreateBy(operName);
                    this.insertUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    user.setUpdateBy(operName);
                    this.updateUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getLoginName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getLoginName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 用户状态修改
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int changeStatus(SysUser user)
    {
        if (SysUser.isAdmin(user.getUserId()))
        {
            throw new BusinessException("不允许修改超级管理员用户");
        }
        return userMapper.updateUser(user);
    }

    /* (non-Javadoc)
     * @see com.ananops.system.service.ISysUserService#selectUserHasRole(java.lang.Long)
     */
    @Override
    public Set<Long> selectUserIdsHasRoles(Long[] roleIds)
    {
        return ArrayUtil.isNotEmpty(roleIds) ? userMapper.selectUserIdsHasRoles(roleIds) : null;
    }

    /* (non-Javadoc)
     * @see com.ananops.system.service.ISysUserService#selectUserInDept(java.lang.Long)
     */
    @Override
    public Set<Long> selectUserIdsInDepts(Long[] deptIds)
    {
        return ArrayUtil.isNotEmpty(deptIds) ? userMapper.selectUserIdsInDepts(deptIds) : null;
    }

    @Override
    public void register(UserRegisterDto registerDto) {
        // 校验注册信息
        validateRegisterInfo(registerDto);
        String phonenumber = registerDto.getPhonenumber();
        String email = registerDto.getEmail();
        Date row = new Date();
        // 封装注册信息
        SysUser sysUser = new SysUser();
        sysUser.setLoginName(registerDto.getLoginName());
        sysUser.setSalt(RandomUtil.randomStr(6));
        sysUser.setPassword(PasswordUtil.encryptPassword(sysUser.getLoginName(), sysUser.getPassword(), sysUser.getSalt()));
        sysUser.setPhonenumber(phonenumber);
        sysUser.setStatus("1");  //停用
        sysUser.setCreateTime(row);
        sysUser.setUpdateTime(row);
        sysUser.setEmail(email);
        sysUser.setCreateBy(registerDto.getLoginName());
        sysUser.setUserName(registerDto.getLoginName());
        sysUser.setUpdateBy(registerDto.getLoginName());
        sysUser.setCompanyId(registerDto.getCompanyId());
        sysUser.setCompanyName(registerDto.getCompanyName());

        // 发送激活邮件
        String activeToken = UUID.randomUUID().toString();
        redisService.setKey(RedisKeyUtil.getActiveUserKey(activeToken), email, 1, TimeUnit.DAYS);

        String param =  activeUserUrl + activeToken;


        Set<String> to = Sets.newHashSet();
        to.add(registerDto.getEmail());

        userManager.register(sysUser,param);

    }

    @Override
    public void activeUser(String activeUserToken) {
        Preconditions.checkArgument(!org.springframework.util.StringUtils.isEmpty(activeUserToken), "激活用户失败");
        String activeUserKey = RedisKeyUtil.getActiveUserKey(activeUserToken);

        String email = redisService.getKey(activeUserKey);

        if (org.springframework.util.StringUtils.isEmpty(email)) {
            throw new BusinessException("激活失败, 链接已过期");
        }
        // 修改用户状态, 绑定访客角色
        SysUser sysUser = this.selectUserByEmail(email);
        if (sysUser == null) {
            log.error("找不到用户信息. email={}", email);
            throw new BusinessException("找不到用户信息");
        }
        sysUser.setStatus("0");
        userManager.activeUser( sysUser, activeUserKey);
    }

    private void validateRegisterInfo(UserRegisterDto registerDto) {
        String mobileNo = registerDto.getPhonenumber();

        Preconditions.checkArgument(!org.springframework.util.StringUtils.isEmpty(registerDto.getLoginName()), "登录名不能为空");
        Preconditions.checkArgument(!org.springframework.util.StringUtils.isEmpty(registerDto.getEmail()), "邮箱不能为空");
        Preconditions.checkArgument(!org.springframework.util.StringUtils.isEmpty(mobileNo), "手机号不能为空");
        Preconditions.checkArgument(!org.springframework.util.StringUtils.isEmpty(registerDto.getPassword()), "新密码不能为空");
        Preconditions.checkArgument(!org.springframework.util.StringUtils.isEmpty(registerDto.getConfirmPwd()), "确认密码不能为空");
        Preconditions.checkArgument(registerDto.getPassword().equals(registerDto.getConfirmPwd()), "两次密码不一致");

        SysUser sysUser = new SysUser();
        sysUser.setLoginName(registerDto.getLoginName());
        sysUser.setPhonenumber(registerDto.getPhonenumber());
        sysUser.setEmail(registerDto.getEmail());


        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(this.checkLoginNameUnique(sysUser.getLoginName()))) {
            throw new BusinessException("新增用户'" + sysUser.getLoginName() + "'失败，登录账号已存在");
        } else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(this.checkPhoneUnique(sysUser))) {
            throw new BusinessException("新增用户'" + sysUser.getLoginName() + "'失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(this.checkEmailUnique(sysUser))) {
            throw new BusinessException("新增用户'" + sysUser.getLoginName() + "'失败，邮箱账号已存在");
        }

    }

}
