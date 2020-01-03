package com.emx.platform.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.emx.platform.common.ResultEnum;
import com.emx.platform.utils.Result;
import com.emx.platform.utils.ResultUtil;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
	    @SuppressWarnings("rawtypes")
		Result result = ResultUtil.error(ResultEnum.PERMISSION_DENIED.getCode(), ResultEnum.PERMISSION_DENIED.getMsg());
        response.getWriter().write(JSONObject.toJSONString(result));
	}

}
