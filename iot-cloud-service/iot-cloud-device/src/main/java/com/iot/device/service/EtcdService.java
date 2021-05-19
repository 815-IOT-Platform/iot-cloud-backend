package com.iot.device.service;
import io.etcd.jetcd.*;

import java.util.List;

public interface EtcdService {
    public List<KeyValue> getEtcdKV(String key);

    public List<KeyValue> getEtcdKV(String key, String prefix);

    public void putEtcdKV(String key, String value);
}
