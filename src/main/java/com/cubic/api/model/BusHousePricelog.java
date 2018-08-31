package com.cubic.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "bus_house_pricelog")
public class BusHousePricelog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 修改的房源id
     */
    @Column(name = "house_id")
    private Long houseId;

    /**
     * 原价
     */
    private String priceold;

    /**
     * 修改后价格
     */
    private String pricenew;

    /**
     * 修改类型(1:上涨,2:下调)
     */
    private String type;

    /**
     * 修改时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改人账号
     */
    private String username;
    /**
     * 价格差价
     */
    private String pricelock;

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
     * 获取修改的房源id
     *
     * @return house_id - 修改的房源id
     */
    public Long getHouseId() {
        return houseId;
    }

    /**
     * 设置修改的房源id
     *
     * @param houseId 修改的房源id
     */
    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    /**
     * 获取原价
     *
     * @return priceold - 原价
     */
    public String getPriceold() {
        return priceold;
    }

    /**
     * 设置原价
     *
     * @param priceold 原价
     */
    public void setPriceold(String priceold) {
        this.priceold = priceold;
    }

    /**
     * 获取修改后价格
     *
     * @return pricenew - 修改后价格
     */
    public String getPricenew() {
        return pricenew;
    }

    /**
     * 设置修改后价格
     *
     * @param pricenew 修改后价格
     */
    public void setPricenew(String pricenew) {
        this.pricenew = pricenew;
    }

    /**
     * 获取修改类型(1:上涨,2:下调)
     *
     * @return type - 修改类型(1:上涨,2:下调)
     */
    public String getType() {
        return type;
    }

    /**
     * 设置修改类型(1:上涨,2:下调)
     *
     * @param type 修改类型(1:上涨,2:下调)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取修改时间
     *
     * @return create_time - 修改时间
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 设置修改时间
     *
     * @param createTime 修改时间
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改人账号
     *
     * @return username - 修改人账号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置修改人账号
     *
     * @param username 修改人账号
     */
    public void setUsername(String username) {
        this.username = username;
    }

	public String getPricelock() {
		return pricelock;
	}

	public void setPricelock(String pricelock) {
		this.pricelock = pricelock;
	}
    
    
    
}