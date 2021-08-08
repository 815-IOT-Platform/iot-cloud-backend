package com.iot.honeyBot.model.crd.device;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import lombok.Data;

/**
 * Created by huqiaoqian on 2020/10/14
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EdgeDevice extends CustomResource implements Namespaced {
    private static final long serialVersionUID = -3636934385695603590L;
    private DeviceSpec spec;

    private DeviceStatus status;
}
