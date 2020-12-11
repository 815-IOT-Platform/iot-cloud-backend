package com.iot.tpc.mapper;

import com.iot.common.core.dao.BaseMapper;
import com.iot.tpc.domain.TpcMqSubscribe;
import java.util.List;

/**
 * 订阅关系Mapper接口
 * 
 * @author ananops
 * @date 2020-06-17
 */
public interface TpcMqSubscribeMapper extends BaseMapper<TpcMqSubscribe>
{
    /**
     * 查询订阅关系
     * 
     * @param id 订阅关系ID
     * @return 订阅关系
     */
    public TpcMqSubscribe selectTpcMqSubscribeById(Long id);

    /**
     * 查询订阅关系列表
     * 
     * @param tpcMqSubscribe 订阅关系
     * @return 订阅关系集合
     */
    public List<TpcMqSubscribe> selectTpcMqSubscribeList(TpcMqSubscribe tpcMqSubscribe);

    /**
     * 新增订阅关系
     * 
     * @param tpcMqSubscribe 订阅关系
     * @return 结果
     */
    public int insertTpcMqSubscribe(TpcMqSubscribe tpcMqSubscribe);

    /**
     * 修改订阅关系
     * 
     * @param tpcMqSubscribe 订阅关系
     * @return 结果
     */
    public int updateTpcMqSubscribe(TpcMqSubscribe tpcMqSubscribe);

    /**
     * 删除订阅关系
     * 
     * @param id 订阅关系ID
     * @return 结果
     */
    public int deleteTpcMqSubscribeById(Long id);

    /**
     * 批量删除订阅关系
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTpcMqSubscribeByIds(String[] ids);
}
