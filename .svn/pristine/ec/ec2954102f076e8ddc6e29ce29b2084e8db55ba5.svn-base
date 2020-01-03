package com.emx.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.emx.platform.domain.SysAttachment;
import com.emx.platform.vo.AttachmentEntityVo;

@Mapper
public interface SysAttachmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysAttachment record);

    int insertSelective(SysAttachment record);

    SysAttachment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysAttachment record);

    int updateByPrimaryKey(SysAttachment record);
    
    List<SysAttachment> selectByEntityId(String id);
    
    /**
     * 
    * @Description:  批量删除
    * @author yanglei
    * @date 2018年12月4日 下午4:28:34
     */
    int deleteByIds(@Param("ids")String[] ids);
    
    /**
     * 
    * @Description: 批量查询
    * @author yanglei
    * @date 2018年12月4日 下午4:29:21
     */
    List<SysAttachment> selectByIds(@Param("ids")String[] ids);
    /**
     * 
    * @Description: 根据entityid查询文件信息
    * @author xuedeng
    * @date 2018年12月19日 下午4:03:38
     */
    List<AttachmentEntityVo> selectByContractId(String id);
    
    /**
     * 
    * @Description: 根据entityid与文件目录查询文件信息
    * @author xuedeng
    * @date 2018年12月19日 下午4:03:38
     */
    List<SysAttachment> selectByEntityIdAndDir(@Param("id")String id,@Param("dir")String dir);
    
    
}