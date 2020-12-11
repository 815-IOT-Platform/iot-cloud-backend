package com.iot.imc.enums;

import com.iot.common.exception.BusinessException;

/**
 * Created by rongshuai on 2020/5/25 22:38
 */
public enum  RoleEnum {
    NO_SUCH_STATUS(-1,"没有这种角色"),

    PRINCIPAL(1,"甲方负责人"),

    FACILITATOR(2,"服务商负责人");

    /**
     * The statusNum.
     */
    int statusNum;

    /**
     * The statusMsg.
     */
    String statusMsg;

    RoleEnum(int statusNum,String statusMsg){
        this.statusNum = statusNum;
        this.statusMsg = statusMsg;
    }

    public int getStatusNum() {
        return statusNum;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public static String getStatusMsg(int statusNum){
        for(RoleEnum ele:RoleEnum.values()){
            if(statusNum == ele.getStatusNum()){
                return ele.getStatusMsg();
            }
        }
        throw new BusinessException("获取msg错误");
    }

    public static RoleEnum getEnum(int statusNum){
        for(RoleEnum ele:RoleEnum.values()){
            if(statusNum == ele.getStatusNum()){
                return ele;
            }
        }
        return RoleEnum.NO_SUCH_STATUS;
    }
}
