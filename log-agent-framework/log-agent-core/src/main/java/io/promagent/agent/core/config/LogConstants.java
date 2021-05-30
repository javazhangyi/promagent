package io.promagent.agent.core.config;

public interface LogConstants {

//    String request = "request";
    String reqUrl = "reqUrl";
    String reqHeaders = "reqHeaders";
    String reqParams = "reqParams";
    String reqStatus = "reqStatus";
//    String method = "method";
    String metArgs = "metArgs";
    String metRet = "metRet";
    String metSign = "metSign";
    String metExec = "metExec";
    String metThrown = "metThrown";

//    String basic = "basic";
    String mdcSn = "mdcSn";
    String mdcGrade = "mdcGrade";
    String mdcType = "mdcType";

//    basic_app
//    basic_evn
//    basic_logId
//    basic_date

//    String mdc = "mdc";
    String mdcLogId = System.getProperty("agent.mdcLogId");
//    String mdc_appName = "appName";
//    String mdc_appEvn = "appEvn";
//    String mdc_type = "type";

    String null_string = "NULL";
    String skip = "skip";

    String PreviousSignature = "PreviousSignature";
    String LogPrinted = "LogPrinted";
    String RequestTimeStamp = "RequestTimeStamp";

    String HandlerInterceptorSign = "HandlerInterceptor.preHandle";
    String FilterSign = "Filter.doFilter";
    String HttpServletSign = "HttpServlet.service";
    String LoggerErrSign = "Logger.error";
    String LoggerInfoSign = "Logger.info";
}
