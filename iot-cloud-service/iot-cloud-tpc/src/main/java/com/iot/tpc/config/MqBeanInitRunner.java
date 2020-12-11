/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MqBeanInitRunner.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.iot.tpc.config;

import com.alibaba.fastjson.JSON;
import com.iot.common.config.GlobalConstants;
import com.iot.common.config.properties.IotProperties;
import com.iot.common.zk.registry.RegistryCenterFactory;
import com.iot.common.zk.registry.base.CoordinatorRegistryCenter;
import com.iot.common.zk.registry.base.ReliableMessageRegisterDto;
import com.iot.tpc.listener.MqConsumerChangeListener;
import com.iot.tpc.listener.MqProducerChangeListener;
import com.iot.tpc.service.ITpcMqProducerService;
import com.iot.tpc.service.MqProducerBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * The class Redis init runner.
 *
 * @author ananops.com @gmail.com
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class MqBeanInitRunner implements CommandLineRunner {
	@Resource
	private IotProperties ananOpsProperties;
	@Resource
	private MqProducerChangeListener producerChangeListener;
	@Resource
	private MqConsumerChangeListener consumerChangeListener;
	@Resource
	private ITpcMqProducerService tpcMqProducerService;

	/**
	 * Run.
	 *
	 * @param args the args
	 */
	@Override
	public void run(String... args) throws Exception {
		CoordinatorRegistryCenter coordinatorRegistryCenter = RegistryCenterFactory.createCoordinatorRegistryCenter(ananOpsProperties.getZk());
		List<String> childrenKeys = coordinatorRegistryCenter.getChildrenKeys(GlobalConstants.ZK_REGISTRY_PRODUCER_ROOT_PATH);
		this.initMqListener(coordinatorRegistryCenter);
		for (final String childrenKey : childrenKeys) {
			int count = coordinatorRegistryCenter.getNumChildren(GlobalConstants.ZK_REGISTRY_PRODUCER_ROOT_PATH + GlobalConstants.Symbol.SLASH + childrenKey);
			if (count == 0) {
				continue;
			}
			String producerString = coordinatorRegistryCenter.getDirectly(GlobalConstants.ZK_REGISTRY_PRODUCER_ROOT_PATH + GlobalConstants.Symbol.SLASH + childrenKey);
			ReliableMessageRegisterDto producerDto = JSON.parseObject(producerString, ReliableMessageRegisterDto.class);
			MqProducerBeanFactory.buildProducerBean(producerDto);
			try {
				tpcMqProducerService.updateOnLineStatusByPid(producerDto.getProducerGroup());
			} catch (Exception e) {
				log.error("更新生产者状态为离线出现异常, ex={}", e.getMessage(), e);
			}
		}
	}

	private void initMqListener(CoordinatorRegistryCenter coordinatorRegistryCenter) throws Exception {
		CuratorFramework cf = (CuratorFramework) coordinatorRegistryCenter.getRawClient();
		initProducerListener(cf);
		initConsumerListener(cf);
	}


	private void initProducerListener(CuratorFramework cf) throws Exception {
		TreeCache treeCache = new TreeCache(cf, GlobalConstants.ZK_REGISTRY_PRODUCER_ROOT_PATH);
		treeCache.getListenable().addListener(producerChangeListener);
		treeCache.start();
	}

	private void initConsumerListener(CuratorFramework cf) throws Exception {
		TreeCache treeCache = new TreeCache(cf, GlobalConstants.ZK_REGISTRY_CONSUMER_ROOT_PATH);
		treeCache.getListenable().addListener(consumerChangeListener);
		treeCache.start();
	}

}