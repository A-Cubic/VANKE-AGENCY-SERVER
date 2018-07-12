package com.cubic.api.model;

import javax.persistence.*;

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
}