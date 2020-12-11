package com.iot.common.utils.dto;

import com.iot.common.constant.Constants;
import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.redis.util.RedisUtils;
import com.iot.common.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By ChengHao On 2020/6/22
 */
@Component
public class LoginAuthUtil {
    @Autowired
    RedisUtils redis;
    /**
     * 获取当前用户信息
     * @return
     */
    public LoginAuthDto getLoginAuthDto(){
        // 获取当前的用户
        HttpServletRequest request = ServletUtils.getRequest();
        String token = request.getHeader("token");
        return redis.get(Constants.ACCESS_TOKEN + token,LoginAuthDto.class);
    }
}
