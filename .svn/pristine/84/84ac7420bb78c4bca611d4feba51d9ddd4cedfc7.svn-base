package com.emx.platform.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import com.emx.platform.dao.SysAuthorityMapper;
import com.emx.platform.dao.SysRoleMapper;
import com.emx.platform.domain.SysAuthority;
import com.emx.platform.domain.SysRole;

@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
	@Autowired
	private SysAuthorityMapper permissionMapper;
	@Autowired
	private SysRoleMapper roleMapper;
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		//获取当前访问url
        String url = ((FilterInvocation) object).getRequestUrl();
        int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            url = url.substring(0, firstQuestionMarkIndex);
        }
        List<ConfigAttribute> result = new ArrayList<ConfigAttribute>();
        //查询匹配的url
        List<SysAuthority> menuList = permissionMapper.selectMenusByUrl(url);
        ConfigAttribute conf = null;
        if (menuList != null && menuList.size() > 0) {
            for (SysAuthority menu : menuList) {
                //查询拥有该菜单权限的角色列表
                List<SysRole> roles = roleMapper.selectRolesByAuthorityId(menu.getId());
                if (roles != null && roles.size() > 0) {
                    for (SysRole role : roles) {
                        conf = new SecurityConfig(role.getCode());
                        result.add(conf);
                    }
                    return result;
                }
            }
        }
        return result;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
