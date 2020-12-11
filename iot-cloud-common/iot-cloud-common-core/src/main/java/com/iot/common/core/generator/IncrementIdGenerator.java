//package com.ananops.common.core.generator;
//
//import com.ananops.common.constant.GlobalConstants;
//import com.ananops.common.core.dto.ZkRegisterDto;
//import com.ananops.common.redis.util.RedisUtils;
//import com.ananops.common.utils.spring.SpringUtils;
//
//
///**
// * Created by rongshuai on 2020/6/4 10:45
// */
//public class IncrementIdGenerator implements IdGenerator {
//    private static Long serviceId = null;
//    private final ZkRegisterDto registerDto;
//    //使用SpringUtils工具类，为普通类注入Redis的Bean
//    private final RedisUtils redis = SpringUtils.getBean(RedisUtils.class);
//
//    /**
//     * 实例化一个新的id自增生成器
//     * @param registerDto
//     */
//    public IncrementIdGenerator(ZkRegisterDto registerDto){
//        this.registerDto = registerDto;
//    }
//
//    @Override
//    public Long nextId(){
//        String app = this.registerDto.getApp();
//        String host = this.registerDto.getHost();
//        String key = GlobalConstants.REDIS_REGISTRY_ID_ROOT_PATH + GlobalConstants.UNDERLINE + app + GlobalConstants.UNDERLINE + host;
//        System.out.println(key);
//        String serviceId = redis.get(key);
//        if (null != serviceId && serviceId.length() > 0) {
//            //如果当前host上部署的该app存在id，则直接返回该id
//            return Long.valueOf(serviceId);
//        } else {
//            //如果当前host没有该app对应的id，则重新生成一个
//            Long id = createServiceId(app,host);
//            redis.set(key,String.valueOf(id));
//            return id;
//        }
//    }
//
//    /**
//     * 创建新的serviceid
//     * @param app
//     * @param host
//     * @return
//     */
//    private Long createServiceId(String app,String host) {
//        long serviceId = (long) (Math.random() * 8191);
//        String key = GlobalConstants.REDIS_SERVICEID + GlobalConstants.UNDERLINE + serviceId;
//        String value = GlobalConstants.REDIS_REGISTRY_ID_ROOT_PATH + GlobalConstants.UNDERLINE + app + GlobalConstants.UNDERLINE + host;
//        if (redis.setnx(key,value)) {
//            //如果serviceid可用
//            return serviceId;
//        } else {
//            //如果serviceid不可用，则递归生成新的serviceid
//            return createServiceId(app,host);
//        }
//    }
//
//    public static Long getServiceId() {
//        return serviceId;
//    }
//
//    public static void setServiceId(Long serviceId){
//        IncrementIdGenerator.serviceId = serviceId;
//    }
//}
