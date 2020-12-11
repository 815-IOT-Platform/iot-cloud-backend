package com.iot.websocket.enums;

import com.iot.common.exception.BusinessException;

/**
 * Created by rongshuai on 2020/6/8 15:09
 */
public enum WsMsgStatusEnum {
    NO_SUCH_STATUS(-1,"没这种状态"),

    WAITING_FOR_CONSUME(0,"等待被消费"),

    CONSUMED(1,"已经被消费");

    /**
     * The statusNum.
     */
    int statusNum;

    /**
     * The statusMsg.
     */
    String statusMsg;

    WsMsgStatusEnum(int statusNum,String statusMsg){
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
        for(WsMsgStatusEnum ele:WsMsgStatusEnum.values()){
            if(statusNum == ele.getStatusNum()){
                return ele.getStatusMsg();
            }
        }
        throw new BusinessException("找不到状态信息");
    }

    public static WsMsgStatusEnum getEnum(int statusNum){
        for(WsMsgStatusEnum ele:WsMsgStatusEnum.values()){
            if(statusNum == ele.getStatusNum()){
                return ele;
            }
        }
        return WsMsgStatusEnum.NO_SUCH_STATUS;
    }
}
