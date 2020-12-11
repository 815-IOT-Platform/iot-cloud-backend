package com.iot.common.enums;

import com.iot.common.exception.BusinessException;

/**
 * Created by rongshuai on 2020/6/5 21:07
 */
public enum WsMsgType {
    NO_SUCH_STATUS(-1,"没有这种类型","NO_SUCH_STATUS"),
    
    IMC_TASK_STATUS(0,"巡检任务状态消息","IMC_TASK_STATUS"),

    IMC_ITEM_STATUS(1,"巡检任务子项状态消息","IMC_ITEM_STATUS"),

    MDMC_TASK_STATUS(2,"维修工单状态消息","MDMC_TASK_STATUS");

    /**
     * The typeNum.
     */
    int typeNum;

    /**
     * The typeMsg.
     */
    String typeMsg;

    /**
     * 类型
     */
    String type;

    WsMsgType(int typeNum,String typeMsg,String type){
        this.typeNum = typeNum;
        this.typeMsg = typeMsg;
        this.type = type;
    }

    public int getTypeNum() {
        return typeNum;
    }

    public String getTypeMsg() {
        return typeMsg;
    }

    public String getType() {
        return type;
    }

    public static String getTypeMsg(int typeNum){
        for(WsMsgType ele:WsMsgType.values()){
            if(typeNum == ele.getTypeNum()){
                return ele.getTypeMsg();
            }
        }
        throw new BusinessException("获取msg错误");
    }

    public static WsMsgType getEnum(int typeNum){
        for(WsMsgType ele:WsMsgType.values()){
            if(typeNum == ele.getTypeNum()){
                return ele;
            }
        }
        return WsMsgType.NO_SUCH_STATUS;
    }
}
