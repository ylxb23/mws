package com.zero.mw.test.dal.zero.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zero.mw.test.dal.zero.entity.AppInfo;
import com.zero.mw.test.dal.zero.entity.AppInfoExample;

public interface ZeroAppInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    long countByExample(AppInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    int insert(AppInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    int insertSelective(AppInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    List<AppInfo> selectByExample(AppInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    AppInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    int updateByExampleSelective(@Param("record") AppInfo record, @Param("example") AppInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    int updateByExample(@Param("record") AppInfo record, @Param("example") AppInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    int updateByPrimaryKeySelective(AppInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_info
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    int updateByPrimaryKey(AppInfo record);

    
	int insertMulti(@Param("list") List<AppInfo> list);
}