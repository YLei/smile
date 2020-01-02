package com.emx.platform.dao;

import com.emx.platform.domain.Merchants;

public interface MerchantsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Merchants record);

    int insertSelective(Merchants record);

    Merchants selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Merchants record);

    int updateByPrimaryKey(Merchants record);
}