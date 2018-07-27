package com.cubic.api.model;


import javax.persistence.*;

@Table(name = "bus_house_lookrecord")
public class BusHouseLookrecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 带客户看房的账户名
     */
    @Column(name = "user_name")
    private String userName;
    /**
     * 带看人真实姓名
     */
    private String userRelName;
    /**
     * 房源id
     */
    @Column(name = "house_id")
    private Long houseId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 结束时间
     */
    private String endtime;

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
     * 获取带客户看房的账户名
     *
     * @return user_name - 带客户看房的账户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置带客户看房的账户名
     *
     * @param userName 带客户看房的账户名
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
     * 获取结束时间
     *
     * @return endtime - 结束时间
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     * 设置结束时间
     *
     * @param endtime 结束时间
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

	public String getUserRelName() {
		return userRelName;
	}

	public void setUserRelName(String userRelName) {
		this.userRelName = userRelName;
	}
    
    
}