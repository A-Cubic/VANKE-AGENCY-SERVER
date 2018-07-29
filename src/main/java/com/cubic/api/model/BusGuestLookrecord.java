package com.cubic.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bus_guest_lookrecord")
public class BusGuestLookrecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 维护人
     */
    @Column(name = "user_name")
    private String userName;
    /**
     * 带看人真实姓名
     */
    @Transient
    private String userRelName;

    /**
     * 客源id
     */
    @Column(name = "guest_id")
    private Long guestId;

    /**
     * 带看房的id
     */
    @Column(name = "house_id")
    private Long houseId;

    /**
     * 带看客源对房子的反馈
     */
    private String feedback;

    /**
     * 带看时间
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
     * 获取维护人
     *
     * @return user_name - 维护人
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置维护人
     *
     * @param userName 维护人
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
     * 获取带看房的id
     *
     * @return house_id - 带看房的id
     */
    public Long getHouseId() {
        return houseId;
    }

    /**
     * 设置带看房的id
     *
     * @param houseId 带看房的id
     */
    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    /**
     * 获取带看客源对房子的反馈
     *
     * @return feedback - 带看客源对房子的反馈
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * 设置带看客源对房子的反馈
     *
     * @param feedback 带看客源对房子的反馈
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    /**
     * 获取带看时间
     *
     * @return create_time - 带看时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置带看时间
     *
     * @param createTime 带看时间
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