package com.emx.platform.controller;

import java.util.Date;
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
import com.emx.platform.dao.SysAuthorityMapper;
import com.emx.platform.domain.SysAuthority;
import com.emx.platform.domain.SysRole;
import com.emx.platform.params.ParamId;
import com.emx.platform.params.RoleListParam;
import com.emx.platform.service.SysAuthorityService;
import com.emx.platform.service.SysRoleService;
import com.emx.platform.utils.Result;
import com.emx.platform.utils.ResultUtil;
import com.emx.platform.utils.StringUtil;
import com.emx.platform.vo.AuthorityListVo;
import com.emx.platform.vo.JwtUser;
import com.emx.platform.vo.RoleListVo;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/role")
@Api(value="角色接口相关",tags={"角色接口相关"})
public class SysRoleController {
	
	@Autowired private SysRoleService roleService;
	@Autowired private SysAuthorityMapper authorityMapper;
	@Autowired private SysAuthorityService authorityService;

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "存入一个role对象")
	@PostMapping(value = "/save")
	public Result<SysRole> save(@RequestBody SysRole role) {
		try {
			JwtUser u=(JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			int count = 0;
			SysRole role2 = roleService.selectByRoleName(role.getName(), role.getId());
			if (StringUtil.isNotEmpty(role2)) {
				return ResultUtil.error(ResultEnum.SYS_ROLENAME_EXIST.getCode(), ResultEnum.SYS_ROLENAME_EXIST.getMsg());
			}else{
				if (StringUtil.isEmpty(role.getId())) {
					role.setCreator(u.getId());
					role.setOperator(u.getId());
					count = roleService.save(role);
				}else{
					SysRole role1 = roleService.selectByPrimaryKey(role.getId());
					if (StringUtil.isNotEmpty(role1)) {
						role1.setOperator(u.getId());
						count = roleService.update(role);
					}
				}
				if (count>0) {
					return ResultUtil.success();
				}else{
					return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "根据id进行判断是新增还是修改")
	@PostMapping(value = "/edit")
	public Result<SysRole> edit(@RequestBody ParamId roleid) {
		try {
			List<AuthorityListVo> listAuthority = authorityService.selectByAuthorityPid(null);
			for (AuthorityListVo authorityListDto : listAuthority) {
				List<AuthorityListVo> authorityListDtoHasChild = authorityService.selectByAuthorityPid(authorityListDto.getAuthId());
				if (StringUtil.isNotEmpty(authorityListDtoHasChild)&&authorityListDtoHasChild.size()>0) {
					authorityListDto.setHasChild(1);
				} else { authorityListDto.setHasChild(0); }
			}
			if (StringUtil.isNotEmpty(roleid.getIdInteger())) {
				SysRole role = roleService.selectByPrimaryKey(roleid.getIdInteger());
				List<SysAuthority> listRoleAuthority = authorityMapper.selectAuthByRoleId(roleid.getIdInteger());
				role.setListRoleAuthority(listRoleAuthority);
				Map<Object, Object> map = new HashMap<>();
				map.put("role", role);
				map.put("listAuthority", listAuthority);
				return ResultUtil.success(map);
			} else {
				Map<Object, Object> map = new HashMap<>();
				map.put("listAuthority", listAuthority);
				return ResultUtil.success(map);
			}
		} catch (Exception e) { System.out.println(e.getMessage()); }
		return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "根据id进行删除状态")
	@PostMapping(value = "/delete")
	public Result<SysRole> delete(@RequestBody ParamId roleid) {
		try {
			JwtUser u=(JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(u != null){
				SysRole role = roleService.selectByPrimaryKey(roleid.getIdInteger());
				if(role != null){
					int iline = roleService.removeByPrimaryKey(roleid.getIdInteger());
					if(iline > 0){
						return ResultUtil.success();
					}else{
						return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "启用/停用")
	@PostMapping(value = "/updateStat")
	public Result<SysRole> updateStat(@RequestBody ParamId roleid) {
		try {
			SysRole role = roleService.selectByPrimaryKey(roleid.getIdInteger());
			JwtUser u=(JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			int count = 0;
			if (role.getStat().equals(ResultEnum.STATUS_NORMAL.getMsg())) {
				role.setStat("停用");
				role.setOperator(u.getId());
				role.setOperateTime(new Date());
				count = roleService.update(role);
			}else if (role.getStat().equals(ResultEnum.STATUS_CANCEL.getMsg())) {
				role.setStat("启用");
				role.setOperator(u.getId());
				role.setOperateTime(new Date());
				count = roleService.update(role);
			}
			if (count>0) {
				return ResultUtil.success();
			}else{
				return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询role对象")
	@PostMapping(value = "/list")
	public Result<SysRole> list(@Valid @RequestBody RoleListParam roleListParam){
		try {
			if (roleListParam.getRow() ==null) roleListParam.setRow(1);
			if (roleListParam.getPagesize()==null) roleListParam.setPagesize(10);
			SysRole role = new SysRole();
			role.setName(roleListParam.getRoleName());
			role.setCode(roleListParam.getRoleCode());
			PageInfo<RoleListVo> rolePage = roleService.selectByRole(role,roleListParam.getRow(),roleListParam.getPagesize());
			Map<Object, Object> map = new HashMap<>();
			map.put("rolePage", rolePage);
			return ResultUtil.success(map);
		} catch (Exception e) { System.out.println(e.getMessage()); }
		return ResultUtil.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
	}
}
