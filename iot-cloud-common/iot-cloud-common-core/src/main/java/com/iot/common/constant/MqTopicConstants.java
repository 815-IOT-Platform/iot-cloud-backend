package com.iot.common.constant;

import com.iot.common.config.GlobalConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rongshuai on 2020/6/18 9:01
 */
public class MqTopicConstants {

    /**
     * The enum Uac mq topic enum.
     *
     * @author ananops.net @gmail.com
     */
    public enum MqTopicEnum {
        /**
         * Tpc topic mq topic enum.
         */
        TPC_CLOUD_TOPIC("TPC_CLOUD_TOPIC", "TPC_CLOUD_TOPIC"),
        /**
         * Imc topic mq topic enum.
         */
        IMC_CLOUD_TOPIC("IMC_CLOUD_TOPIC","IMC_CLOUD_TOPIC"),
        /**
         * Mdmc topic mq topic enum.
         */
        MDMC_CLOUD_TOPIC("MDMC_CLOUD_TOPIC","MDMC_CLOUD_TOPIC"),
        /**
         * Amc topic mq topic enum.
         */
        AMC_CLOUD_TOPIC("AMC_CLOUD_TOPIC","AMC_CLOUD_TOPIC"),
        ;

        MqTopicEnum(String topic, String topicName) {
            this.topic = topic;
            this.topicName = topicName;
        }

        /**
         * The Topic.
         */
        String topic;
        /**
         * The Topic name.
         */
        String topicName;

        /**
         * Gets topic.
         *
         * @return the topic
         */
        public String getTopic() {
            return topic;
        }
    }

    /**
     * The enum Uac mq tag enum.
     *
     * @author ananops.net @gmail.com
     */
    public enum MqTagEnum {
        /**
         * 巡检mq测试
         */
        IMC_MQ_TEST("IMC_MQ_TEST",MqTopicEnum.IMC_CLOUD_TOPIC.getTopic(),"巡检mq测试"),
        /**
         * 巡检任务状态改变
         */
        IMC_TASK_STATUS_CHANGED("IMC_TASK_STATUS_CHANGED",MqTopicEnum.IMC_CLOUD_TOPIC.getTopic(),"巡检任务状态改变"),
         /**
         * 巡检任务子项状态改变
         */
        IMC_ITEM_STATUS_CHANGED("IMC_ITEM_STATUS_CHANGED",MqTopicEnum.IMC_CLOUD_TOPIC.getTopic(),"巡检任务子项状态改变"),
        /**
         * 维修工单状态改变
         */
        MDMC_TASK_STATUS_CHANGED("MDMC_TASK_STATUS_CHANGED",MqTopicEnum.MDMC_CLOUD_TOPIC.getTopic(),"维修工单状态改变"),
        /**
         * 发生报警
         */
        AMC_ALARM_OCCUR("AMC_ALARM_OCCUR",MqTopicEnum.AMC_CLOUD_TOPIC.getTopic(),"发生报警"),
        ;
        /**
         * The Tag.
         */
        String tag;
        /**
         * The Topic.
         */
        String topic;
        /**
         * The Tag name.
         */
        String tagName;

        MqTagEnum(String tag, String topic, String tagName) {
            this.tag = tag;
            this.topic = topic;
            this.tagName = tagName;
        }

        /**
         * Gets tag.
         *
         * @return the tag
         */
        public String getTag() {
            return tag;
        }

        /**
         * Gets topic.
         *
         * @return the topic
         */
        public String getTopic() {
            return topic;
        }
    }

    /**
     * The class Consumer topics.
     *
     * @author ananops.net @gmail.com
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ConsumerTopics {

        public static final String WEBSOCKET = buildWebSocketConsumerTopics();

    }

    private static String buildWebSocketConsumerTopics(){
        List<TopicObj> topicObjList = new ArrayList<>();

        //IMC
        Set<String> imcTagList = new HashSet<>();
        imcTagList.add(MqTagEnum.IMC_MQ_TEST.getTag());
        //MDMC
        Set<String> mdmcTagList=new HashSet<>();
        mdmcTagList.add(MqTagEnum.MDMC_TASK_STATUS_CHANGED.getTag());
        //AMC
        Set<String> amcTagList=new HashSet<>();
        amcTagList.add(MqTagEnum.AMC_ALARM_OCCUR.getTag());

        topicObjList.add(new TopicObj(MqTopicEnum.MDMC_CLOUD_TOPIC.getTopic(),mdmcTagList));

        topicObjList.add(new TopicObj(MqTopicEnum.IMC_CLOUD_TOPIC.getTopic(),imcTagList));

        topicObjList.add(new TopicObj(MqTopicEnum.AMC_CLOUD_TOPIC.getTopic(),amcTagList));

        return buildOpcConsumerTopics(topicObjList);
    }

    private static String buildOpcConsumerTopics(List<TopicObj> topicList) {

        StringBuilder result = new StringBuilder();

        if (!CollectionUtils.isEmpty(topicList)) {
            for (TopicObj topicObj : topicList) {
                String topic = topicObj.getTopic();
                Set<String> tagList = topicObj.getTagList();

                if (StringUtils.isEmpty(topic) || CollectionUtils.isEmpty(topicList)) {
                    continue;
                }

                StringBuilder tagInfo = new StringBuilder();
                for (String tag : tagList) {
                    tagInfo.append(tag).append(GlobalConstants.Symbol.PIPE);
                }
                trimEnd(tagInfo, GlobalConstants.Symbol.PIPE);
                result.append(topic).append(GlobalConstants.Symbol.AT).append(tagInfo).append(GlobalConstants.Symbol.COMMA);
            }
        }
        trimEnd(result, GlobalConstants.Symbol.COMMA);
        return result.toString();

    }

    /**
     * The class Topic obj.
     *
     * @author ananops.net @gmail.com
     */
    static class TopicObj {

        private String topic;
        private Set<String> tagList;

        /**
         * Instantiates a new Topic obj.
         *
         * @param topic   the topic
         * @param tagList the tag list
         */
        TopicObj(String topic, Set<String> tagList) {
            this.topic = topic;
            this.tagList = tagList;
        }

        /**
         * Gets topic.
         *
         * @return the topic
         */
        String getTopic() {
            return topic;
        }

        /**
         * Gets tag list.
         *
         * @return the tag list
         */
        Set<String> getTagList() {
            return tagList;
        }
    }

    private static void trimEnd(StringBuilder stringBuilder, String suffix) {
        if (null == stringBuilder) {
            return;
        }
        String str = stringBuilder.toString();
        if (!StringUtils.isEmpty(suffix) && !str.endsWith(suffix)) {
            return;
        }
        stringBuilder.delete(str.length() - suffix.length(), str.length());
    }
}
