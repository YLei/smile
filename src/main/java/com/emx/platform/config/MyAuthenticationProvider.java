package com.emx.platform.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.emx.platform.dao.SysUserMapper;
import com.emx.platform.utils.EncryptCode;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserDetailsService userService;
	@Autowired
	private SysUserMapper userMapper;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String)authentication.getCredentials();
		String encodePwd = null;
        try {
			encodePwd = EncryptCode.encryptMD5String(password);
		} catch (Exception e1) { e1.printStackTrace(); }
		UserDetails user = (UserDetails) userService.loadUserByUsername(username);
        if(user == null) {
        	if(userMapper.findIsFalseByUserName(username) != null) {
        		throw new BadCredentialsException("该用户已被禁用");
        	} else { throw new BadCredentialsException("用户名不正确，请重新登录！"); }
        }
		if(user.getPassword().equals(encodePwd)) {
			Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		       return new UsernamePasswordAuthenticationToken(user, encodePwd, authorities);
		}
		else { throw new BadCredentialsException("密码不正确，请重新登录！"); }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
