
package com.customers.customerapp.dto;

import java.math.BigDecimal;

import com.customers.customerapp.model.Address;

public class WorkResponseDto {

    private Integer id;
    private String workType;
    private Address constructionAddress;
    private BigDecimal chargeCost;
    private BigDecimal paidCharge;
    private BigDecimal remainingCharge;
    private String moreInfo;

    public WorkResponseDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Address getConstructionAddress() {
        return constructionAddress;
    }

    public void setConstructionAddress(Address constructionAddress) {
        this.constructionAddress = constructionAddress;
    }

    public BigDecimal getChargeCost() {
        return chargeCost;
    }

    public void setChargeCost(BigDecimal chargeCost) {
        this.chargeCost = chargeCost;
    }

    public BigDecimal getPaidCharge() {
        return paidCharge;
    }

    public void setPaidCharge(BigDecimal paidCharge) {
        this.paidCharge = paidCharge;
    }

    public BigDecimal getRemainingCharge() {
        return remainingCharge;
    }

    public void setRemainingCharge(BigDecimal remainingCharge) {
        this.remainingCharge = remainingCharge;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
}
