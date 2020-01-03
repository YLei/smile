package com.emx.platform.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emx.platform.common.DictionaryContext;
import com.emx.platform.dao.SysRoleAuthorityMapper;
import com.emx.platform.dao.SysRoleMapper;
import com.emx.platform.domain.SysAuthority;
import com.emx.platform.domain.SysRole;
import com.emx.platform.domain.SysRoleAuthority;
import com.emx.platform.utils.StringUtil;
import com.emx.platform.vo.RoleListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SysRoleService {
	
	@Autowired private SysRoleMapper roleMapper;
	@Autowired private SysRoleAuthorityMapper roleAuthorityMapper;
	@Autowired private SysAuthorityService authService;
	
	@Transactional
	public int save(SysRole role) {
		role.setStat(DictionaryContext.STAT_ENABLE);
		role.setCreateTime(new Date());
		role.setOperateTime(new Date());
		int count = roleMapper.insertSelective(role);
		int roleId = role.getId();
		SysRoleAuthority roleAuthority = null;
		for (Integer authorityId : role.getAuthId()) {
			roleAuthority = new SysRoleAuthority();
			roleAuthority.setRoleId(roleId);
			roleAuthority.setAuthId(authorityId);
			roleAuthority.setCreateTime(new Date());
			roleAuthority.setOperateTime(new Date());
			roleAuthorityMapper.insertSelective(roleAuthority);
		}
		return count;
	}
	
	@Transactional
	public int update(SysRole role) {
		role.setOperateTime(new Date());
		int count = roleMapper.updateByPrimaryKeySelective(role);
		if (StringUtil.isNotEmpty(role.getAuthId())) {
			roleAuthorityMapper.deleteByRoleId(role.getId());
			SysRoleAuthority roleAuthority = null;
			for (Integer authorityId : role.getAuthId()) {
				roleAuthority = new SysRoleAuthority();
				roleAuthority.setRoleId(role.getId());
				roleAuthority.setAuthId(authorityId);
				roleAuthority.setCreateTime(new Date());
				roleAuthority.setOperateTime(new Date());
				roleAuthorityMapper.insertSelective(roleAuthority);
			}
		}
		return count;
	}

	public SysRole selectByPrimaryKey(Integer id) {
		SysRole role = roleMapper.selectByPrimaryKey(id);
		return role;
	}
	
	@Transactional
	public int removeByPrimaryKey(Integer id) {
		int count = roleMapper.deleteByPrimaryKey(id);
		return count;
	}
	
	public PageInfo<RoleListVo> selectByRole(SysRole role,Integer row,Integer pagesize) {
		PageHelper.startPage(row,pagesize);
		List<RoleListVo> listDtos = roleMapper.selectByRole(role);
		for (RoleListVo roleListDto : listDtos) {
			List<SysAuthority> authorities = authService.selectAuthByRoleId(roleListDto.getRoleId());
			StringBuffer authority = new StringBuffer();
			for (int i = 0; i < authorities.size(); i++) {
				authority.append(authorities.get(i).getName());
				if (i<authorities.size()-1) {
					authority.append(" | ");
				}
			}
			roleListDto.setAuthority(authority.toString());
		}
		PageInfo<RoleListVo> pageInfo = new PageInfo<RoleListVo>(listDtos);
		return pageInfo;
	}

	public SysRole selectByRoleName(String name, Integer id) {
		SysRole role = roleMapper.selectByRoleName(name,id);
		return role;
	}
}












