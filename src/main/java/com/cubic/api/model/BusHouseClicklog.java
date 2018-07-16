package com.cubic.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bus_house_clicklog")
public class BusHouseClicklog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 点击查看人的账号名
     */
    @Column(name = "click_user_name")
    private String clickUserName;

    /**
     * 查看房源的id
     */
    @Column(name = "house_id")
    private Long houseId;

    /**
     * 房源维护人的账号名
     */
    @Column(name = "record_user_name")
    private String recordUserName;

    /**
     * 点击查看时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取点击查看人的账号名
     *
     * @return click_user_name - 点击查看人的账号名
     */
    public String getClickUserName() {
        return clickUserName;
    }

    /**
     * 设置点击查看人的账号名
     *
     * @param clickUserName 点击查看人的账号名
     */
    public void setClickUserName(String clickUserName) {
        this.clickUserName = clickUserName;
    }

    /**
     * 获取查看房源的id
     *
     * @return house_id - 查看房源的id
     */
    public Long getHouseId() {
        return houseId;
    }

    /**
     * 设置查看房源的id
     *
     * @param houseId 查看房源的id
     */
    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    /**
     * 获取房源维护人的账号名
     *
     * @return record_user_name - 房源维护人的账号名
     */
    public String getRecordUserName() {
        return recordUserName;
    }

    /**
     * 设置房源维护人的账号名
     *
     * @param recordUserName 房源维护人的账号名
     */
    public void setRecordUserName(String recordUserName) {
        this.recordUserName = recordUserName;
    }

    /**
     * 获取点击查看时间
     *
     * @return create_time - 点击查看时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置点击查看时间
     *
     * @param createTime 点击查看时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}