package com.emx.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.emx.platform.domain.SysDictionary;
import com.emx.platform.vo.DictionaryMeanCommon;

@Mapper
public interface SysDictionaryMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysDictionary record);

    int insertSelective(SysDictionary record);

    SysDictionary selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDictionary record);

    int updateByPrimaryKey(SysDictionary record);
    
    List<SysDictionary> selectListByDiction(SysDictionary dictionary);
    /**
	 * 
	* @Description: 根据详情模糊查询
	* @author xuedeng
     * @param code 
	* @date 2018年12月4日 下午7:15:14
	 */
    List<SysDictionary> selectListByMean(@Param("mean")String mean, @Param("code")String code);
    
    /**
     * 
    * @Description: 根据编码查询详情
    * @author xuedeng
    * @date 2018年12月29日 下午5:03:57
     */
	List<DictionaryMeanCommon> selectMeanByCode(String code);
    
}