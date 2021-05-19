package com.iot.device.service.impl;

import java.util.List;
import com.google.common.base.Charsets;
import com.iot.common.exception.BusinessException;
import com.iot.device.service.EtcdService;
import io.etcd.jetcd.*;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.options.GetOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EtcdServiceImpl implements EtcdService {

    @Autowired
    private Client etcdClient;

    @Override
    public List<KeyValue> getEtcdKV(String key) {
        try {
            GetResponse getResponse = etcdClient.getKVClient()
                    .get(ByteSequence.from(key, Charsets.UTF_8)).get();
            if (getResponse.getKvs().isEmpty()) {
                throw new BusinessException("key doesn't exist");
            }
            return getResponse.getKvs();
        } catch (Exception e) {
            throw new BusinessException("get etcd kv error, error is %s",e.getCause());
        }
    }

    @Override
    public List<KeyValue> getEtcdKV(String key, String prefix) {
        try {
            GetResponse getResponse = etcdClient.getKVClient()
                    .get(ByteSequence.from(key, Charsets.UTF_8),
                            GetOption.newBuilder().withPrefix(ByteSequence.from(prefix,Charsets.UTF_8)).build())
                    .get();
            if (getResponse.getKvs().isEmpty()) {
                throw new BusinessException("key doesn't exist");
            }
            return getResponse.getKvs();
        } catch (Exception e) {
            throw new BusinessException("get etcd kv error, error is %s",e.getCause());
        }
    }

    @Override
    public void putEtcdKV(String key, String value) {
        try {
            etcdClient.getKVClient().put(ByteSequence.from(key, Charsets.UTF_8), ByteSequence.from(value, Charsets.UTF_8)).get();
            log.info("put etcd kv succeed");
        } catch (Exception e) {
            throw new BusinessException("put etcd kv error, error is %v", e.getCause());
        }
    }

}