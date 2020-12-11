package com.iot.imc.enums;

import com.iot.common.exception.BusinessException;

/**
 * Created by rongshuai on 2020/5/25 14:25
 */
public enum TaskTypeEnum {
    NO_SUCH_STATUS(-1,"没有这种状态"),

    TEMPORARY_TASK(0,"临时发起的任务"),

    FROM_PROJECT(1,"从项目中发起的任务");

    /**
     * The statusNum.
     */
    int statusNum;

    /**
     * The statusMsg.
     */
    String statusMsg;

    TaskTypeEnum(int statusNum,String statusMsg){
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
        for(TaskTypeEnum ele:TaskTypeEnum.values()){
            if(statusNum == ele.getStatusNum()){
                return ele.getStatusMsg();
            }
        }
        throw new BusinessException("获取msg错误");
    }

    public static TaskTypeEnum getEnum(int statusNum){
        for(TaskTypeEnum ele:TaskTypeEnum.values()){
            if(statusNum == ele.getStatusNum()){
                return ele;
            }
        }
        return TaskTypeEnum.NO_SUCH_STATUS;
    }
}
