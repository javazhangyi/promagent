package io.promagent.agent.core.internal;

import com.alibaba.fastjson.JSONObject;


import io.promagent.agent.core.config.GradeConstants;
import io.promagent.agent.core.config.LogConfig;
import io.promagent.agent.core.config.LogConstants;
import io.promagent.agent.core.config.TypeConstants;
import io.promagent.agent.core.utils.LogObjectUtils;
import io.promagent.agent.core.utils.MdcUtils;
import io.promagent.agent.core.utils.StringUtils;
import io.promagent.agent.core.utils.ThrowableUtils;
import lombok.Getter;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogObject {

    private int sn = 0;
    private String type;
    private String grade;
    @Getter
    private Map<String, String> request = new ConcurrentHashMap<>();
    private Map<String, String> method = new ConcurrentHashMap<>();

    @Getter
    private JSONObject tempData = new JSONObject();
    private String beforeError;

    protected LogObject setMethod(Long exec, Throwable error, Object ret, String sign, Object[] args, String type, String grade) {

        this.type = StringUtils.isEmpty(type) ? TypeConstants.DEFAULT : type;
        this.grade = StringUtils.isEmpty(grade) ? GradeConstants.DEFAULT : grade;

        if (StringUtils.isEmpty(exec)) {
            method.remove(LogConstants.metExec);
        } else {
            method.put(LogConstants.metExec, String.valueOf(exec));
        }
        if (StringUtils.isEmpty(sign)) {
            method.remove(LogConstants.metSign);
        } else {
            method.put(LogConstants.metSign, sign);
        }
        if (StringUtils.isEmpty(args) || args.length == 0) {
            method.remove(LogConstants.metArgs);
        } else {
            method.put(LogConstants.metArgs, LogObjectUtils.getArgs(args));
        }
        if (StringUtils.isEmpty(ret)) {
            method.remove(LogConstants.metRet);
        } else {
            String metRet = LogConfig.skipRetSignatures.contains(sign) ? LogConstants.skip : LogObjectUtils.getReturn(ret);
            method.put(LogConstants.metRet, metRet);
        }

        if (StringUtils.isEmpty(error)) {
            method.remove(LogConstants.metThrown);
        } else {
            // 避免对同一个异常多次打印
            if (StringUtils.isEmpty(beforeError) || !error.getMessage().contains(beforeError)) {
                this.grade = GradeConstants.EXCEPTION;
                method.put(LogConstants.metThrown, ThrowableUtils.getStackTrace(error));
                beforeError = error.getMessage();
                tempData.put(LogConstants.LogPrinted, true);
            } else {
                method.put(LogConstants.metThrown, LogConstants.LogPrinted);
                tempData.remove(LogConstants.LogPrinted);
            }
        }
        return this;
    }

    protected String getLogJson() {
        MDC.put(LogConstants.mdcSn, String.valueOf(++sn));
        MDC.put(LogConstants.mdcGrade, grade);
        MDC.put(LogConstants.mdcType, type);
        MDC.put(LogConstants.mdcLogId, MdcUtils.getLogId());

        MDC.put(LogConstants.metArgs, method.get(LogConstants.metArgs));
        MDC.put(LogConstants.metRet, method.get(LogConstants.metRet));
        MDC.put(LogConstants.metExec, method.get(LogConstants.metExec));
        MDC.put(LogConstants.metThrown, method.get(LogConstants.metThrown));

        MDC.put(LogConstants.reqUrl, request.get(LogConstants.reqUrl));
        MDC.put(LogConstants.reqHeaders, request.get(LogConstants.reqHeaders));
        MDC.put(LogConstants.reqParams, request.get(LogConstants.reqParams));
        MDC.put(LogConstants.reqStatus, request.get(LogConstants.reqStatus));
        return "agentJsonLog";
    }
}
