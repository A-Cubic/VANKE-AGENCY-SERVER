package com.cubic.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bus_house_record")
public class BusHouseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 跟进维护人账号
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 跟进房屋id
     */
    @Column(name = "house_id")
    private Long houseId;

    /**
     * 跟进内容
     */
    private String content;

    /**
     * 跟进时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 置顶状态(0:不置顶,1:置顶)
     */
    private String istop;

    /**
     * 置顶时间
     */
    private Date toptime;

    /**
     * 置顶icon
     */
    private String topicon;

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
     * 获取跟进维护人账号
     *
     * @return user_name - 跟进维护人账号
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置跟进维护人账号
     *
     * @param userName 跟进维护人账号
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取跟进房屋id
     *
     * @return house_id - 跟进房屋id
     */
    public Long getHouseId() {
        return houseId;
    }

    /**
     * 设置跟进房屋id
     *
     * @param houseId 跟进房屋id
     */
    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    /**
     * 获取跟进内容
     *
     * @return content - 跟进内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置跟进内容
     *
     * @param content 跟进内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取跟进时间
     *
     * @return create_time - 跟进时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置跟进时间
     *
     * @param createTime 跟进时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取置顶状态(0:不置顶,1:置顶)
     *
     * @return istop - 置顶状态(0:不置顶,1:置顶)
     */
    public String getIstop() {
        return istop;
    }

    /**
     * 设置置顶状态(0:不置顶,1:置顶)
     *
     * @param istop 置顶状态(0:不置顶,1:置顶)
     */
    public void setIstop(String istop) {
        this.istop = istop;
    }

    /**
     * 获取置顶时间
     *
     * @return toptime - 置顶时间
     */
    public Date getToptime() {
        return toptime;
    }

    /**
     * 设置置顶时间
     *
     * @param toptime 置顶时间
     */
    public void setToptime(Date toptime) {
        this.toptime = toptime;
    }

    /**
     * 获取置顶icon
     *
     * @return topicon - 置顶icon
     */
    public String getTopicon() {
        return topicon;
    }

    /**
     * 设置置顶icon
     *
     * @param topicon 置顶icon
     */
    public void setTopicon(String topicon) {
        this.topicon = topicon;
    }
}