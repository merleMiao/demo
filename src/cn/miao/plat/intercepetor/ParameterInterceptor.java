package cn.miao.plat.intercepetor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Created by Miaosk
 * @date Created on 2015/5/19.
 */
public class ParameterInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("contextPath", request.getContextPath());
        return true;
    }

}
