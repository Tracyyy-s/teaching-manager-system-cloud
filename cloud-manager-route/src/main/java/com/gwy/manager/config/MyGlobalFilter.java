package com.gwy.manager.config;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.util.JwtTokenUtils;
import com.gwy.manager.util.ResultVOUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyGlobalFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(MyGlobalFilter.class);

    private static final String LOGIN_REQUEST = "/login";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 获取请求上下文
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();

        //获取header中的验证信息
        String token = request.getHeader(JwtTokenUtils.TOKEN_HEADER);

        // 响应类型
        rc.getResponse().setContentType("application/json; charset=utf-8");

        if (request.getServletPath().equals(LOGIN_REQUEST)) {
            return null;
        }

        // 业务逻辑处理
        if (null == token) {
            logger.warn("无token");

            // 请求结束，不在继续向下请求。
            rc.setSendZuulResponse(false);

            // 响应状态码，HTTP 401 错误代表用户没有访问权限
            rc.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());

            try (PrintWriter writer = rc.getResponse().getWriter()) {
                // 响应内容
                writer.write(JSONObject.toJSONString(ResultVOUtil.error("No Token")));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            // 使用 token 进行身份验证
            logger.info("有token");

            // Token不合法
            if (isInvalidToken(token)) {
                // 请求结束，不在继续向下请求。
                rc.setSendZuulResponse(false);

                // 响应状态码，HTTP 401 错误代表用户没有访问权限
                rc.setResponseStatusCode(HttpStatus.FORBIDDEN.value());

                try {
                    rc.getResponse().getWriter().write(JSONObject.toJSONString(ResultVOUtil.error("Error Token")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    /**
     * 判断Token是否合法
     * @param token token
     * @return  是否合法
     */
    private boolean isInvalidToken(String token) {
        //从token中获取用户信息，jwtUtils自定义的token加解密方式
        String username = JwtTokenUtils.getUsername(token);

        return username.equals(JwtTokenUtils.ERROR_TOKEN);
    }

}
