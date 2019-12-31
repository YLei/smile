package com.emx.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.emx.platform.domain.SysUser;
import com.emx.platform.vo.UserListVo;
@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    /**
     * 
    * @Description： 根据用户名（username）查询user对象
    * @author： yanglei
    * @date： 2018年10月19日 上午10:29:17
     */
    @Select("select * from sys_user u where u.stat = '启用' and u.user_name = #{username} ")
    SysUser findUserByUserName(String username);
    
    /**
     * 
    * @Description： 根据用户名（username）查询user对象是否失效
    * @author： yanglei
    * @date： 2018年10月19日 上午10:29:17
     */
    @Select("select * from sys_user u where u.stat = '停用' and u.user_name = #{username} ")
    SysUser findIsFalseByUserName(String username);
    
    /**
     * 
    * @Description： 根据用户列表
    * @author： yanglei
    * @date： 2018年10月19日 上午10:29:17
     */
    @Select("select * from sys_user u where u.stat = '启用' ")
    List<SysUser> getUserList();
    
    /**
     * 
    * @Description: user分页
    * @author xuedeng
    * @date 2018年11月5日 下午1:32:42
     */
    List<UserListVo> selectByUser(SysUser user);
    
    /**
     * 
    * @Description: 根据user对象进行查询
    * @author xuedeng
    * @date 2018年11月8日 下午5:03:47
     */
    List<SysUser> selectListByUser(SysUser user);
    
    /**
     * 
    * @Description: 根据用户名查找
    * @author xuedeng
    * @date 2018年11月20日 下午1:32:03
     */
    SysUser selectByUsername(String username);
    /**
	 * 
	* @Description: 根据真实姓名模糊查询
	* @author xuedeng
	* @date 2018年12月3日 下午1:45:13
	 */
    List<SysUser> selectListByRealName(@Param("realname")String realname);

    /**
     * 
    * @Description: 查询所有用户，包括删除
    * @author xuedeng
    * @date 2019年1月16日 上午11:50:42
     */
	List<SysUser> selectAllListByUser(SysUser user);

    
}