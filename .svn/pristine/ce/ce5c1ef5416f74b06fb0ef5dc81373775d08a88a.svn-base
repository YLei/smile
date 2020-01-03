package com.emx.platform.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final String id;
    private final String username; 
    private final String password;
    private final String realname;
    private final String icon;
    //对应角色（role）
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(String id, String username, String password,String realname, String icon, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.icon = icon;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
	public String getId() {
		return id;
	}
	public String getRealname() {
		return realname;
	}
	public String getIcon() {
		return icon;
	}
}