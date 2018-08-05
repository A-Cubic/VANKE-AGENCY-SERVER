package com.cubic.api.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
     * 点击查看类型(1:联系方式,2:房屋地址)
     */
    private String type;
    /**
     * 查看房源的id
     */
    @Column(name = "house_id")
    private Long houseId;
    /**
     * 真实姓名
     * */
    @Transient
    private String clickRelName;

    /**
     * 房源维护人的账号名
     */
    @Column(name = "record_user_name")
    private String recordUserName;

    /**
     * 点击查看时间
     */
    @Column(name = "create_time")
    private String createTime;

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

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置点击查看时间
     *
     * @param createTime 点击查看时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

	public String getClickRelName() {
		return clickRelName;
	}

	public void setClickRelName(String clickRelName) {
		this.clickRelName = clickRelName;
	}
    
    
}