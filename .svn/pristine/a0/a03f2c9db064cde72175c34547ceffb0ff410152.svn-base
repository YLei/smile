package com.emx.platform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emx.platform.dao.SysAuthorityMapper;
import com.emx.platform.domain.SysAuthority;
import com.emx.platform.utils.TreeEntity;
import com.emx.platform.utils.TreeUtil;
import com.emx.platform.vo.AuthorityListVo;
import com.emx.platform.utils.TreeAuth;
import com.emx.platform.utils.TreeAuthUtil;

@Service
public class SysAuthorityService {
	@Autowired
	private SysAuthorityMapper authorityMapper;

	public List<Map<String, Object>> selectByAuthority(SysAuthority authority) {
		List<TreeAuth> treeList = new ArrayList<TreeAuth>();
		List<AuthorityListVo> ListAuthority = authorityMapper.selectByAuthority(authority);
		for (AuthorityListVo authorityListDto : ListAuthority) {
			TreeAuth treeAuth = new TreeAuth();
			treeAuth.setAuthId(authorityListDto.getAuthId().toString());
			treeAuth.setName(authorityListDto.getName());
			treeAuth.setCode(authorityListDto.getCode());
			treeAuth.setSeq(authorityListDto.getSeq().toString());
			treeAuth.setLevel(authorityListDto.getLevel().toString());
			treeAuth.setPid(authorityListDto.getPid().toString());
			treeAuth.setUrl(authorityListDto.getUrl());
			treeAuth.setStat(authorityListDto.getStat());
			treeList.add(treeAuth);
		}
		TreeAuthUtil treeAuthUtil = new TreeAuthUtil();
		List<Map<String,Object>> list = treeAuthUtil.treeMenu(treeList);
		return list;
	}
	
	public List<Map<String, Object>> selectMenuByRole(List<String> roleCode,Integer type) {
		List<SysAuthority> listAuthority = authorityMapper.selectAuthByRoleList(roleCode, type,null);
		List<TreeEntity> treeList = new ArrayList<TreeEntity>();
		for(SysAuthority auth:listAuthority){
			TreeEntity tree = new TreeEntity();
			tree.setId(auth.getId().toString());
			tree.setName(auth.getName());
			tree.setParentId(auth.getPid().toString());
			tree.setUrl(auth.getUrl());
			treeList.add(tree);
		}
		TreeUtil treeUtil = new TreeUtil();
		List<Map<String,Object>> list = treeUtil.treeMenu(treeList);
		return list;
	}

	public List<SysAuthority> selectButtonByRoleAndPid(List<String> roleCode, Integer type, Integer pid) {
		List<SysAuthority> listAuthority = authorityMapper.selectAuthByRoleList(roleCode, type,pid);
		return listAuthority;
	}

	public List<SysAuthority> selectAuthByRoleId(Integer id) {
		List<SysAuthority> authorities = authorityMapper.selectAuthByRoleId(id);
		return authorities;
	}

	public List<AuthorityListVo> selectByAuthorityPid(Integer pid) {
		List<AuthorityListVo> ListAuthority = authorityMapper.selectByAuthorityPid(pid);
		return ListAuthority;
	}
}
