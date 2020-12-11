package com.iot.websocket.mapper;

import com.iot.common.core.dao.BaseMapper;
import com.iot.websocket.domain.AnWebsocketMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * websocketMapper接口
 * 
 * @author ananops
 * @date 2020-06-08
 */
public interface WebsocketMsgMapper extends BaseMapper<AnWebsocketMsg>
{
    /**
     * 查询websocket
     * 
     * @param id websocketID
     * @return websocket
     */
    public AnWebsocketMsg selectAnWebsocketMsgById(Long id);

    /**
     * 查询websocket列表
     * 
     * @param anWebsocketMsg websocket
     * @return websocket集合
     */
    public List<AnWebsocketMsg> selectAnWebsocketMsgList(AnWebsocketMsg anWebsocketMsg);

    /**
     * 新增websocket
     * 
     * @param anWebsocketMsg websocket
     * @return 结果
     */
    public int insertAnWebsocketMsg(AnWebsocketMsg anWebsocketMsg);

    /**
     * 修改websocket
     * 
     * @param anWebsocketMsg websocket
     * @return 结果
     */
    public int updateAnWebsocketMsg(AnWebsocketMsg anWebsocketMsg);

    /**
     * 删除websocket
     * 
     * @param id websocketID
     * @return 结果
     */
    public int deleteAnWebsocketMsgById(Long id);

    /**
     * 批量删除websocket
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnWebsocketMsgByIds(String[] ids);

    /**
     * 查询消息
     * @param userId
     * @param status
     * @param msgType
     * @return
     */
    public List<AnWebsocketMsg> getWebsocketMsg(@Param("userId") Long userId,@Param("status")Integer status,@Param("msgType")String msgType);
}
