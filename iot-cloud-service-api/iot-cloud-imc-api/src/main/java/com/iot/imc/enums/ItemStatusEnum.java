package com.iot.imc.enums;


import com.iot.common.exception.BusinessException;

/**
 * Created by rongshuai on 2019/12/5 10:53
 */
public enum ItemStatusEnum {

    NO_SUCH_STATUS(-1,"没有这种状态"),

    WAITING_FOR_MAINTAINER(1,"巡检任务子项已创建，等待分配工程师"),

    WAITING_FOR_ACCEPT(2,"已分配工程师，等待工程师接单"),

    IN_THE_INSPECTION(3,"工程师已接单"),

    INSPECTION_OVER(4,"巡检任务子项结束，等待甲方负责人审核"),

    VERIFIED(5,"甲方负责人已经审核完毕");
    /**
     * The statusNum.
     */
    int statusNum;

    /**
     * The statusMsg.
     */
    String statusMsg;

    ItemStatusEnum(int statusNum,String statusMsg){
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
        for(ItemStatusEnum ele:ItemStatusEnum.values()){
            if(statusNum == ele.getStatusNum()){
                return ele.getStatusMsg();
            }
        }
        throw new BusinessException("找不到状态信息");
    }

    public static ItemStatusEnum getEnum(int statusNum){
        for(ItemStatusEnum ele:ItemStatusEnum.values()){
            if(statusNum == ele.getStatusNum()){
                return ele;
            }
        }
        return ItemStatusEnum.NO_SUCH_STATUS;
    }

}
