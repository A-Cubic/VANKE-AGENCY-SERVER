package com.cubic.api.model.home;

import java.util.List;
import java.util.Map;

public class CurrentScore {
	private Long id;
	private String rank;
	private String type;
	private String user_name;
	private String real_name;
	private Long store_id;
	private String store_name;
	private String score;
	private String houseScore="0";//房积分
	private String guestScore="0";//客积分
	private String lookrecordScore="0";//带看积分
	private String keyScore="0";//钥匙积分
    private String sumScore="0";//总积分
	/**
	 *本店量化带看积分列表
	 *返回map里的内容:
	 *createTime:但看时间
	 *countScore:每条的积分
	 * 
	 * */
	private List<Map<String,Object>> myStoreLookList;
	
	/**
	 *本店量化钥匙积分列表
	 *返回map里的内容:
	 *createTime:但看时间
	 *countScore:每条的积分
	 * 
	 * */
	private List<Map<String,Object>> myStoreKeyList; 
	/**
	 *所有门店量化带看积分列表
	 *返回map里的内容:
	 *createTime:但看时间
	 *countScore:每条的积分
	 * 
	 * */
	private List<Map<String,Object>> storeAllLookList;
	/**
	 *所有门店量化钥匙积分列表
	 *返回map里的内容:
	 *createTime:但看时间
	 *countScore:每条的积分
	 * 
	 * */
	private List<Map<String,Object>> storeAllKeyList; 

	public List<Map<String, Object>> getMyStoreLookList() {
		return myStoreLookList;
	}

	public void setMyStoreLookList(List<Map<String, Object>> myStoreLookList) {
		this.myStoreLookList = myStoreLookList;
	}

	public List<Map<String, Object>> getStoreAllLookList() {
		return storeAllLookList;
	}

	public void setStoreAllLookList(List<Map<String, Object>> storeAllLookList) {
		this.storeAllLookList = storeAllLookList;
	}

	public String getScore() {
		return score;
	}

	public void setScore_value(String score) {
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getHouseScore() {
		return houseScore;
	}

	public void setHouseScore(String houseScore) {
		this.houseScore = houseScore;
	}

	public String getGuestScore() {
		return guestScore;
	}

	public void setGuestScore(String guestScore) {
		this.guestScore = guestScore;
	}

	public String getLookrecordScore() {
		return lookrecordScore;
	}

	public void setLookrecordScore(String lookrecordScore) {
		this.lookrecordScore = lookrecordScore;
	}

	public String getKeyScore() {
		return keyScore;
	}

	public void setKeyScore(String keyScore) {
		this.keyScore = keyScore;
	}

	public String getSumScore() {
		return sumScore;
	}

	public void setSumScore(String sumScore) {
		this.sumScore = sumScore;
	}

	public List<Map<String, Object>> getMyStoreKeyList() {
		return myStoreKeyList;
	}

	public void setMyStoreKeyList(List<Map<String, Object>> myStoreKeyList) {
		this.myStoreKeyList = myStoreKeyList;
	}

	public List<Map<String, Object>> getStoreAllKeyList() {
		return storeAllKeyList;
	}

	public void setStoreAllKeyList(List<Map<String, Object>> storeAllKeyList) {
		this.storeAllKeyList = storeAllKeyList;
	}

}
