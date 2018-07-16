package com.cubic.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bus_guest_record")
public class BusGuestRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 维护人账号名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 客源id
     */
    @Column(name = "guest_id")
    private Long guestId;

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
     * 置顶状态
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
     * 获取维护人账号名
     *
     * @return user_name - 维护人账号名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置维护人账号名
     *
     * @param userName 维护人账号名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取客源id
     *
     * @return guest_id - 客源id
     */
    public Long getGuestId() {
        return guestId;
    }

    /**
     * 设置客源id
     *
     * @param guestId 客源id
     */
    public void setGuestId(Long guestId) {
        this.guestId = guestId;
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
     * 获取置顶状态
     *
     * @return istop - 置顶状态
     */
    public String getIstop() {
        return istop;
    }

    /**
     * 设置置顶状态
     *
     * @param istop 置顶状态
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