package io.promagent.log.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @Author:zhangyi
 * @Date:2019/9/25
 */
public class LogConfig {
    public static String TRACE_ID = System.getProperty("agent.traceId");
    public static String IP = System.getProperty("agent.ip");
    public static int MAX_MSG = Integer.parseInt(System.getProperty("agent.maxMsg"));
    public static String appName = System.getProperty("agent.appName");
    public static String appEvn = System.getProperty("agent.appEvn");
    public static List<String> headers = JSONArray.parseArray(System.getProperty("agent.headers"), String.class);
    public static List<String> ignoreSignatures = JSONArray.parseArray(System.getProperty("agent.ignoreSignatures"), String.class);

    public static Map<String, String> type = new HashMap<>();
    public static Map<String, String> annClassType = JSON.parseObject(System.getProperty("agent.hooks.annClassType"), Map.class);
    public static Map<String, String> annMethodType = JSON.parseObject(System.getProperty("agent.hooks.annMethodType"), Map.class);
    public static Map<String, String> regType = JSON.parseObject(System.getProperty("agent.hooks.regType"), Map.class);

}
