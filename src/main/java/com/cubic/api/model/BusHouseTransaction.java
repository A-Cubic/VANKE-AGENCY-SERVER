package com.cubic.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bus_house_transaction")
public class BusHouseTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 房源id
     */
    @Column(name = "house_id")
    private Long houseId;

    /**
     * 客源id
     */
    @Column(name = "guest_id")
    private Long guestId;

    /**
     * 创建人
     */
    @Column(name = "create_user_name")
    private String createUserName;
    /**
     * 成交编号
     */
    private String number;
    /**
     * 合同编号
     */
    private String  contractnumber;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 买方服务费
     */
    @Column(name = "buy_intermediary_price")
    private String buyIntermediaryPrice;

    /**
     * 卖方服务费
     */
    @Column(name = "sell_intermediary_price")
    private String sellIntermediaryPrice;

    /**
     * 买方贷款费
     */
    @Column(name = "buy_loan_price")
    private String buyLoanPrice;

    /**
     * 买方实缴服务费
     */
    @Column(name = "buy_intermediary_payment")
    private String buyIntermediaryPayment;

    /**
     * 卖方实缴服务费
     */
    @Column(name = "sell_intermediary_payment")
    private String sellIntermediaryPayment;

    /**
     * 买房实缴贷款
     */
    @Column(name = "buy_loan_payment")
    private String buyLoanPayment;

    /**
     * 买房服务费差价
     */
    @Column(name = "buy_intermediary_lack")
    private String buyIntermediaryLack;

    /**
     * 卖方服务费差价
     */
    @Column(name = "sell_intermediary_lack")
    private String sellIntermediaryLack;

    /**
     * 买方贷款差价
     */
    @Column(name = "buy_loan_lack")
    private String buyLoanLack;

    /**
     * 应缴费是否结清(0:未结清,1:已结清)
     */
    private String type;

    /**
     * 合同图片
     */
    @Column(name = "contract_img")
    private String contractImg;

    /**
     * 产权证图片
     */
    @Column(name = "house_prove_img")
    private String houseProveImg;

    /**
     * 贷款合同图片
     */
    @Column(name = "loan_contract_img")
    private String loanContractImg;

    /**
     * 身份证图片
     */
    @Column(name = "identity_prove_img")
    private String identityProveImg;

    /**
     * 收据图片
     */
    @Column(name = "receipt_img")
    private String receiptImg;

    /**
     * 补充协议图片
     */
    @Column(name = "agreement_img")
    private String agreementImg;

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
     * 获取创建人
     *
     * @return create_user_name - 创建人
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * 设置创建人
     *
     * @param createUserName 创建人
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
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
     * 获取买方服务费
     *
     * @return buy_intermediary_price - 买方服务费
     */
    public String getBuyIntermediaryPrice() {
        return buyIntermediaryPrice;
    }

    /**
     * 设置买方服务费
     *
     * @param buyIntermediaryPrice 买方服务费
     */
    public void setBuyIntermediaryPrice(String buyIntermediaryPrice) {
        this.buyIntermediaryPrice = buyIntermediaryPrice;
    }

    /**
     * 获取卖方服务费
     *
     * @return sell_intermediary_price - 卖方服务费
     */
    public String getSellIntermediaryPrice() {
        return sellIntermediaryPrice;
    }

    /**
     * 设置卖方服务费
     *
     * @param sellIntermediaryPrice 卖方服务费
     */
    public void setSellIntermediaryPrice(String sellIntermediaryPrice) {
        this.sellIntermediaryPrice = sellIntermediaryPrice;
    }

    /**
     * 获取买方贷款费
     *
     * @return buy_loan_price - 买方贷款费
     */
    public String getBuyLoanPrice() {
        return buyLoanPrice;
    }

    /**
     * 设置买方贷款费
     *
     * @param buyLoanPrice 买方贷款费
     */
    public void setBuyLoanPrice(String buyLoanPrice) {
        this.buyLoanPrice = buyLoanPrice;
    }

    /**
     * 获取买方实缴服务费
     *
     * @return buy_intermediary_payment - 买方实缴服务费
     */
    public String getBuyIntermediaryPayment() {
        return buyIntermediaryPayment;
    }

    /**
     * 设置买方实缴服务费
     *
     * @param buyIntermediaryPayment 买方实缴服务费
     */
    public void setBuyIntermediaryPayment(String buyIntermediaryPayment) {
        this.buyIntermediaryPayment = buyIntermediaryPayment;
    }

    /**
     * 获取卖方实缴服务费
     *
     * @return sell_intermediary_payment - 卖方实缴服务费
     */
    public String getSellIntermediaryPayment() {
        return sellIntermediaryPayment;
    }

    /**
     * 设置卖方实缴服务费
     *
     * @param sellIntermediaryPayment 卖方实缴服务费
     */
    public void setSellIntermediaryPayment(String sellIntermediaryPayment) {
        this.sellIntermediaryPayment = sellIntermediaryPayment;
    }

    /**
     * 获取买房实缴贷款
     *
     * @return buy_loan_payment - 买房实缴贷款
     */
    public String getBuyLoanPayment() {
        return buyLoanPayment;
    }

    /**
     * 设置买房实缴贷款
     *
     * @param buyLoanPayment 买房实缴贷款
     */
    public void setBuyLoanPayment(String buyLoanPayment) {
        this.buyLoanPayment = buyLoanPayment;
    }

    /**
     * 获取买房服务费差价
     *
     * @return buy_intermediary_lack - 买房服务费差价
     */
    public String getBuyIntermediaryLack() {
        return buyIntermediaryLack;
    }

    /**
     * 设置买房服务费差价
     *
     * @param buyIntermediaryLack 买房服务费差价
     */
    public void setBuyIntermediaryLack(String buyIntermediaryLack) {
        this.buyIntermediaryLack = buyIntermediaryLack;
    }

    /**
     * 获取卖方服务费差价
     *
     * @return sell_intermediary_lack - 卖方服务费差价
     */
    public String getSellIntermediaryLack() {
        return sellIntermediaryLack;
    }

    /**
     * 设置卖方服务费差价
     *
     * @param sellIntermediaryLack 卖方服务费差价
     */
    public void setSellIntermediaryLack(String sellIntermediaryLack) {
        this.sellIntermediaryLack = sellIntermediaryLack;
    }

    /**
     * 获取买方贷款差价
     *
     * @return buy_loan_lack - 买方贷款差价
     */
    public String getBuyLoanLack() {
        return buyLoanLack;
    }

    /**
     * 设置买方贷款差价
     *
     * @param buyLoanLack 买方贷款差价
     */
    public void setBuyLoanLack(String buyLoanLack) {
        this.buyLoanLack = buyLoanLack;
    }

    /**
     * 获取应缴费是否结清(0:未结清,1:已结清)
     *
     * @return type - 应缴费是否结清(0:未结清,1:已结清)
     */
    public String getType() {
        return type;
    }

    /**
     * 设置应缴费是否结清(0:未结清,1:已结清)
     *
     * @param type 应缴费是否结清(0:未结清,1:已结清)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取合同图片
     *
     * @return contract_img - 合同图片
     */
    public String getContractImg() {
        return contractImg;
    }

    /**
     * 设置合同图片
     *
     * @param contractImg 合同图片
     */
    public void setContractImg(String contractImg) {
        this.contractImg = contractImg;
    }

    /**
     * 获取产权证图片
     *
     * @return house_prove_img - 产权证图片
     */
    public String getHouseProveImg() {
        return houseProveImg;
    }

    /**
     * 设置产权证图片
     *
     * @param houseProveImg 产权证图片
     */
    public void setHouseProveImg(String houseProveImg) {
        this.houseProveImg = houseProveImg;
    }

    /**
     * 获取贷款合同图片
     *
     * @return loan_contract_img - 贷款合同图片
     */
    public String getLoanContractImg() {
        return loanContractImg;
    }

    /**
     * 设置贷款合同图片
     *
     * @param loanContractImg 贷款合同图片
     */
    public void setLoanContractImg(String loanContractImg) {
        this.loanContractImg = loanContractImg;
    }

    /**
     * 获取身份证图片
     *
     * @return identity_prove_img - 身份证图片
     */
    public String getIdentityProveImg() {
        return identityProveImg;
    }

    /**
     * 设置身份证图片
     *
     * @param identityProveImg 身份证图片
     */
    public void setIdentityProveImg(String identityProveImg) {
        this.identityProveImg = identityProveImg;
    }

    /**
     * 获取收据图片
     *
     * @return receipt_img - 收据图片
     */
    public String getReceiptImg() {
        return receiptImg;
    }

    /**
     * 设置收据图片
     *
     * @param receiptImg 收据图片
     */
    public void setReceiptImg(String receiptImg) {
        this.receiptImg = receiptImg;
    }

    /**
     * 获取补充协议图片
     *
     * @return agreement_img - 补充协议图片
     */
    public String getAgreementImg() {
        return agreementImg;
    }

    /**
     * 设置补充协议图片
     *
     * @param agreementImg 补充协议图片
     */
    public void setAgreementImg(String agreementImg) {
        this.agreementImg = agreementImg;
    }

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getContractnumber() {
		return contractnumber;
	}

	public void setContractnumber(String contractnumber) {
		this.contractnumber = contractnumber;
	}
    
    
}