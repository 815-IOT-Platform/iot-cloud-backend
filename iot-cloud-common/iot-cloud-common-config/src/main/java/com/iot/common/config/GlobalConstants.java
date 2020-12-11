package com.iot.common.config;

/**
 * Created by rongshuai on 2020/6/17 8:52
 */
public class GlobalConstants {

    public static final String ROOT_PREFIX = "iot-cloud";

    public static final String ZK_REGISTRY_ID_ROOT_PATH = "/iot-cloud/registry/id";
    public static final String ZK_REGISTRY_PRODUCER_ROOT_PATH = "/iot-cloud/registry/producer";
    public static final String ZK_REGISTRY_CONSUMER_ROOT_PATH = "/iot-cloud/registry/consumer";
    public static final String ZK_REGISTRY_SEQ = "/iot-cloud/seq";

    /**
     * The class Symbol.
     *
     * @author ananops.net@gmail.com
     */
    public static final class Symbol {
        private Symbol() {
        }

        /**
         * The constant COMMA.
         */
        public static final String COMMA = ",";
        public static final String SPOT = ".";
        /**
         * The constant UNDER_LINE.
         */
        public final static String UNDER_LINE = "_";
        /**
         * The constant PER_CENT.
         */
        public final static String PER_CENT = "%";
        /**
         * The constant AT.
         */
        public final static String AT = "@";
        /**
         * The constant PIPE.
         */
        public final static String PIPE = "||";
        public final static String SHORT_LINE = "-";
        public final static String SPACE = " ";
        public static final String SLASH = "/";
        public static final String MH = ":";

    }

    public interface Number {
        int THOUSAND_INT = 1000;
        int HUNDRED_INT = 100;
        int ONE_INT = 1;
        int TWO_INT = 2;
        int THREE_INT = 3;
        int FOUR_INT = 4;
        int FIVE_INT = 5;
        int SIX_INT = 6;
        int SEVEN_INT = 7;
        int EIGHT_INT = 8;
        int NINE_INT = 9;
        int TEN_INT = 10;
        int EIGHTEEN_INT = 18;
    }
}
