package com.iot.tpc.mapper;

import com.iot.common.core.dao.BaseMapper;
import com.iot.tpc.domain.TpcMqConfirm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订阅者状态确认Mapper接口
 * 
 * @author ananops
 * @date 2020-06-17
 */
public interface TpcMqConfirmMapper extends BaseMapper<TpcMqConfirm>
{
    /**
     * Confirm receive message.
     *
     * @param confirmId the confirm id
     */
    void confirmReceiveMessage(@Param("cid") Long confirmId);

    /**
     * Confirm consumed message.
     *
     * @param confirmId the confirm id
     */
    void confirmConsumedMessage(@Param("cid") Long confirmId);

    /**
     * Gets id mq confirm.
     *
     * @param cid        the cid
     * @param messageKey the message key
     *
     * @return the id mq confirm
     */
    Long getIdMqConfirm(@Param("cid") String cid, @Param("messageKey") String messageKey);

    /**
     * Batch create mq confirm int.
     *
     * @param list the list
     *
     * @return the int
     */
    int batchCreateMqConfirm(@Param("tpcMqConfirmList") List<TpcMqConfirm> list);

    /**
     * 查询没有消费的数量.
     *
     * @param messageKey the message key
     *
     * @return the int
     */
    int selectUnConsumedCount(@Param("messageKey") String messageKey);
}
