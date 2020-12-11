/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MqProducerChangeListener.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.tpc.listener;

import com.alibaba.fastjson.JSON;
import com.iot.common.config.GlobalConstants;
import com.iot.common.zk.registry.base.ReliableMessageRegisterDto;
import com.iot.common.zk.registry.exception.RegExceptionHandler;
import com.iot.tpc.service.ITpcMqProducerService;
import com.iot.tpc.service.MqProducerBeanFactory;
import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * The class Mq producer change listener.
 *
 * @author ananops.com @gmail.com
 */
@Component
@Slf4j
public class MqProducerChangeListener implements TreeCacheListener {
	@Resource
	private ITpcMqProducerService tpcMqProducerService;

	/**
	 * Child event.
	 *
	 * @param client the client
	 * @param event  the event
	 */
	@Override
	public void childEvent(final CuratorFramework client, final TreeCacheEvent event) {
		ChildData data = event.getData();
		if (data == null) {
			return;
		}
		String path = data.getPath();
		if (GlobalConstants.ZK_REGISTRY_PRODUCER_ROOT_PATH.equals(path) || GlobalConstants.ZK_REGISTRY_CONSUMER_ROOT_PATH.equals(path)) {
			return;
		}
		String[] split = path.split(GlobalConstants.Symbol.SLASH);
		String dataStr = new String(data.getData());
		switch (event.getType()) {
			case NODE_ADDED:
				log.info("MqProducerChangeListener CHILD_ADDED path={}, data={}", path, dataStr);
				if (split.length == GlobalConstants.Number.SIX_INT) {
					String appPath = path.substring(0, path.lastIndexOf(GlobalConstants.Symbol.SLASH));

					ReliableMessageRegisterDto dto = JSON.parseObject(getDirectly(client, appPath), ReliableMessageRegisterDto.class);
					if (this.getNumChildren(client, appPath) > 0) {
						tpcMqProducerService.updateOnLineStatusByPid(dto.getProducerGroup());
						MqProducerBeanFactory.buildProducerBean(dto);
						MqProducerBeanFactory.putPid(dto.getProducerGroup());
					}
				}
				break;
			case NODE_REMOVED:
				log.info("MqProducerChangeListener NODE_REMOVED path={}, data={}", path, dataStr);
				if (split.length == GlobalConstants.Number.SIX_INT) {
					String appPath = path.substring(0, path.lastIndexOf(GlobalConstants.Symbol.SLASH));

					ReliableMessageRegisterDto dto = JSON.parseObject(getDirectly(client, appPath), ReliableMessageRegisterDto.class);
					if (this.getNumChildren(client, appPath) < 1) {
						tpcMqProducerService.updateOffLineStatusByPid(dto.getProducerGroup());
						MqProducerBeanFactory.rmPid(dto.getProducerGroup());
					}
				}
				break;
			case NODE_UPDATED:
				log.error("本次版本不对更新做处理, path={}, data={}", path, new String(data.getData()));
				break;
			default:
				break;
		}
	}

	private int getNumChildren(CuratorFramework client, final String key) {
		log.info("获取生产者节点个数 path={}", key);
		Stat stat = null;
		try {
			stat = client.checkExists().forPath(key);
		} catch (final Exception ex) {
			log.error("获取目录 key={}的子节点个数出现异常={}", key, ex.getMessage(), ex);
		}
		return stat == null ? 0 : stat.getNumChildren();
	}

	private String getDirectly(CuratorFramework client, final String key) {
		try {
			return new String(client.getData().forPath(key), Charsets.UTF_8);
		} catch (final Exception ex) {
			RegExceptionHandler.handleException(ex);
			return null;
		}
	}
}
