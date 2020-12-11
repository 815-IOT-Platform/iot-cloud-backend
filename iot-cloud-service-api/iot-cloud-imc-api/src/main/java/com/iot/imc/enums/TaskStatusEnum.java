package com.iot.imc.enums;


import com.iot.common.exception.BusinessException;

/**
 * Created by rongshuai on 2019/12/5 9:18
 */
public enum TaskStatusEnum {

    NO_SUCH_STATUS(-1,"巡检任务被否决"),

    WAITING_FOR_PRINCIPAL(0,"等待负责人审核"),

    WAITING_FOR_FACILITATOR(1,"等待分配服务商"),

    WAITING_FOR_ACCEPT(2,"已分配服务商，等待服务商接单"),

    EXECUTING(3,"服务商已接单"),

    WAITING_FOR_CONFIRM(4,"巡检完成，等待确认结果"),

    WAITING_FOR_PAY(5,"巡检结果已确认，等待付款"),

    WAITING_FOR_REVIEW(6,"巡检已付款，等待甲方负责人评价"),

    INSPECTION_OVER(7,"已完成评价，巡检结束");

    /**
     * The statusNum.
     */
    int statusNum;

    /**
     * The statusMsg.
     */
    String statusMsg;

    TaskStatusEnum(int statusNum,String statusMsg){
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
        for(TaskStatusEnum ele:TaskStatusEnum.values()){
            if(statusNum == ele.getStatusNum()){
                return ele.getStatusMsg();
            }
        }
        throw new BusinessException("找不到状态信息");
    }

    public static TaskStatusEnum getEnum(int statusNum){
        for(TaskStatusEnum ele:TaskStatusEnum.values()){
            if(statusNum == ele.getStatusNum()){
                return ele;
            }
        }
        return TaskStatusEnum.NO_SUCH_STATUS;
    }

}
