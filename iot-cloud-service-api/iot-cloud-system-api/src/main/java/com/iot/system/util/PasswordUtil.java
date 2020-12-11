package com.iot.system.util;

import com.iot.common.utils.security.Md5Utils;
import com.iot.system.domain.SysUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordUtil
{
    public static boolean matches(SysUser user, String newPassword)
    {
        log.info("SysUser-->getPassword--->{}",user.getPassword());
        log.info("newPassword-->{}--->{}--->{}",user.getLoginName(),user.getSalt(),encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public static String encryptPassword(String username, String password, String salt)
    {
        return Md5Utils.hash(username + password + salt);
    }
}