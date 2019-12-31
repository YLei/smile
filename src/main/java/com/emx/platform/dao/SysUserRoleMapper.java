package com.emx.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.emx.platform.domain.SysUserRole;
@Mapper
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
    
    /**
     * 
    * @Description: 根据用户id查找角色id
    * @author xuedeng
    * @date 2018年11月7日 上午9:10:53
     */
    List<SysUserRole> selectRoleIdByUserId(String id);
    /**
     * 
    * @Description: 根据用户删除角色
    * @author xuedeng
    * @date 2018年11月19日 上午9:36:40
     */
    int deleteByUserId(String id);
}