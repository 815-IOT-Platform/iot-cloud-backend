package com.iot.honeyBot.model.crd.modelType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModelType implements Serializable {
    private static final long serialVersionUID = 2381654044014006304L;
    @JsonProperty("int")
    private IntType intType;

    @JsonProperty("string")
    private StringType stringType;

    @JsonProperty("double")
    private DoubleType doubleType;
}
