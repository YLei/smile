package com.emx.platform.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emx.platform.common.ResultEnum;
import com.emx.platform.security.UserLoginService;
import com.emx.platform.service.SysAuthorityService;
import com.emx.platform.utils.Result;
import com.emx.platform.utils.ResultUtil;
import com.emx.platform.vo.JwtUser;
import com.emx.platform.vo.LoginVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// 允许跨域访问
@CrossOrigin("*")
@Api(value = "登录相关接口", tags = { "登录操作接口" })
@RestController
public class SysLoginController {
	
	@Autowired private UserLoginService userService;
	@Autowired private SysAuthorityService authService;

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/login")
	@ApiOperation(value = "用户登录", notes = "根据用户名、密码来进行用户登录")
	public Result getToken(@Valid @RequestBody LoginVo loginVo) throws AuthenticationException {
		String token = userService.login(loginVo.getUsername(), loginVo.getPassword());
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前用户
		JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> roleCodes = new ArrayList<String>();
		try {
			roleCodes = user.getAuthorities().stream().map(s -> s.getAuthority()).collect(Collectors.toList());
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			if(roleCodes !=null && !roleCodes.isEmpty()) {
				list = authService.selectMenuByRole(roleCodes, ResultEnum.TYPE_MENU.getCode());
			}
			map.put("Token", token);
			map.put("menus", list);
			map.put("username", user.getUsername());
			map.put("realname", user.getRealname());
			map.put("icon", user.getIcon());
			return ResultUtil.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error();
		}

	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/loadMenuInfo")
	@ApiOperation(value = "加载权限菜单", notes = "左侧菜单栏")
	public Result loadMenuInfo() {
		// 获取当前用户
		JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> roleCodes = new ArrayList<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			roleCodes = user.getAuthorities().stream().map(s -> s.getAuthority()).collect(Collectors.toList());
			if(roleCodes !=null && !roleCodes.isEmpty()) list = authService.selectMenuByRole(roleCodes, ResultEnum.TYPE_MENU.getCode());
			map.put("username", user.getUsername());
			map.put("realname", user.getRealname());
			map.put("icon", user.getIcon());
			map.put("menus", list);
			return ResultUtil.success(map);
		} catch (Exception e) { return ResultUtil.error(); }
	}
}
