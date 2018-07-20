package com.cubic.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bus_house_like")
public class BusHouseLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关注房源id
     */
    @Column(name = "house_id")
    private Long houseId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 用户name
     */
    @Column(name = "user_name")
    private byte[] userName;

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
     * 获取关注房源id
     *
     * @return house_id - 关注房源id
     */
    public Long getHouseId() {
        return houseId;
    }

    /**
     * 设置关注房源id
     *
     * @param houseId 关注房源id
     */
    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取用户name
     *
     * @return user_name - 用户name
     */
    public byte[] getUserName() {
        return userName;
    }

    /**
     * 设置用户name
     *
     * @param userName 用户name
     */
    public void setUserName(byte[] userName) {
        this.userName = userName;
    }
}