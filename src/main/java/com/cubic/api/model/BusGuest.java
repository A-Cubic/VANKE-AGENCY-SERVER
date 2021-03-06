package com.cubic.api.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "bus_guest")
public class BusGuest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 是否是维护人本人
	 */
	@Transient
	private String user_type="1";
	/**
	 * 搜索类型(1:客源名称,2:维护人名称)
	 */
	@Transient
	private String search_type="1";
	/**
	 * 客源编号
	 */
	private String number;
	/**
	 * 维护时间的type(1:大于等于10天但是小于15天,2:大于等于15天)
	 */
	@Transient
	private String casetype;

	/**
	 * 是否是合作人(0:不是,1:是)
	 */
	@Transient
	private String collaboratorType = "0";

	/**
	 * 客源姓名
	 */
	private String guestname;

	/**
	 * 客源性别(1:女,2男)
	 */
	private String sex;

	/**
	 * 客源等级(A,B,C)
	 */
	private String guestgrade;

	/**
	 * 客源委托类型(0:买,1:租)
	 */
	private String type;

	/**
	 * 心里价位
	 */
	private String heartprice;

	/**
	 * 客源联系方式
	 */
	private String phone;

	/**
	 * 备用电话
	 */
	private String phonetow;

	/**
	 * 备注信息
	 */
	private String remarks;

	/**
	 * 委托面积
	 */
	private String areas;

	/**
	 * 委托居室
	 */
	private String huxing;

	/**
	 * 位置
	 */
	private String position;

	/**
	 * 目的用途
	 */
	private String purpose;

	/**
	 * 标签(自定义)
	 */
	private String label;

	/**
	 * 是否是无效客源(0:否,1:是)
	 */
	private String iskey;

	/**
	 * 维护人账户名
	 */
	@Column(name = "record_user_name")
	private String recordUserName;

	/**
	 * 创建人账户名
	 */
	@Column(name = "create_user_name")
	private String createUserName;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private String createTime;

	/**
	 * 客源合作人(逗号间隔)
	 */
	private String collaboratorids;

	/**
	 * 上次维护时间
	 */
	@Column(name = "record_time")
	private String recordTime;
	/**
	 * 需求几室
	 */
	private String huxingshi;
	/**
	 * 需求几厅
	 */
	private String huxingting;
	/**
	 * 需求几卫
	 */
	private String huxingwei;
	/**
	 * 需求几厨
	 */
	private String huxingchu;

	/**
	 * 是否是共享池里的数据
	 */
	private String isshare;

	/**
	 * 维护人真实姓名
	 */
	@Column(name = "record_rel_name")
	private String recordRelName;
	/**
	 * 创建人真实姓名
	 */
	@Column(name = "create_rel_name")
	private String createRelName;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

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
	 * 获取客源姓名
	 *
	 * @return guestname - 客源姓名
	 */
	public String getGuestname() {
		return guestname;
	}

	/**
	 * 设置客源姓名
	 *
	 * @param guestname 客源姓名
	 */
	public void setGuestname(String guestname) {
		this.guestname = guestname;
	}

	/**
	 * 获取客源性别(1:女,2男)
	 *
	 * @return sex - 客源性别(1:女,2男)
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置客源性别(1:女,2男)
	 *
	 * @param sex 客源性别(1:女,2男)
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获取客源等级(A,B,C)
	 *
	 * @return guestgrade - 客源等级(A,B,C)
	 */
	public String getGuestgrade() {
		return guestgrade;
	}

	/**
	 * 设置客源等级(A,B,C)
	 *
	 * @param guestgrade 客源等级(A,B,C)
	 */
	public void setGuestgrade(String guestgrade) {
		this.guestgrade = guestgrade;
	}

	/**
	 * 获取客源委托类型(0:买,1:租)
	 *
	 * @return type - 客源委托类型(0:买,1:租)
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置客源委托类型(0:买,1:租)
	 *
	 * @param type 客源委托类型(0:买,1:租)
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取心里价位
	 *
	 * @return heartprice - 心里价位
	 */
	public String getHeartprice() {
		return heartprice;
	}

	/**
	 * 设置心里价位
	 *
	 * @param heartprice 心里价位
	 */
	public void setHeartprice(String heartprice) {
		this.heartprice = heartprice;
	}

	/**
	 * 获取客源联系方式(可以有两个逗号隔开)
	 *
	 * @return phone - 客源联系方式(可以有两个逗号隔开)
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置客源联系方式(可以有两个逗号隔开)
	 *
	 * @param phone 客源联系方式(可以有两个逗号隔开)
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取备注信息
	 *
	 * @return remarks - 备注信息
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 设置备注信息
	 *
	 * @param remarks 备注信息
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 获取委托面积
	 *
	 * @return areas - 委托面积
	 */
	public String getAreas() {
		return areas;
	}

	/**
	 * 设置委托面积
	 *
	 * @param areas 委托面积
	 */
	public void setAreas(String areas) {
		this.areas = areas;
	}

	/**
	 * 获取委托居室
	 *
	 * @return huxing - 委托居室
	 */
	public String getHuxing() {
		return huxing;
	}

	/**
	 * 设置委托居室
	 *
	 * @param huxing 委托居室
	 */
	public void setHuxing(String huxing) {
		this.huxing = huxing;
	}

	/**
	 * 获取位置
	 *
	 * @return position - 位置
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * 设置位置
	 *
	 * @param position 位置
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * 获取目的用途
	 *
	 * @return purpose - 目的用途
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * 设置目的用途
	 *
	 * @param purpose 目的用途
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 * 获取标签(自定义)
	 *
	 * @return label - 标签(自定义)
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * 设置标签(自定义)
	 *
	 * @param label 标签(自定义)
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * 获取是否是无效客源(0:否,1:是)
	 *
	 * @return iskey - 是否是无效客源(0:否,1:是)
	 */
	public String getIskey() {
		return iskey;
	}

	/**
	 * 设置是否是无效客源(0:否,1:是)
	 *
	 * @param iskey 是否是无效客源(0:否,1:是)
	 */
	public void setIskey(String iskey) {
		this.iskey = iskey;
	}

	/**
	 * 获取维护人账户名
	 *
	 * @return record_user_name - 维护人账户名
	 */
	public String getRecordUserName() {
		return recordUserName;
	}

	/**
	 * 设置维护人账户名
	 *
	 * @param recordUserName 维护人账户名
	 */
	public void setRecordUserName(String recordUserName) {
		this.recordUserName = recordUserName;
	}

	/**
	 * 获取创建人账户名
	 *
	 * @return create_user_name - 创建人账户名
	 */
	public String getCreateUserName() {
		return createUserName;
	}

	/**
	 * 设置创建人账户名
	 *
	 * @param createUserName 创建人账户名
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * 获取创建时间
	 *
	 * @return create_time - 创建时间
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime 创建时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取客源合作人(逗号间隔)
	 *
	 * @return collaboratorids - 客源合作人(逗号间隔)
	 */
	public String getCollaboratorids() {
		return collaboratorids;
	}

	/**
	 * 设置客源合作人(逗号间隔)
	 *
	 * @param collaboratorids 客源合作人(逗号间隔)
	 */
	public void setCollaboratorids(String collaboratorids) {
		this.collaboratorids = collaboratorids;
	}

	/**
	 * 上次维护时间
	 *
	 * @return recordTime
	 */
	public String getRecordTime() {
		return recordTime;
	}

	/**
	 * 上次维护时间
	 *
	 * @param recordTime
	 */
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getHuxingshi() {
		return huxingshi;
	}

	public void setHuxingshi(String huxingshi) {
		this.huxingshi = huxingshi;
	}

	public String getHuxingting() {
		return huxingting;
	}

	public void setHuxingting(String huxingting) {
		this.huxingting = huxingting;
	}

	public String getHuxingwei() {
		return huxingwei;
	}

	public void setHuxingwei(String huxingwei) {
		this.huxingwei = huxingwei;
	}

	public String getHuxingchu() {
		return huxingchu;
	}

	public void setHuxingchu(String huxingchu) {
		this.huxingchu = huxingchu;
	}

	public String getRecordRelName() {
		return recordRelName;
	}

	public void setRecordRelName(String recordRelName) {
		this.recordRelName = recordRelName;
	}

	public String getCreateRelName() {
		return createRelName;
	}

	public void setCreateRelName(String createRelName) {
		this.createRelName = createRelName;
	}

	public String getCasetype() {
		return casetype;
	}

	public void setCasetype(String casetype) {
		this.casetype = casetype;
	}

	public String getIsshare() {
		return isshare;
	}

	public void setIsshare(String isshare) {
		this.isshare = isshare;
	}

	public String getCollaboratorType() {
		return collaboratorType;
	}

	public void setCollaboratorType(String collaboratorType) {
		this.collaboratorType = collaboratorType;
	}

	public String getPhonetow() {
		return phonetow;
	}

	public void setPhonetow(String phonetow) {
		this.phonetow = phonetow;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getSearch_type() {
		return search_type;
	}

	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}

}