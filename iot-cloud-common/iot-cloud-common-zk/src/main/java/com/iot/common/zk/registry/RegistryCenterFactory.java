package com.iot.common.zk.registry;

import com.iot.common.config.properties.IotProperties;
import com.iot.common.config.properties.RocketMqProperties;
import com.iot.common.config.properties.ZookeeperProperties;
import com.iot.common.zk.generator.IncrementIdGenerator;
import com.iot.common.zk.registry.base.CoordinatorRegistryCenter;
import com.iot.common.zk.registry.base.ZkRegisterDto;
import com.iot.common.zk.registry.zookeeper.ZookeeperRegistryCenter;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by rongshuai on 2020/6/16 19:50
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegistryCenterFactory {

    private static final ConcurrentHashMap<HashCode, CoordinatorRegistryCenter> REG_CENTER_REGISTRY = new ConcurrentHashMap<>();

    /**
     * 创建注册中心.
     *
     * @param zkConfig the zookeeper properties
     *
     * @return 注册中心对象 coordinator registry center
     */
    public static CoordinatorRegistryCenter createCoordinatorRegistryCenter(ZookeeperProperties zkConfig) {
        Hasher hasher = Hashing.md5().newHasher().putString(zkConfig.getZkAddressList(), Charsets.UTF_8);
        HashCode hashCode = hasher.hash();
        CoordinatorRegistryCenter result = REG_CENTER_REGISTRY.get(hashCode);
        if (null != result) {
            return result;
        }
        result = new ZookeeperRegistryCenter(zkConfig);
        result.init();
        REG_CENTER_REGISTRY.put(hashCode, result);
        return result;
    }

    /**
     * Startup.
     *
     * @param iotProperties the AnanOps properties
     * @param host                the host
     * @param app                 the app
     */
    public static void startup(IotProperties iotProperties, String host, String app) {
        //初始化用于协调分布式服务的注册中心
        CoordinatorRegistryCenter coordinatorRegistryCenter = createCoordinatorRegistryCenter(iotProperties.getZk());
        ZkRegisterDto dto = new ZkRegisterDto(app, host, coordinatorRegistryCenter);
        //生成分布式ID
        Long serviceId = new IncrementIdGenerator(dto).nextId();
        IncrementIdGenerator.setServiceId(serviceId);
        //当前启动服务（生产者或消费者）注册到zookeeper中心
        registerMq(iotProperties, host, app);
    }
    //注册rocketmq生产者消费者
    private static void registerMq(IotProperties iotProperties, String host, String app) {
        CoordinatorRegistryCenter coordinatorRegistryCenter = createCoordinatorRegistryCenter(iotProperties.getZk());
        //生产者和消费者
        RocketMqProperties rocketMqProperties = iotProperties.getRocketMq();
        String consumerGroup = rocketMqProperties.isReliableMessageConsumer() ? rocketMqProperties.getConsumerGroup() : null;
        String namesrvAddr = rocketMqProperties.getNamesrvAddr();
        String producerGroup = rocketMqProperties.isReliableMessageProducer() ? rocketMqProperties.getProducerGroup() : null;
        coordinatorRegistryCenter.registerMq(app, host, producerGroup, consumerGroup, namesrvAddr);
    }
}
