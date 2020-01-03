package com.emx.platform.dao;

import org.apache.ibatis.annotations.Mapper;

import com.emx.platform.domain.SysRoleAuthority;
@Mapper
public interface SysRoleAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleAuthority record);

    int insertSelective(SysRoleAuthority record);

    SysRoleAuthority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleAuthority record);

    int updateByPrimaryKey(SysRoleAuthority record);
    
    int deleteByRoleId(Integer id);
}