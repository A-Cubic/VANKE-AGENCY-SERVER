package com.cubic.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bus_house")
public class BusHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 房屋编号
     */
    private String number;
    /**
     * 房屋标题
     */
    private String title;

    /**
     * 房主姓名
     */
    private String owner;

    /**
     * 房主联系方式
     */
    private String phone;

    /**
     * 房屋金额
     */
    private String price;

    /**
     * 房屋楼层
     */
    private String floor;

    /**
     * 房屋户型
     */
    private String huxing;

    /**
     * 房屋面积
     */
    private String areas;

    /**
     * 房屋朝向
     */
    private String chaoxiang;

    /**
     * 房屋等级(S,A,B,C)
     */
    private String grade="B";

    /**
     * 房屋维护人的id
     */
    @Column(name = "record_user_id")
    private Long recordUserId;

    /**
     * 房屋提交人的id
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 房屋提交时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 房屋介绍
     */
    private String introduce;

    /**
     * 房源类型(1:买卖房源,2出租房源)
     */
    private String type;

    /**
     * 房源状态(0:普通,1:特殊,2:无效,3:已售出/已租出)
     */
    private String state="0";

    /**
     * 房屋详细地址
     */
    private String address;

    /**
     * 房屋钥匙状态在不在维护人手里(0:不在,1:在)
     */
    private String iskey;

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
     * 获取房屋编号
     *
     * @return number 房屋编号
     */
    public String getNumber() {
		return number;
	}
	/**
     * 设置房屋编号
     *
     * @return number 房屋编号
     */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
     * 获取房屋标题
     *
     * @return title - 房屋标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置房屋标题
     *
     * @param title 房屋标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取房主姓名
     *
     * @return owner - 房主姓名
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 设置房主姓名
     *
     * @param owner 房主姓名
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * 获取房主联系方式
     *
     * @return phone - 房主联系方式
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置房主联系方式
     *
     * @param phone 房主联系方式
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取房屋金额
     *
     * @return price - 房屋金额
     */
    public String getPrice() {
        return price;
    }

    /**
     * 设置房屋金额
     *
     * @param price 房屋金额
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 获取房屋楼层
     *
     * @return floor - 房屋楼层
     */
    public String getFloor() {
        return floor;
    }

    /**
     * 设置房屋楼层
     *
     * @param floor 房屋楼层
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * 获取房屋户型
     *
     * @return huxing - 房屋户型
     */
    public String getHuxing() {
        return huxing;
    }

    /**
     * 设置房屋户型
     *
     * @param huxing 房屋户型
     */
    public void setHuxing(String huxing) {
        this.huxing = huxing;
    }

    /**
     * 获取房屋面积
     *
     * @return areas - 房屋面积
     */
    public String getAreas() {
        return areas;
    }

    /**
     * 设置房屋面积
     *
     * @param areas 房屋面积
     */
    public void setAreas(String areas) {
        this.areas = areas;
    }

    /**
     * 获取房屋朝向
     *
     * @return chaoxiang - 房屋朝向
     */
    public String getChaoxiang() {
        return chaoxiang;
    }

    /**
     * 设置房屋朝向
     *
     * @param chaoxiang 房屋朝向
     */
    public void setChaoxiang(String chaoxiang) {
        this.chaoxiang = chaoxiang;
    }

    /**
     * 获取房屋等级(S,A,B,C)
     *
     * @return grade - 房屋等级(S,A,B,C)
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 设置房屋等级(S,A,B,C)
     *
     * @param grade 房屋等级(S,A,B,C)
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获取房屋维护人的id
     *
     * @return record_user_id - 房屋维护人的id
     */
    public Long getRecordUserId() {
        return recordUserId;
    }

    /**
     * 设置房屋维护人的id
     *
     * @param recordUserId 房屋维护人的id
     */
    public void setRecordUserId(Long recordUserId) {
        this.recordUserId = recordUserId;
    }

    /**
     * 获取房屋提交人的id
     *
     * @return create_user_id - 房屋提交人的id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置房屋提交人的id
     *
     * @param createUserId 房屋提交人的id
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取房屋提交时间
     *
     * @return create_time - 房屋提交时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置房屋提交时间
     *
     * @param createTime 房屋提交时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取房屋介绍
     *
     * @return introduce - 房屋介绍
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置房屋介绍
     *
     * @param introduce 房屋介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 获取房源类型(1:买卖房源,2出租房源)
     *
     * @return type - 房源类型(1:买卖房源,2出租房源)
     */
    public String getType() {
        return type;
    }

    /**
     * 设置房源类型(1:买卖房源,2出租房源)
     *
     * @param type 房源类型(1:买卖房源,2出租房源)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取房源状态(0:普通,1:特殊,2:无效,3:已售出/已租出)
     *
     * @return state - 房源状态(0:普通,1:特殊,2:无效,3:已售出/已租出)
     */
    public String getState() {
        return state;
    }

    /**
     * 设置房源状态(0:普通,1:特殊,2:无效,3:已售出/已租出)
     *
     * @param state 房源状态(0:普通,1:特殊,2:无效,3:已售出/已租出)
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取房屋详细地址
     *
     * @return address - 房屋详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置房屋详细地址
     *
     * @param address 房屋详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取房屋钥匙状态在不在维护人手里(0:不在,1:在)
     *
     * @return iskey - 房屋钥匙状态在不在维护人手里(0:不在,1:在)
     */
    public String getIskey() {
        return iskey;
    }

    /**
     * 设置房屋钥匙状态在不在维护人手里(0:不在,1:在)
     *
     * @param iskey 房屋钥匙状态在不在维护人手里(0:不在,1:在)
     */
    public void setIskey(String iskey) {
        this.iskey = iskey;
    }
}