package com.emx.platform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emx.platform.common.ResultEnum;
import com.emx.platform.common.TableContext;
import com.emx.platform.dao.SysRoleMapper;
import com.emx.platform.dao.SysUserRoleMapper;
import com.emx.platform.domain.SysRole;
import com.emx.platform.domain.SysUser;
import com.emx.platform.domain.SysUserRole;
import com.emx.platform.params.ParamId;
import com.emx.platform.params.UserListParam;
import com.emx.platform.service.SysAttachmentService;
import com.emx.platform.service.SysUserService;
import com.emx.platform.utils.EncryptCode;
import com.emx.platform.utils.Result;
import com.emx.platform.utils.ResultUtil;
import com.emx.platform.utils.StringUtil;
import com.emx.platform.vo.AttachmentEntityVo;
import com.emx.platform.vo.JwtUser;
import com.emx.platform.vo.UserListVo;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


// 允许跨域访问
@CrossOrigin("*")
@RestController
@RequestMapping("/user")
@SuppressWarnings("unchecked")
@Api(value="用户接口相关",tags={"用户接口相关"})
public class SysUserController {
	
	@Autowired private SysUserService userService;
	@Autowired private SysUserRoleMapper userRoleMapper;
	@Autowired private SysRoleMapper roleMapper;

	@Autowired private SysAttachmentService attachmentService;

	@ApiOperation(value = "存一个user")
	@PostMapping(value = "/save")
	public Result<SysUser> save(@RequestBody SysUser user) {
		String pwd = "********";
		try {
			int count = 0;
			JwtUser u=(JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (StringUtil.isEmpty(user.getId())) {
				SysUser user2 = userService.selectByUsername(user.getUsername());
				if (StringUtil.isNotEmpty(user2)) {
				 return ResultUtil.error(ResultEnum.USER_IS_EXISTS.getCode(), ResultEnum.USER_IS_EXISTS.getMsg());
				}else{
					user.setCreator(u.getId());
					user.setOperator(u.getId());
					if(!user.getPassword().isEmpty() && !pwd.equals(user.getPassword())){
						user.setPassword(EncryptCode.encryptMD5String(user.getPassword()));
					}else{
						user.setPassword(null);
					}
					count = userService.save(user,u.getId());
					
				}
			}else{
				SysUser user1  = userService.selectByPrimaryKey(user.getId());
				if (StringUtil.isNotEmpty(user1)) {
					if (user.getUsername().equals(user1.getUsername())) {
						user.setOperator(u.getId());
						if(!user.getPassword().isEmpty() && !pwd.equals(user.getPassword())){
							user.setPassword(EncryptCode.encryptMD5String(user.getPassword()));
						}else{
							user.setPassword(null);
						}
						count = userService.update(user,u.getId());
					}else{
						SysUser user2 = userService.selectByUsername(user.getUsername());
						if (StringUtil.isNotEmpty(user2)) {
							return ResultUtil.error(ResultEnum.USER_IS_EXISTS.getCode(), ResultEnum.USER_IS_EXISTS.getMsg());
						}else{
							user.setOperator(u.getId());
							if(!user.getPassword().isEmpty() && !pwd.equals(user.getPassword())){
								user.setPassword(EncryptCode.encryptMD5String(user.getPassword()));
							}else{
								user.setPassword(null);
							}
							count = userService.update(user,u.getId());
						}
					}
				}else {
					return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
				}
			}
			if (count>0) {
				return ResultUtil.success();
			}else{
				return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
	}

	@ApiOperation(value = "通过uid判断是新增还是修改用户")
	@PostMapping(value = "/edit")
	public Result<SysUser> edit(@RequestBody ParamId uid) {
		try {
			if (StringUtil.isNotEmpty(uid.getIdString())) {
				SysUser user = userService.selectByPrimaryKey(uid.getIdString());
				if (StringUtil.isNotEmpty(user)) {
					List<SysUserRole> listroleId = userRoleMapper.selectRoleIdByUserId(uid.getIdString());
					user.setListUserRole(listroleId);
					user.setPassword("********");
				}
				List<AttachmentEntityVo> attachmentEntityDtos = attachmentService.selectByContractId(uid.getIdString());
				if (StringUtil.isNotEmpty(attachmentEntityDtos)&&attachmentEntityDtos.size()>0) {
					user.setOldFileName(attachmentEntityDtos.get(0).getOldFileName());
				}
				List<SysRole> listRole = roleMapper.selectListByRole(null);
				Map<Object, Object> map = new HashMap<>();
				map.put("listRole", listRole);
				map.put("user", user);
				return ResultUtil.success(map);				
			}else{
				List<SysRole> listRole = roleMapper.selectListByRole(null);
				Map<Object, Object> map = new HashMap<>();
				map.put("listRole", listRole);
				return ResultUtil.success(map);	
			}			
		} catch (Exception e) { System.out.println(e.getMessage()); }
		return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
	}

	@ApiOperation(value = "启用/停用")
	@PostMapping(value = "/updateStat")
	public Result<SysUser> updateStat(@RequestBody ParamId uid) {
		JwtUser u=(JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SysUser user = userService.selectByPrimaryKey(uid.getIdString());
		user.setOperator(u.getId());
		int count = 0;
		if (ResultEnum.STATUS_NORMAL.getMsg().equals(user.getStat())) {
			user.setStat("停用");	
			count = userService.update(user,null);
		}else if (ResultEnum.STATUS_CANCEL.getCode().equals(user.getStat())) {
			user.setStat("启用");
			count = userService.update(user,null);
		}
		if (count>0) {
			return ResultUtil.success();
		}else{
			return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
		}
	}
	@ApiOperation(value = "根据id进行修改状态")
	@PostMapping(value = "/delete")
	public Result<SysUser> delete(@RequestBody ParamId uid){
		try {
			JwtUser u=(JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(u!=null){
				SysUser user = userService.selectByPrimaryKey(uid.getIdString());
				if(user != null){
					int iline = userService.removeByPrimaryKey(uid.getIdString());
					if(iline > 0 ){
						return ResultUtil.success();
					}else{
						return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
					}
				}else{
					return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
				}
			}else{
				return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
	}

	@ApiOperation(value = "查询listUser")
	@PostMapping(value = "/list")
	public Result<SysUser> list(@Valid @RequestBody UserListParam userListParam) {
		try {
			if (userListParam.getRow() ==null) { userListParam.setRow(1); }
			if (userListParam.getPagesize()==null) { userListParam.setPagesize(10); }
			SysUser user = new SysUser();
			user.setRealname(userListParam.getRealName());
			PageInfo<UserListVo> userPage  = userService.selectByUser(user,userListParam.getRow(),userListParam.getPagesize());
			Map<Object, Object> map = new HashMap<>();
			map.put("userPage", userPage);
			return ResultUtil.success(map);
		} catch (Exception e) { System.out.println(e.getMessage()); }
		return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
		
	}
}
