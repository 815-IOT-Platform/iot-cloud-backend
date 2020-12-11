package com.iot.common.utils.bean;

import com.iot.common.core.dto.LoginAuthDto;
import com.iot.common.exception.BusinessException;
import com.iot.common.utils.DateUtils;
import com.iot.common.zk.generator.UniqueIdGenerator;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by rongshuai on 2020/5/19 18:30
 */
public class UpdateInfoUtil {
    public static void setUpdateInfo(Object pojo, boolean isNew, LoginAuthDto user) {
        if (isNew) {
            setInsertInfo(pojo, user);
        } else {
            setModifyInfo(pojo, user);
        }

    }

    public static void setInsertInfo(Object pojo, LoginAuthDto user) {
        Date nowTime = DateUtils.getNowDate();
        Class clazz = pojo.getClass();
        Method[] methods = clazz.getMethods();
        try {
            for (Method method : methods) {
                switch (method.getName()) {
                    case "setId":
                        method.invoke(pojo,UniqueIdGenerator.generateId());
                        break;
                    case "setCreateBy":
                    case "setUpdateBy":
                        method.invoke(pojo,user.getUserName());
                        break;
                    case "setCreatorId":
                    case "setLastOperatorId":
                        method.invoke(pojo,user.getUserId());
                        break;
                    case "setCreateTime":
                    case "setUpdateTime":
                        method.invoke(pojo,nowTime);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("反射执行异常");
        }
    }

    public static void setModifyInfo(Object pojo, LoginAuthDto user) {
        Date nowTime = DateUtils.getNowDate();
        Class clazz = pojo.getClass();
        Method[] methods = clazz.getMethods();
        try {
            for (Method method : methods) {
                switch (method.getName()) {
                    case "setUpdateBy":
                        method.invoke(pojo,user.getUserName());
                        break;
                    case "setLastOperatorId":
                        method.invoke(pojo,user.getUserId());
                        break;
                    case "setUpdateTime":
                        method.invoke(pojo,nowTime);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("反射执行异常");
        }
    }
}
