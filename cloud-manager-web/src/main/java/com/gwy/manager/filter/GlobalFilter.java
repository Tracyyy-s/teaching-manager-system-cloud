package com.gwy.manager.filter;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.request.WebHttpServletRequestWrapper;
import com.gwy.manager.util.JwtTokenUtils;
import com.gwy.manager.util.ResultVOUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tracy
 * @date 2021/1/18 1:29
 */
@Component
public class GlobalFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(JwtTokenUtils.TOKEN_HEADER);

        if (header == null) {
            response.getWriter().write(JSONObject.toJSONString(ResultVOUtil.error("No Token")));
            return;
        }

        final String token = header.substring(JwtTokenUtils.TOKEN_PREFIX.length());

        String username = getUsernameByToken(token);

        if (username.equals(JwtTokenUtils.ERROR_TOKEN)) {
            response.getWriter().write(JSONObject.toJSONString(ResultVOUtil.error("Error Token")));
            return;
        }

        WebHttpServletRequestWrapper requestWrapper = new WebHttpServletRequestWrapper(request, username);

        filterChain.doFilter(requestWrapper, response);
    }

    /**
     * 根据token获得username
     *
     * @param token token
     * @return  结果集
     */
    private String getUsernameByToken(String token) {
        return JwtTokenUtils.getUsername(token);
    }
}
