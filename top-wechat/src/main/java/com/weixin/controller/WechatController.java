package com.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.weixin.util.JsonUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guishuangfeng on 2016/5/13.
 */
@Controller
public class WechatController  {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @RequestMapping(value = "/init", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String init(@RequestParam(value="code")String code,HttpServletResponse res,HttpServletRequest req) {
        req.setAttribute("code",code);
        String appid = "wxb1df005da57f3087";
        String secret="daaa9d74da2b65c6727d4daa9cb80cbc";
        String tokenUrl = " https://api.weixin.qq.com/sns/oauth2/access_token?appid="+
               appid+"&secret="+
               secret+"&code="+
               code+"&grant_type=authorization_code";
        HttpClient httpclient = new HttpClient();
        GetMethod getMethod = new GetMethod(tokenUrl);
        getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");//设置客户端请求字符集！
//        getMethod.getParams().setParameter("http.protocol.content-charset","utf-8");//设置客户端请求字符集！
        try {
            logger.debug("调用url:"+tokenUrl);
            logger.debug("调用结果状态:"+httpclient.executeMethod(getMethod));
            InputStream is = getMethod.getResponseBodyAsStream();
            String resp = IOUtils.toString(is, "UTF-8");
            logger.debug("调用返回结果"+resp);
            if (getMethod.getStatusCode() == 200) {
                if (resp != null) {
                    /*
                    返回例子:
                    { "access_token":"ACCESS_TOKEN",
                         "expires_in":7200,
                         "refresh_token":"REFRESH_TOKEN",
                         "openid":"OPENID",
                         "scope":"SCOPE" }
                     */

                    Map map = JsonUtils.toBean(resp,HashMap.class);
                    String access_token = (String)map.get("access_token");
                    String openid = (String)map.get("openid");
                    req.setAttribute("access_token",access_token);
                    req.setAttribute("openid", openid);

                    String userUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="
                                    +access_token+"&openid="+
                                    openid+"&lang=zh_CN ";
                    getMethod = new GetMethod(userUrl);
                    logger.debug("调用url:"+tokenUrl);
                    logger.debug("调用结果状态:"+httpclient.executeMethod(getMethod));
                    InputStream isu = getMethod.getResponseBodyAsStream();
                    String result = IOUtils.toString(isu, "UTF-8");
                    logger.debug("调用返回结果"+result);
                    req.setAttribute("user_resp",result);
                }
            }
            else{
               logger.error("接口调用异常");
            }
        } catch (Exception e) {
            logger.error("微信接口调用异常:",e);
        }

        return "init";
    }

    @RequestMapping(value = "/test/init", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String initTest(@RequestParam(value="code")String code,HttpServletResponse res,HttpServletRequest req) {
        req.setAttribute("code",code);
        req.setAttribute("access_token","test_token");
        req.setAttribute("openid","test_openid");
        req.setAttribute("user_resp","test_user_resp");

        return "init";
    }
}
