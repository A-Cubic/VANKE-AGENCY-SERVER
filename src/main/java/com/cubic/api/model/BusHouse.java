package com.cubic.api.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "bus_house")
public class BusHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 房屋编号
     */
    private String number;
    /**
     * 用户是否是维护人
     */
    @Transient
    private String user_ype;
    /**
     * 详细地址文本
     */
    @Transient
    private String addressText;
    /**
     * 房屋标题
     */
    @Transient
    private String title;
    /**
     * 维护时间的type(1:大于等于10天但是小于15天,2:大于等于15天)
     */
    @Transient
    private String casetype;
    /**
     * 是否是特殊房源(0:否,1:是)
     */
    private String isspecial;
    

    /**
     * 房主姓名
     */
    private String owner;

    /**
     * 房主联系方式
     */
    private String phone;

    /**
     * 房屋金额
     */
    private String price;

    /**
     * 房屋楼层
     */
    private String floor;
    /**
     * 房屋最大楼层
     */
    private String maxfloor;
    

    /**
     * 房屋户型
     */
    private String huxing;
    /**
     * 房屋几室
     */
    private String huxingshi;
    /**
     * 房屋几厅
     */
    private String huxingting;
    /**
     * 房屋几卫
     */
    private String huxingwei;
    /**
     * 房屋几厨
     */
    private String huxingchu;
    

    /**
     * 房屋面积
     */
    private String areas;
    /**
     * 隐藏面积
     */
    private String hiddenarea;
    

    /**
     * 房屋朝向
     */
    private String chaoxiang;

    /**
     * 房屋等级(S,A,B,C)
     */
    private String grade;
    
    /**
     * 区域code
     * 
     */
    @Column(name = " region_code")
    private String regionCode;
    /**
     * 房主姓名1
     * 
     */
    private String owner1;
    /**
     * 房主电话1
     * 
     */
    private String phone1;
    
    
    @Transient
    private int clickcount = 0;

    public int getClickcount() {
		return clickcount;
	}

	public void setClickcount(int clickcount) {
		this.clickcount = clickcount;
	}

	/**
     * 房屋维护人的id
     */
    @Column(name = "record_user_name")
    private String recordUserName;

    /**
     * 房屋提交人的id
     */
    @Column(name = "create_user_name")
    private String createUserName;

    /**
     * 房屋提交时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 房屋介绍
     */
    private String introduce;

    /**
     * 房源类型(1:买卖房源,2出租房源)
     */
    private String type;

    /**
     * 房源状态(0:普通,1:无效)
     */
    private String state;
    
    /**
     * 房屋区号地址id
     */
    @Column(name = "region_id")
    private Long regionId;
    
    /**
     * 房屋街道id
     */
    @Column(name = "street_id")
    private Long streetId;
    
    
    /**
     * 房屋区号地址
     */
    @Column(name = "region_name")
    private String regionName;
    
    /**
     * 房屋街道地址
     */
    @Column(name = "region_name")
    private String streetName;
    
    /**
     * 房屋小区地址
     */
    @Column(name = "xiaoqu_name")
    private String xiaoquName;
    /**
     * 房屋详细地址
     */
  
    private String address;
    
    /**
     * 缩略图
     */
    private String titleimg;
    

    /**
     * 房屋钥匙状态在不在维护人手里(0:不在,1:在)
     */
    private String iskey;
    
    /**
     * 房屋信息图片(多个逗号间隔)
     */
    private List<String> imgurl;
    
    /**
     * 维护人真实姓名
     */
    @Column(name = "record_rel_name")
    private String recordrelName;
    /**
     * 创建人真实姓名
     */
    @Column(name = "create_rel_name")
    private String createrelName;
    /**
     * 钥匙持有人真实姓名
     */
    @Column(name = "key_rel_name")
    private String keyrelName;
    /**
     * 实勘人真实姓名
     */
    @Column(name = "exploration_rel_name")
    private String explorationrelName;
    /**
     * 钥匙拥有人账户名
     */
    @Column(name = "key_user_name")
    private String keyUserName;
    /**
     * 实勘录入人账户名
     */
    @Column(name = "exploration_user_name")
    private String explorationUserName;
    /**
     * 独家人账户名
     */
    @Column(name = "exclusive_user_name")
    private String exclusiveUserName;
    /**
     * 上次维护时间
     */
    @Column(name = "record_time")
    private Date recordTime;
    /**
     * 几室的实勘图片
     */
    private String shiimg;
    /**
     * 几室的实勘图片接受list
     */
    @Transient
    private List<String> shiImgList;
    /**
     * 几厅的实勘图片
     */
    private String tingimg;
    /**
     * 几厅的实勘图片接受list
     */
    @Transient
    private List<String>  tingImgList;
    /**
     * 几卫的实勘图片
     */
    private String weiimg;
    /**
     * 几卫的实勘图片接受list
     */
    @Transient
    private List<String> weiImgList;
    /**
     * 几厨的实勘图片
     */
    private String chuimg;
    /**
     * 几厨的实勘图片接受list
     */
    @Transient
    private List<String> chuImgList;
    /**
     * 户型的实勘图片
     */
    private String huxingimg;
    /**
     * 户型的实勘图片接受list
     */
    @Transient
    private List<String> huxingImgList;
    /**
     * 其他的实勘图片
     */
    private String otherimg;
    /**
     * 其他的实勘图片接受list
     */
    @Transient
    private List<String> otherImgList;
    
    /**
     * 几号楼
     */
    private String numfloor;
    /**
     * 几号单元
     */
    private String numunit;
    /**
     * 几号住户
     */
    private String numhousehold;
    /**
     * 搜索框模糊匹配条件
     */
    private String searchtext;
    
    
    /**
     * 是否是优质房源
     */
    private String isfine;
    
    /**
     * 是否是共享池状态
     */
    private String isshare;

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
     * 获取房屋编号
     *
     * @return number 房屋编号
     */
    public String getNumber() {
		return number;
	}
	/**
     * 设置房屋编号
     *
     * @return number 房屋编号
     */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
     * 获取房屋标题
     *
     * @return title - 房屋标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置房屋标题
     *
     * @param title 房屋标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取房主姓名
     *
     * @return owner - 房主姓名
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 设置房主姓名
     *
     * @param owner 房主姓名
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * 获取房主联系方式
     *
     * @return phone - 房主联系方式
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置房主联系方式
     *
     * @param phone 房主联系方式
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取房屋金额
     *
     * @return price - 房屋金额
     */
    public String getPrice() {
        return price;
    }

    /**
     * 设置房屋金额
     *
     * @param price 房屋金额
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 获取房屋楼层
     *
     * @return floor - 房屋楼层
     */
    public String getFloor() {
        return floor;
    }

    /**
     * 设置房屋楼层
     *
     * @param floor 房屋楼层
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * 获取房屋户型
     *
     * @return huxing - 房屋户型
     */
    public String getHuxing() {
        return huxing;
    }

    /**
     * 设置房屋户型
     *
     * @param huxing 房屋户型
     */
    public void setHuxing(String huxing) {
        this.huxing =huxing;
    }

    public String getHuxingshi() {
		return huxingshi;
	}

	public void setHuxingshi(String huxingshi) {
		this.huxingshi = huxingshi;
	}

	public String getHuxingting() {
		return huxingting;
	}

	public void setHuxingting(String huxingting) {
		this.huxingting = huxingting;
	}

	public String getHuxingwei() {
		return huxingwei;
	}

	public void setHuxingwei(String huxingwei) {
		this.huxingwei = huxingwei;
	}

	public String getHuxingchu() {
		return huxingchu;
	}

	public void setHuxingchu(String huxingchu) {
		this.huxingchu = huxingchu;
	}

	/**
     * 获取房屋面积
     *
     * @return areas - 房屋面积
     */
    public String getAreas() {
        return areas;
    }

    /**
     * 设置房屋面积
     *
     * @param areas 房屋面积
     */
    public void setAreas(String areas) {
        this.areas = areas;
    }

    /**
     * 获取房屋朝向
     *
     * @return chaoxiang - 房屋朝向
     */
    public String getChaoxiang() {
        return chaoxiang;
    }

    /**
     * 设置房屋朝向
     *
     * @param chaoxiang 房屋朝向
     */
    public void setChaoxiang(String chaoxiang) {
        this.chaoxiang = chaoxiang;
    }

    /**
     * 获取房屋等级(S,A,B,C)
     *
     * @return grade - 房屋等级(S,A,B,C)
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 设置房屋等级(S,A,B,C)
     *
     * @param grade 房屋等级(S,A,B,C)
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获取房屋维护人的账号名
     *
     * @return record_user_Name - 房屋维护人的账号名
     */
    public String getRecordUserName() {
        return recordUserName;
    }

    /**
     * 设置房屋维护人的账号名
     *
     * @param recordUserName 房屋维护人的账号名
     */
    public void setRecordUserName(String recordUserName) {
        this.recordUserName = recordUserName;
    }

    /**
     * 获取房屋提交人的账号名
     *
     * @return create_user_name - 房屋提交人的账号名
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * 设置房屋提交人的账号名
     *
     * @param createUserName 房屋提交人的账号名
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * 获取房屋提交时间
     *
     * @return create_time - 房屋提交时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置房屋提交时间
     *
     * @param createTime 房屋提交时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取房屋介绍
     *
     * @return introduce - 房屋介绍
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置房屋介绍
     *
     * @param introduce 房屋介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 获取房源类型(1:买卖房源,2出租房源)
     *
     * @return type - 房源类型(1:买卖房源,2出租房源)
     */
    public String getType() {
        return type;
    }

    /**
     * 设置房源类型(1:买卖房源,2出租房源)
     *
     * @param type 房源类型(1:买卖房源,2出租房源)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取房源状态(0:普通,1:无效)
     *
     * @return state - 房源状态(0:普通,1:无效)
     */
    public String getState() {
        return state;
    }

    /**
     * 获取房源状态(0:普通,1:无效)
     *
     * @param  state - 房源状态(0:普通,1:无效)
     */
    public void setState(String state) {
        this.state = state;
    }
    
	 /**
     * 房屋区号地址code
     * 
     * @return regionId 
     */
	public Long getRegionId() {
		return regionId;
	}
	 public String getIsspecial() {
		return isspecial;
	}

	public void setIsspecial(String isspecial) {
		this.isspecial = isspecial;
	}

	/**
     * 房屋区号地址code
     * 
     * @param regionId 
     */
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	 /**
     * 房屋街道id
     * 
     * @return streetId 
     */
	public Long getStreetId() {
		return streetId;
	}
	
	 /**
     * 房屋街道id
     * 
     * @param streetId 
     */
	public void setStreetId(Long streetId) {
		this.streetId = streetId;
	}

    /**
     * 获取房屋详细地址
     *
     * @return address - 房屋详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置房屋详细地址
     *
     * @param address 房屋详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取房屋钥匙状态在不在维护人手里(0:不在,1:在)
     *
     * @return iskey - 房屋钥匙状态在不在维护人手里(0:不在,1:在)
     */
    public String getIskey() {
        return iskey;
    }

    /**
     * 设置房屋钥匙状态在不在维护人手里(0:不在,1:在)
     *
     * @param iskey 房屋钥匙状态在不在维护人手里(0:不在,1:在)
     */
    public void setIskey(String iskey) {
        this.iskey = iskey;
    }



	 /**
     * 房屋信息图片
     * 
     * @return imgurl
     */
	public List<String> getImgurl() {
		return imgurl;
	}
	
	 /**
     * 房屋信息图片
     * 
     * @param imgurl 
     */
	public void setImgurl(List<String> imgurl) {
		this.imgurl = imgurl;
	}
	 /**
     * 最大楼层
     * 
     * @return maxfloor
     */
	public String getMaxfloor() {
		return maxfloor;
	}
	 /**
     * 最大楼层
     * 
     * @param maxfloor
     */
	public void setMaxfloor(String maxfloor) {
		this.maxfloor = maxfloor;
	}
	
	
	 /**
     *隐藏面积
     * 
     * @return hiddenarea
     */
	public String getHiddenarea() {
		return hiddenarea;
	}
	 /**
     * 隐藏面积
     * 
     * @param hiddenarea
     */
	public void setHiddenarea(String hiddenarea) {
		this.hiddenarea = hiddenarea;
	}
	 /**
     * 钥匙拥有者
     * 
     * @return keyUserName
     */
	public String getKeyUserName() {
		return keyUserName;
	}
	 /**
     * 钥匙拥有者
     * 
     * @param keyUserName
     */
	public void setKeyUserName(String keyUserName) {
		this.keyUserName = keyUserName;
	}
	 /**
     * 实勘录入人
     * 
     * @return explorationUserName
     */
	public String getExplorationUserName() {
		return explorationUserName;
	}
	 /**
     * 实勘录入人
     * 
     * @param explorationUserName
     */
	public void setExplorationUserName(String explorationUserName) {
		this.explorationUserName = explorationUserName;
	}
	 /**
     * 上次维护时间
     * 
     * @return recordTime
     */
	public Date getRecordTime() {
		return recordTime;
	}
	 /**
     * 上次维护时间
     * 
     * @param recordTime
     */
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	 /**
     * 几室的实勘图片
     * 
     * @return shiimg
     */
	public String getShiimg() {
		return shiimg;
	}
	 /**
     *几室的实勘图片
     * 
     * @param shiimg
     */
	public void setShiimg(String shiimg) {
		this.shiimg = shiimg;
	}
	 /**
     * 几厅的实勘图片
     * 
     * @return tingimg
     */
	public String getTingimg() {
		return tingimg;
	}
	 /**
     *几厅的实勘图片
     * 
     * @param shiimg
     */
	public void setTingimg(String tingimg) {
		this.tingimg = tingimg;
	}
	 /**
     *几卫的实勘图片
     * 
     * @return weiimg
     */
	public String getWeiimg() {
		return weiimg;
	}
	 /**
     *几卫的实勘图片
     * 
     * @param weiimg
     */
	public void setWeiimg(String weiimg) {
		this.weiimg = weiimg;
	}
	 /**
     *几厨的实勘图片
     * 
     * @return chuimg
     */
	public String getChuimg() {
		return chuimg;
	}
	 /**
     *几厨的实勘图片
     * 
     * @param chuimg
     */
	public void setChuimg(String chuimg) {
		this.chuimg = chuimg;
	}
	 /**
     *户型的实勘图片
     * 
     * @return huxingimg
     */
	public String getHuxingimg() {
		return huxingimg;
	}
	 /**
     *户型的实勘图片
     * 
     * @param huxingimg
     */
	public void setHuxingimg(String huxingimg) {
		this.huxingimg = huxingimg;
	}
	 /**
     *其他的实勘图片
     * 
     * @return otherimg
     */
	public String getOtherimg() {
		return otherimg;
	}
	 /**
     *其他的实勘图片
     * 
     * @param otherimg
     */
	public void setOtherimg(String otherimg) {
		this.otherimg = otherimg;
	}
	 /**
     *独家账户名
     * 
     * @return exclusiveUserName
     */
	public String getExclusiveUserName() {
		return exclusiveUserName;
	}
	 /**
     *独家账户名
     * 
     * @param exclusiveUserName
     */
	public void setExclusiveUserName(String exclusiveUserName) {
		this.exclusiveUserName = exclusiveUserName;
	}
	 /**
     * 几号楼
     * 
     * @return numfloor
     */
	public String getNumfloor() {
		return numfloor;
	}
	 /**
     * 几号楼
     * 
     * @param numfloor
     */
	public void setNumfloor(String numfloor) {
		this.numfloor = numfloor;
	}
	 /**
     * 单元号
     * 
     * @return numunit
     */
	public String getNumunit() {
		return numunit;
	}
	 /**
     * 单元号
     * 
     * @param numunit
     */
	public void setNumunit(String numunit) {
		this.numunit = numunit;
	}
	 /**
     * 住户门号
     * 
     * @return numhousehold
     */
	public String getNumhousehold() {
		return numhousehold;
	}
	 /**
     * 住户门号
     * 
     * @param numhousehold
     */
	public void setNumhousehold(String numhousehold) {
		this.numhousehold = numhousehold;
	}
	 /**
     * 搜索文本
     * 
     * @return searchtext
     */
	public String getSearchtext() {
		return searchtext;
	}
	 /**
     * 搜索文本
     * 
     * @param searchtext
     */
	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}
	 /**
     * 区域名称
     * 
     * @return regionName
     */
	public String getRegionName() {
		return regionName;
	}
	 /**
     * 区域名称
     * 
     * @param regionName
     */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	 /**
     * 街道名称
     * 
     * @return streetName
     */
	public String getStreetName() {
		return streetName;
	}
	 /**
     * 街道名称
     * 
     * @param streetName
     */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	 /**
     * 小区名称
     * 
     * @return xiaoquName
     */
	public String getXiaoquName() {
		return xiaoquName;
	}
	 /**
     * 小区名称
     * 
     * @param xiaoquName
     */
	public void setXiaoquName(String xiaoquName) {
		this.xiaoquName = xiaoquName;
	}
	 /**
     * 判断是否是优质房源
     * 
     * @return isfine
     */
	public String getIsfine() {
		return isfine;
	}
	 /**
     * 判断是否是优质房源
     * 
     * @param isfine
     */
	public void setIsfine(String isfine) {
		this.isfine = isfine;
	}
	 /**
     * 判断是否是共享状态的房源
     * 
     * @return isshare
     */
	public String getIsshare() {
		return isshare;
	}
	 /**
     * 判断是否是共享状态的房源
     * 
     * @param isshare
     */
	public void setIsshare(String isshare) {
		this.isshare = isshare;
	}

	public String getRecordrelName() {
		return recordrelName;
	}

	public void setRecordrelName(String recordrelName) {
		this.recordrelName = recordrelName;
	}

	public String getCreaterelName() {
		return createrelName;
	}

	public void setCreaterelName(String createrelName) {
		this.createrelName = createrelName;
	}

	public String getKeyrelName() {
		return keyrelName;
	}

	public void setKeyrelName(String keyrelName) {
		this.keyrelName = keyrelName;
	}

	public String getExplorationrelName() {
		return explorationrelName;
	}

	public void setExplorationrelName(String explorationrelName) {
		this.explorationrelName = explorationrelName;
	}

	public List<String> getShiImgList() {
		return shiImgList;
	}

	public void setShiImgList(List<String> shiImgList) {
		this.shiImgList = shiImgList;
	}

	public List<String> getTingImgList() {
		return tingImgList;
	}

	public void setTingImgList(List<String> tingImgList) {
		this.tingImgList = tingImgList;
	}

	public List<String> getWeiImgList() {
		return weiImgList;
	}

	public void setWeiImgList(List<String> weiImgList) {
		this.weiImgList = weiImgList;
	}

	public List<String> getChuImgList() {
		return chuImgList;
	}

	public void setChuImgList(List<String> chuImgList) {
		this.chuImgList = chuImgList;
	}

	public List<String> getHuxingImgList() {
		return huxingImgList;
	}

	public void setHuxingImgList(List<String> huxingImgList) {
		this.huxingImgList = huxingImgList;
	}

	public List<String> getOtherImgList() {
		return otherImgList;
	}

	public void setOtherImgList(List<String> otherImgList) {
		this.otherImgList = otherImgList;
	}

	public String getUser_ype() {
		return user_ype;
	}

	public void setUser_ype(String user_ype) {
		this.user_ype = user_ype;
	}

	public String getCasetype() {
		return casetype;
	}

	public void setCasetype(String casetype) {
		this.casetype = casetype;
	}

	public String getAddressText() {
		return addressText;
	}

	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}

	public String getTitleimg() {
		return titleimg;
	}

	public void setTitleimg(String titleimg) {
		this.titleimg = titleimg;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getOwner1() {
		return owner1;
	}

	public void setOwner1(String owner1) {
		this.owner1 = owner1;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

    
}