package com.iot.system.controller;

import com.iot.common.core.controller.BaseController;
import com.iot.common.core.domain.R;
import com.iot.system.dto.CompanyRegisterDto;
import com.iot.system.dto.UserRegisterDto;
import com.iot.system.service.ISysCompanyService;
import com.iot.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created By ChengHao On 2020/6/3
 */
@RestController
@RequestMapping(value = "/auth")
@Api(value = "Web-AuthRestController")
public class AuthRestController extends BaseController {
    @Resource
    ISysUserService sysUserService;

    @Resource
    ISysCompanyService sysCompanyService;

    /**
     * 注册用户.
     *
     * @param user the user
     *
     * @return the wrapper
     */
    @PostMapping(value = "/register")
    @ApiOperation(httpMethod = "POST", value = "注册用户")
    public R registerUser(UserRegisterDto user) {
        sysUserService.register(user);
        return R.ok("注册成功");
    }

    /**
     * 注册一个服务商
     *
     * @param company 初始注册信息
     *
     * @return the Wrapper
     */
    @PostMapping(value = "/registerCompany")
    @ApiOperation(httpMethod = "POST", value = "注册服务商")
    public R registerSpcCompany(@ApiParam(name = "company", value = "服务商信息") @RequestBody CompanyRegisterDto company) {
        logger.info("注册服务商");
        sysCompanyService.register(company);
        return R.ok("注册成功");
    }

    /**
     * 激活用户.
     *
     * @param activeUserToken the active user token
     *
     * @return the wrapper
     */
    @GetMapping(value = "/activeUser/{activeUserToken}")
    @ApiOperation(value = "激活用户")
    public R activeUser(@PathVariable String activeUserToken) {
        sysUserService.activeUser(activeUserToken);
        return R.ok("激活成功");
    }

}
