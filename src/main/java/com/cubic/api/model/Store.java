package com.cubic.api.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "base_store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 门店名称
     */
    @Column(name = "store_name")
    private String storeName;

    /**
     * 门店地址
     */
    @Column(name = "store_adress")
    private String storeAdress;
    /**
     *位置code
     *
     * */
    @Transient
    private List<String> regionCode;
    /**
     * 街道id
     * */
    @Transient
    private List<String>  streetId;
    /**
     * 用户列表
     * */
    @Transient
    private List<User> userlist;
    
    
    /**
     * 用户列表
     * */
    @Transient
    private List<Map<String,Object>> storeRangeList;

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
     * 获取门店名称
     *
     * @return store_name - 门店名称
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * 设置门店名称
     *
     * @param storeName 门店名称
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * 获取门店地址
     *
     * @return store_adress - 门店地址
     */
    public String getStoreAdress() {
        return storeAdress;
    }

    /**
     * 设置门店地址
     *
     * @param storeAdress 门店地址
     */
    public void setStoreAdress(String storeAdress) {
        this.storeAdress = storeAdress;
    }

	public List<String> getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(List<String> regionCode) {
		this.regionCode = regionCode;
	}

	public List<String> getStreetId() {
		return streetId;
	}

	public void setStreetId(List<String> streetId) {
		this.streetId = streetId;
	}

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}

	public List<Map<String, Object>> getStoreRangeList() {
		return storeRangeList;
	}

	public void setStoreRangeList(List<Map<String, Object>> storeRangeList) {
		this.storeRangeList = storeRangeList;
	}
    
    
}