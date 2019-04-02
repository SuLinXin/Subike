package com.su.subike.bike.dao;

import com.su.subike.bike.entity.Bike;
import com.su.subike.bike.entity.BikeNoGen;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Bike record);

    int insertSelective(Bike record);

    Bike selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Bike record);

    int updateByPrimaryKey(Bike record);

    void generateBikeNo(BikeNoGen bikeNoGen);
}