package com.emx.platform.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emx.platform.common.ResultEnum;
import com.emx.platform.dao.SysAttachmentMapper;
import com.emx.platform.dao.SysRoleMapper;
import com.emx.platform.dao.SysUserMapper;
import com.emx.platform.dao.SysUserRoleMapper;
import com.emx.platform.domain.SysAttachment;
import com.emx.platform.domain.SysRole;
import com.emx.platform.domain.SysUser;
import com.emx.platform.domain.SysUserRole;
import com.emx.platform.utils.StringUtil;
import com.emx.platform.vo.UserListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SysUserService {
	
	@Autowired private SysUserMapper userMapper;
	@Autowired private SysUserRoleMapper userRoleMapper;
	@Autowired private SysAttachmentMapper attMapper;
	@Autowired private SysUserService userService;
	@Autowired private SysRoleMapper roleMapper;
	
	public PageInfo<SysUser> getUserList(Integer row,Integer pagesize) {
		PageHelper.startPage(row,pagesize);
		List<SysUser> userList = userMapper.getUserList();
		PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(userList);
		return pageInfo;
	}
	
	@Transactional
	public int save(SysUser user,String creator) {
		user.setStat("启用");
		user.setCreateTime(new Date());
		user.setOperateTime(new Date());
		int count = userMapper.insertSelective(user);
		String userId = user.getId();
		if (StringUtil.isNotEmpty(user.getRoleIds())) {
			SysUserRole userRole = null;
			for (Integer roleId : user.getRoleIds()) {
				userRole = new SysUserRole();
				userRole.setUserId(userId);
				userRole.setRoleId(roleId);
				userRole.setCreator(creator);
				userRole.setCreateTime(new Date());
				userRole.setOperator(creator);
				userRole.setOperateTime(new Date());
				count = userRoleMapper.insertSelective(userRole);
			}	
		}
		if (user.getFileId()!=null && !"".equals(user.getFileId())) {
			SysAttachment att = attMapper.selectByPrimaryKey(user.getFileId());
			att.setCreateTime(new Date());
			att.setCreator(creator);
			att.setEntityId(user.getId());
			att.setStat(ResultEnum.STATUS_NORMAL.getCode().toString());
			att.setOperator(creator);
			att.setUploadTime(new Date());
			att.setUpdateTime(new Date());
			attMapper.updateByPrimaryKeySelective(att);
			SysUser user2 = userService.selectByPrimaryKey(userId);
			user2.setIcon(att.getUrl());
			count = userService.update(user2,null);
		}
		return count;
	}
	
	@Transactional
	public int update(SysUser user,String creator) {
		user.setOperateTime(new Date());
		user.setOperator(creator);
		int count = userMapper.updateByPrimaryKeySelective(user);
		if (StringUtil.isNotEmpty(user.getRoleIds())) {
			userRoleMapper.deleteByUserId(user.getId());
			SysUserRole userRole = null;
			for (Integer roleId : user.getRoleIds()) {
				userRole = new SysUserRole();
				userRole.setUserId(user.getId());
				userRole.setRoleId(roleId);
				userRole.setCreator(creator);
				userRole.setCreateTime(new Date());
				count = userRoleMapper.insertSelective(userRole);
			}
		}
		
		if (user.getFileId()!=null && !"".equals(user.getFileId())) {
			SysAttachment att = attMapper.selectByPrimaryKey(user.getFileId());
			att.setCreateTime(new Date());
			att.setCreator(creator);
			att.setEntityId(user.getId());
			att.setStat(ResultEnum.STATUS_NORMAL.getCode().toString());
			att.setOperator(creator);
			att.setUploadTime(new Date());
			att.setUpdateTime(new Date());
			attMapper.updateByPrimaryKeySelective(att);
			SysUser user2 = userService.selectByPrimaryKey(user.getId());
			user2.setIcon(att.getUrl());
			count = userService.update(user2,null);
		}
		return count;
	}

	public SysUser selectByPrimaryKey(String id) {
		SysUser user = userMapper.selectByPrimaryKey(id);
		return user;
	}
	
	@Transactional
	public int removeByPrimaryKey(String id) {
		int count = userMapper.deleteByPrimaryKey(id);
		return count;
	}

	public PageInfo<UserListVo> selectByUser(SysUser user,Integer row,Integer pagesize) {
		PageHelper.startPage(row,pagesize);
		PageInfo<UserListVo> pageInfo = new PageInfo<UserListVo>(userMapper.selectByUser(user));
		return pageInfo;
	}

	public List<SysUser> selectListByUser(SysUser user) {
		List<SysUser> listUser = userMapper.selectListByUser(user);
		return listUser;
	}

	public SysUser selectByUsername(String username) {
		SysUser user = userMapper.selectByUsername(username);
		return user;
	}

	public List<SysUser> selectListByRealName(String realName) {
		List<SysUser> listUser = userMapper.selectListByRealName(realName);
		return listUser;
	}


	public List<SysUser> selectAllListByUser(SysUser user) {
		List<SysUser> allUser = userMapper.selectAllListByUser(user);
		return allUser;
	}
}
