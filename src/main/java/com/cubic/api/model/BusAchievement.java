package com.cubic.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bus_achievement")
public class BusAchievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户账号
     */
    @Column(name = "user_name")
    private String userName;
    
    /**
     * 真实姓名
     */
    @Transient
    private String userrelname;
    /**
     * 个人总业绩
     */
    @Transient
    private String sumprice;

    /**
     * 房源id
     */
    @Column(name = "house_id")
    private Long houseId;

    /**
     * 成交id
     */
    @Column(name = "transaction_id")
    private Long transactionId;

    /**
     * 角色(1:房源录入人,2:房源维护人,3:房源钥匙持有人,4:房源实勘录入人,5:独家人,6:成交促成人,7:促成合作人)
     */
    private String rolenum;

    /**
     * 用户业绩占比(%)
     */
    private String proportion;

    /**
     * 实际业绩金额
     */
    private String price;

    /**
     * 审核状态(0:未审核,1审核通过)
     */
    @Column(name = "examine_type")
    private String examineType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String  createTime;

    
    /**
     * 小区名称
     */
    
    @Transient
    private String xiaoquName;
    /**
     * 成交编号
     */
    @Transient
    private String dealNum;
    /**
     * 合同编号
     */
    @Transient
    private String contractNum;
    /**
     * 角色名
     */
    @Transient
    private String roleName;
    
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
     * 获取用户账号
     *
     * @return user_name - 用户账号
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户账号
     *
     * @param userName 用户账号
     */
    public void setUserName(String userName) {
        this.userName = userName;
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

    /**
     * 获取成交id
     *
     * @return transaction_id - 成交id
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * 设置成交id
     *
     * @param transactionId 成交id
     */
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * 获取角色(1:房源录入人,2:房源维护人,3:房源钥匙持有人,4:房源实勘录入人,5:独家人,6:成交促成人,7:促成合作人)
     *
     * @return rolenum - 角色(1:房源录入人,2:房源维护人,3:房源钥匙持有人,4:房源实勘录入人,5:独家人,6:成交促成人,7:促成合作人)
     */
    public String getRolenum() {
        return rolenum;
    }

    /**
     * 设置角色(1:房源录入人,2:房源维护人,3:房源钥匙持有人,4:房源实勘录入人,5:独家人,6:成交促成人,7:促成合作人)
     *
     * @param rolenum 角色(1:房源录入人,2:房源维护人,3:房源钥匙持有人,4:房源实勘录入人,5:独家人,6:成交促成人,7:促成合作人)
     */
    public void setRolenum(String rolenum) {
        this.rolenum = rolenum;
    }

    /**
     * 获取用户业绩占比(%)
     *
     * @return proportion - 用户业绩占比(%)
     */
    public String getProportion() {
        return proportion;
    }

    /**
     * 设置用户业绩占比(%)
     *
     * @param proportion 用户业绩占比(%)
     */
    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    /**
     * 获取实际业绩金额
     *
     * @return price - 实际业绩金额
     */
    public String getPrice() {
        return price;
    }

    /**
     * 设置实际业绩金额
     *
     * @param price 实际业绩金额
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 获取审核状态(0:未审核,1审核通过)
     *
     * @return examine_type - 审核状态(0:未审核,1审核通过)
     */
    public String getExamineType() {
        return examineType;
    }

    /**
     * 设置审核状态(0:未审核,1审核通过)
     *
     * @param examineType 审核状态(0:未审核,1审核通过)
     */
    public void setExamineType(String examineType) {
        this.examineType = examineType;
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

	public String getUserrelname() {
		return userrelname;
	}

	public void setUserrelname(String userrelname) {
		this.userrelname = userrelname;
	}

	public String getSumprice() {
		return sumprice;
	}

	public void setSumprice(String sumprice) {
		this.sumprice = sumprice;
	}

	public String getXiaoquName() {
		return xiaoquName;
	}

	public void setXiaoquName(String xiaoquName) {
		this.xiaoquName = xiaoquName;
	}

	public String getDealNum() {
		return dealNum;
	}

	public void setDealNum(String dealNum) {
		this.dealNum = dealNum;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
    
}