package com.emx.platform.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emx.platform.dao.SysRoleMapper;
import com.emx.platform.dao.SysUserMapper;
import com.emx.platform.domain.SysRole;
import com.emx.platform.domain.SysUser;
import com.emx.platform.vo.JwtUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysRoleMapper roleMapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
		List<GrantedAuthority> auths = new ArrayList<>();
		SysUser user = null;
		//根据用户名查询用户对象
		user = userMapper.findUserByUserName(username);
		if (user == null) {
			logger.debug("找不到该用户 用户名:{}", username);
			return null;
		}
		//根据用户名查询角色
		List<SysRole> roles = roleMapper.selectRoleByUser(user.getId());
		if (roles != null) { // 设置角色名称
			for (SysRole role : roles) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getCode());
				auths.add(authority);
			}
		}
		return new JwtUser(user.getId(), username, user.getPassword(),user.getRealname(),user.getIcon(), auths);
	}

}
