package com.emx.platform.utils;

import org.springframework.stereotype.Component;

import com.emx.platform.common.ResultEnum;

@SuppressWarnings("rawtypes")
@Component
public class ResultUtil {

	public static <T>Result success(T object) {
		return new Result<T>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),object);
    }

	public static <T>Result success(Integer code,String Msg) {
		return new Result<T>(code,Msg);
    }

	public static <T>Result success(){
		return new Result<T>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg());
    }

	public static <T>Result success(String Msg,T object) {
		return new Result<T>(ResultEnum.SUCCESS.getCode(), Msg, object);
    }

    public static <T>Result error(Integer code,String msg) {
        return new Result<T>(code,msg);
    }

    public static <T>Result error() {
        return new Result<T>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
    }
}
