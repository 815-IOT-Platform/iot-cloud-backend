package com.iot.system.config;

import java.util.ArrayList;
import java.util.List;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.utils.dto.LoginAuthUtil;
import com.iot.system.feign.RemoteUserService;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

/**
 * Created By ChengHao On 2020/6/21
 */
@Slf4j
@Configuration
@MapperScan("com.iot.system.mapper*")
public class MybatisPlusConfig {
    @Autowired
    private LoginAuthUtil loginAuthUtil;

    //TODO 无法注入？
//    @Autowired
//    private ISysUserService sysUserService;

    @Autowired
    private RemoteUserService userService;


    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        /*
         * 【测试多租户】 SQL 解析处理拦截器<br>
         */
        List<ISqlParser> sqlParserList = new ArrayList<>();
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId() {
//                HttpServletRequest request = ServletUtils.getRequest();
//                String loginName = request.getHeader(Constants.CURRENT_USERNAME);
                Long companyId = null;
                LoginAuthDto loginAuthDto = loginAuthUtil.getLoginAuthDto();
                if (loginAuthDto == null) {

                }
                companyId = loginAuthDto.getCompanyId();
                if (companyId == null) {
                    //默认为1
                    companyId = 1L;
                }
                return new LongValue(companyId);
            }

            @Override
            public String getTenantIdColumn() {
                return "company_id";
            }

            @Override
            public boolean doTableFilter(String tableName) {
                // 这里可以判断是否过滤表
                if ("sys_company".equals(tableName) || "districts".equals(tableName) || "mail_send_log".equals(tableName)
                        || "sys_menu".equals(tableName) || "districts".equals(tableName) || tableName.startsWith("gen")
                        || "sys_oper_log".equals(tableName) || "sys_logininfor".equals(tableName)   || "sys_oper_log".equals(tableName)
                ) {
                    return true;
                }
                return false;
            }
        });

        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
                // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
                if ("com.iot.system.mapper.SysUserMapper.selectUserByLoginName".equals(ms.getId())
                        || "com.iot.system.mapper.SysUserMapper.selectUserById".equals(ms.getId())

                        || "com.iot.system.mapper.SysUserMapper.selectUserByEmail".equals(ms.getId())
                        || "com.iot.system.mapper.SysUserMapper.recordLoginInfo".equals(ms.getId())
                        || "com.iot.system.mapper.SysUserMapper.checkLoginNameUnique".equals(ms.getId())
                        || "com.iot.system.mapper.SysUserMapper.checkPhoneUnique".equals(ms.getId())
                        || "com.iot.system.mapper.SysUserMapper.checkEmailUnique".equals(ms.getId())
                        || "com.iot.system.mapper.SysUserMapper.insertUser".equals(ms.getId())
                        || "com.iot.system.mapper.SysUserMapper.updateUser".equals(ms.getId())
                        || "com.iot.system.mapper.SysUserRoleMapper.batchUserRole".equals(ms.getId())
                        || "com.iot.system.mapper.SysRoleMapper.selectRoleList".equals(ms.getId())
                        || "com.iot.system.mapper.SysRoleMapper.selectRolesByUserId".equals(ms.getId())
                        || "com.iot.system.mapper.SysRoleMapper.selectRoleById".equals(ms.getId())
                ) {
                    return true;
                }
                return false;
            }
        });
        return paginationInterceptor;
    }

    /**
     * 性能分析拦截器，不建议生产使用
     */
//    @Bean
//    public PerformanceInterceptor performanceInterceptor(){
//        return new PerformanceInterceptor();
//    }
}
