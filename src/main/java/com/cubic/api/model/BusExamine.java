package com.cubic.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bus_examine")
public class BusExamine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 房源id
     */
    @Column(name = "house_id")
    private Long houseId;
    
    /**
     * 客源id
     */
    @Column(name = "guest_id")
    private Long guestId;
    /**
     * 客源id
     */
    @Column(name = "transaction_id")
    private Long transactionId;

    /**
     * 审核类型(1:特殊房源,2:优质房源,3:无效房源,4:实勘图片,5:无效客源)
     */
    private String type;

    
    /**
     * 申请人真实姓名
     */
    @Column(name = "user_rel_name")
    private String userRelName;
    /**
     * 审批人真实姓名
     */
    @Column(name = "examine_rel_name")
    private String examineRelName;
    /**
     * 审核未通过理由
     */
    private String content;
    
    /**
     * 登录人权限
     */
	@Transient
    private String roleid;
    

    /**
     * 室审核图片
     */
    private String shiimg;

    /**
     * 厅审核图片
     */
    private String tingimg;

    /**
     * 卫审核图片
     */
    private String weiimg;

    /**
     * 厨审核图片
     */
    private String chuimg;

    /**
     * 户型审核图片
     */
    private String huxingimg;

    /**
     * 其他审核图片
     */
    private String otherimg;

    /**
     * 申请人账号名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 审核状态(0:未审核,1:审核中,2:审核结束)
     */
    private String state;

    /**
     * 申请审核时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 审核结果(0:未通过,1:通过)
     */
    private String result;

    /**
     * 审批人账号
     */
    @Column(name = "examine_name")
    private String examineName;

    /**
     * 审批时间
     */
    @Column(name = "examine_time")
    private Date examineTime;

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
     * 获取房源id
     *
     * @return house_id - 房源id
     */
    public Long getHouseId() {
        return houseId;
    }

    /**
     * 设置房源id
     *
     * @param houseId 房源id
     */
    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

	/**
     * 获取审核类型(1:特殊房源,2:优质房源,3:无效房源,4:实勘图片,5:无效客源)
     *
     * @return type - 审核类型(1:特殊房源,2:优质房源,3:无效房源,4:实勘图片,5:无效客源)
     */
    public String getType() {
        return type;
    }

    /**
     * 设置审核类型(1:特殊房源,2:优质房源,3:无效房源,4:实勘图片,5:无效客源)
     *
     * @param type 审核类型(1:特殊房源,2:优质房源,3:无效房源,4:实勘图片,5:无效客源)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取审核未通过理由
     *
     * @return content - 审核未通过理由
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置审核未通过理由
     *
     * @param content 审核未通过理由
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取室审核图片
     *
     * @return shiimg - 室审核图片
     */
    public String getShiimg() {
        return shiimg;
    }

    /**
     * 设置室审核图片
     *
     * @param shiimg 室审核图片
     */
    public void setShiimg(String shiimg) {
        this.shiimg = shiimg;
    }

    /**
     * 获取厅审核图片
     *
     * @return tingimg - 厅审核图片
     */
    public String getTingimg() {
        return tingimg;
    }

    /**
     * 设置厅审核图片
     *
     * @param tingimg 厅审核图片
     */
    public void setTingimg(String tingimg) {
        this.tingimg = tingimg;
    }

    /**
     * 获取卫审核图片
     *
     * @return weiimg - 卫审核图片
     */
    public String getWeiimg() {
        return weiimg;
    }

    /**
     * 设置卫审核图片
     *
     * @param weiimg 卫审核图片
     */
    public void setWeiimg(String weiimg) {
        this.weiimg = weiimg;
    }

    /**
     * 获取厨审核图片
     *
     * @return chuimg - 厨审核图片
     */
    public String getChuimg() {
        return chuimg;
    }

    /**
     * 设置厨审核图片
     *
     * @param chuimg 厨审核图片
     */
    public void setChuimg(String chuimg) {
        this.chuimg = chuimg;
    }

    /**
     * 获取户型审核图片
     *
     * @return huxingimg - 户型审核图片
     */
    public String getHuxingimg() {
        return huxingimg;
    }

    /**
     * 设置户型审核图片
     *
     * @param huxingimg 户型审核图片
     */
    public void setHuxingimg(String huxingimg) {
        this.huxingimg = huxingimg;
    }

    /**
     * 获取其他审核图片
     *
     * @return otherimg - 其他审核图片
     */
    public String getOtherimg() {
        return otherimg;
    }

    /**
     * 设置其他审核图片
     *
     * @param otherimg 其他审核图片
     */
    public void setOtherimg(String otherimg) {
        this.otherimg = otherimg;
    }

    /**
     * 获取申请人账号名
     *
     * @return user_name - 申请人账号名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置申请人账号名
     *
     * @param userName 申请人账号名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取审核状态(0:未审核,1:审核中,2:审核结束)
     *
     * @return state - 审核状态(0:未审核,1:审核中,2:审核结束)
     */
    public String getState() {
        return state;
    }

    /**
     * 设置审核状态(0:未审核,1:审核中,2:审核结束)
     *
     * @param state 审核状态(0:未审核,1:审核中,2:审核结束)
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取申请审核时间
     *
     * @return create_time - 申请审核时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置申请审核时间
     *
     * @param createTime 申请审核时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取审核结果(0:未通过,1:通过)
     *
     * @return result - 审核结果(0:未通过,1:通过)
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置审核结果(0:未通过,1:通过)
     *
     * @param result 审核结果(0:未通过,1:通过)
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取审批人账号
     *
     * @return examine_name - 审批人账号
     */
    public String getExamineName() {
        return examineName;
    }

    /**
     * 设置审批人账号
     *
     * @param examineName 审批人账号
     */
    public void setExamineName(String examineName) {
        this.examineName = examineName;
    }

    /**
     * 获取审批时间
     *
     * @return examine_time - 审批时间
     */
    public Date getExamineTime() {
        return examineTime;
    }

    /**
     * 设置审批时间
     *
     * @param examineTime 审批时间
     */
    public void setExamineTime(Date examineTime) {
        this.examineTime = examineTime;
    }

	public String getUserRelName() {
		return userRelName;
	}

	public void setUserRelName(String userRelName) {
		this.userRelName = userRelName;
	}

	public String getExamineRelName() {
		return examineRelName;
	}

	public void setExamineRelName(String examineRelName) {
		this.examineRelName = examineRelName;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
    
    
}