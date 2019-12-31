package com.emx.platform.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.emx.platform.common.ResultEnum;
import com.emx.platform.utils.Result;
import com.emx.platform.utils.ResultUtil;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint,Serializable {
	
	private static final long serialVersionUID = 6944133544136282322L;

	@SuppressWarnings("rawtypes")
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        Result result = null;
        //身份认证未通过
        if(authException instanceof BadCredentialsException){ 
        	result = ResultUtil.error(ResultEnum.USER_ERROR.getCode(), authException.getMessage());
        }else{
        	result = ResultUtil.error(ResultEnum.TOKEN_ERROR.getCode(), ResultEnum.TOKEN_ERROR.getMsg());
        }
        response.getWriter().write(JSONObject.toJSONString(result));
    }
}
