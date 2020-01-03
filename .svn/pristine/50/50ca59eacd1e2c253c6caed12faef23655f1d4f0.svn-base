package com.emx.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.emx.platform.domain.SysRole;
import com.emx.platform.vo.RoleListVo;
@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    /**
     * 
    * @Description： 查询用户的角色
    * @author： yanglei
    * @date： 2018年10月19日 上午10:28:45
     */
    @Select("select r.* from sys_user_role ur JOIN sys_role r on ur.role_id = r.id "+
    		"where r.stat = '启用' and ur.user_id =#{userId}")
    List<SysRole> selectRoleByUser(String userId);
    /**
     * 
    * @Description： 根据权限url查询角色
    * @author： yanglei
    * @date： 2018年10月25日 上午10:28:45
     */
    @Select("select r.* from sys_role_authority rp LEFT JOIN sys_role r on rp.role_id = r.id "+
    		"where r.stat = '启用' and rp.auth_id =#{authorityId}")
    List<SysRole> selectRolesByAuthorityId(Integer authorityId);
    
    /**
     * 
    * @Description: 根据role对象进行查询
    * @author xuedeng
    * @date 2018年11月5日 上午9:22:58
     */
    List<RoleListVo> selectByRole(SysRole role);
    /**
     * 
    * @Description: 根据对象进行查询集合
    * @author xuedeng
    * @date 2018年11月29日 上午9:06:09
     */
    List<SysRole> selectListByRole(SysRole role);
    /**
	 * 
	* @Description: 根据角色名查询角色
	* @author xuedeng
	* @date 2018年11月29日 上午9:04:41
	 */
    SysRole selectByRoleName(@Param("name")String name, @Param("id")Integer id);
}