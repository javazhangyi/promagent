package io.promagent.agent.core.utils;

import io.promagent.agent.core.config.LogConfig;
import io.promagent.agent.core.config.LogConstants;
import org.slf4j.MDC;

import java.util.UUID;


public class MdcUtils {

    public static void setLogId(String val) {
        val = StringUtils.isEmpty(val) ? UUID.randomUUID().toString() : val;
        MDC.put(LogConstants.mdcLogId, val);
//        MDC.put(LogConstants.mdc_appName, LogConfig.appName);
//        MDC.put(LogConstants.mdc_appEvn, LogConfig.appEvn);
    }

    public static String getLogId() {
        String logId = MDC.get(LogConstants.mdcLogId);
        if (StringUtils.isEmpty(logId)) {
            logId = UUID.randomUUID().toString();
            MDC.put(LogConstants.mdcLogId, logId);
            return logId;
        }
        return logId;
    }
}
