package com.cubic.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bus_house_transaction_repair")
public class BusHouseTransactionRepair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 发起补交的账号
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 成交id
     */
    @Column(name = "transaction_id")
    private Long transactionId;

    /**
     * 买方服务费补交
     */
    @Column(name = "buy_intermediary_payment")
    private String buyIntermediaryPayment;

    /**
     * 卖方服务费补交
     */
    @Column(name = "sell_intermediary_payment")
    private String sellIntermediaryPayment;

    /**
     * 买方贷款补交
     */
    @Column(name = "buy_loan_payment")
    private String buyLoanPayment;

    /**
     * 补交时间
     */
    @Column(name = "create_time")
    private String createTime;

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
     * 获取发起补交的账号
     *
     * @return user_name - 发起补交的账号
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置发起补交的账号
     *
     * @param userName 发起补交的账号
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取成交id
     *
     * @return transaction_id - 成交id
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * 设置成交id
     *
     * @param transactionId 成交id
     */
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * 获取买方服务费补交
     *
     * @return buy_intermediary_payment - 买方服务费补交
     */
    public String getBuyIntermediaryPayment() {
        return buyIntermediaryPayment;
    }

    /**
     * 设置买方服务费补交
     *
     * @param buyIntermediaryPayment 买方服务费补交
     */
    public void setBuyIntermediaryPayment(String buyIntermediaryPayment) {
        this.buyIntermediaryPayment = buyIntermediaryPayment;
    }

    /**
     * 获取卖方服务费补交
     *
     * @return sell_intermediary_payment - 卖方服务费补交
     */
    public String getSellIntermediaryPayment() {
        return sellIntermediaryPayment;
    }

    /**
     * 设置卖方服务费补交
     *
     * @param sellIntermediaryPayment 卖方服务费补交
     */
    public void setSellIntermediaryPayment(String sellIntermediaryPayment) {
        this.sellIntermediaryPayment = sellIntermediaryPayment;
    }

    /**
     * 获取买方贷款补交
     *
     * @return buy_loan_payment - 买方贷款补交
     */
    public String getBuyLoanPayment() {
        return buyLoanPayment;
    }

    /**
     * 设置买方贷款补交
     *
     * @param buyLoanPayment 买方贷款补交
     */
    public void setBuyLoanPayment(String buyLoanPayment) {
        this.buyLoanPayment = buyLoanPayment;
    }

    /**
     * 获取补交时间
     *
     * @return create_time - 补交时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置补交时间
     *
     * @param createTime 补交时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}