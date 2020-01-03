package com.emx.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.emx.platform.domain.SysAuthority;
import com.emx.platform.vo.AuthorityListVo;

@Mapper
public interface SysAuthorityMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(SysAuthority record);

    int insertSelective(SysAuthority record);

    SysAuthority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAuthority record);

    int updateByPrimaryKey(SysAuthority record);
    
    List<SysAuthority> selectListByAuthority(SysAuthority authority);
    
    /**
     * 
    * @Description: 查找所有权限
    * @author xuedeng
    * @date 2018年11月7日 下午2:52:25
     */
    List<AuthorityListVo> selectByAuthority(SysAuthority authority);
    
    /**
     * 
    * @Description: 根据角色id查找他的权限
    * @author xuedeng
    * @date 2018年11月7日 下午2:52:01
     */
    List<SysAuthority> selectAuthByRoleId(Integer id);
    /**
     * 
    * @Description: 根据url地址查找对应的权限
    * @author yanglei
    * @date 2018年10月25日 下午2:34:23
     */
    @Select("select * from sys_authority p where p.url = #{url} ")
    List<SysAuthority> selectMenusByUrl(String url);
    /**
     * 
    * @Description: 根据角色编码查询对应的权限(type:表示不同权限类型)
    * @author yanglei
    * @date 2018年11月7日 下午7:01:25
     */
    List<SysAuthority> selectAuthByRoleList(@Param("roleCode")List<String> roleCode,@Param("type")Integer type,@Param("pid")Integer pid);
    /**
     * 
    * @Description: 权限列表list
    * @author xuedeng
    * @date 2019年1月28日 上午11:59:14
     */
	List<AuthorityListVo> selectByAuthorityPid(@Param("pid")Integer pid);
}