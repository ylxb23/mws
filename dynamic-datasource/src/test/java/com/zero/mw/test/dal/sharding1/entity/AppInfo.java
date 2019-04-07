package com.zero.mw.test.dal.sharding1.entity;

import java.util.Date;

public class AppInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_info.id
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_info.app_name
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    private String appName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_info.app_version
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    private String appVersion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_info.description
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_info.create_time
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_info.update_time
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_info.is_deleted
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    private Byte isDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_info.id
     *
     * @return the value of app_info.id
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_info.id
     *
     * @param id the value for app_info.id
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_info.app_name
     *
     * @return the value of app_info.app_name
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    public String getAppName() {
        return appName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_info.app_name
     *
     * @param appName the value for app_info.app_name
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_info.app_version
     *
     * @return the value of app_info.app_version
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_info.app_version
     *
     * @param appVersion the value for app_info.app_version
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_info.description
     *
     * @return the value of app_info.description
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_info.description
     *
     * @param description the value for app_info.description
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_info.create_time
     *
     * @return the value of app_info.create_time
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_info.create_time
     *
     * @param createTime the value for app_info.create_time
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_info.update_time
     *
     * @return the value of app_info.update_time
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_info.update_time
     *
     * @param updateTime the value for app_info.update_time
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_info.is_deleted
     *
     * @return the value of app_info.is_deleted
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_info.is_deleted
     *
     * @param isDeleted the value for app_info.is_deleted
     *
     * @mbg.generated Fri Nov 03 16:09:03 CST 2017
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}