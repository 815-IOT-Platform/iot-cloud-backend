package com.iot.honeyBot.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

@Component
public class K8sutil {
    private static final Logger logger = LoggerFactory.getLogger(K8sutil.class);

    public static Map PotTemplate;

    static {
        try {
            DumperOptions dumperOptions = new DumperOptions();
            dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            dumperOptions.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);
            dumperOptions.setPrettyFlow(false);
            Yaml yaml = new Yaml(dumperOptions);
            Resource resource = new ClassPathResource("honeypot.yaml");
            InputStream is = resource.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            PotTemplate = yaml.loadAs(isr, Map.class);
            String jsonStr = JSONObject.toJSONString(PotTemplate);
            logger.info("pot template is {}", jsonStr);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
