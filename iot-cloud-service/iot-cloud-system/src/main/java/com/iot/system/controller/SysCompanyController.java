package com.iot.system.controller;

import com.iot.common.core.dto.LoginAuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.iot.common.core.domain.R;
import com.iot.common.core.controller.BaseController;
import com.iot.system.domain.SysCompany;
import com.iot.system.service.ISysCompanyService;

/**
 * 企业管理 提供者
 *
 * @author ananops
 * @date 2020-05-25
 */
@RestController
@RequestMapping("company")
@Api("企业管理")
public class SysCompanyController extends BaseController {

    @Autowired
    private ISysCompanyService sysCompanyService;

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("get/{id}")
    public SysCompany get(@PathVariable("id") Long id) {
        return sysCompanyService.selectSysCompanyById(id);

    }

    /**
     * 查询企业管理列表
     */
    @ApiOperation(value = "查询企业列表")
    @GetMapping("list")
    public R list(SysCompany sysCompany) {
        startPage();
        return result(sysCompanyService.selectSysCompanyList(sysCompany));
    }


    /**
     * 新增保存企业管理
     */
    @ApiOperation(value = "新增保存企业")
    @PostMapping("save")
    public R addSave(@RequestBody SysCompany sysCompany) {
        LoginAuthDto user = getLoginAuthDto();
        return toAjax(sysCompanyService.insertSysCompany(sysCompany, user));
    }

    /**
     * 修改保存企业管理
     */
    @ApiOperation(value = "修改保存企业")
    @PostMapping("update")
    public R editSave(@RequestBody SysCompany sysCompany) {
        LoginAuthDto user = getLoginAuthDto();
        return toAjax(sysCompanyService.updateSysCompany(sysCompany,user));
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @PostMapping("remove")
    public R remove(String ids) {
        return toAjax(sysCompanyService.deleteSysCompanyByIds(ids));
    }

}
