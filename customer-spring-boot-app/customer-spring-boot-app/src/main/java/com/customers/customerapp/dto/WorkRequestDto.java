
package com.customers.customerapp.dto;

import java.math.BigDecimal;

import com.customers.customerapp.model.Address;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class WorkRequestDto {

    @NotBlank(message = "Το είδος εργασίας είναι υποχρεωτικό")
    @Size(max = 100, message = "Το είδος εργασίας δεν μπορεί να ξεπερνά τους 100 χαρακτήρες")
    private String workType;

    @NotNull(message = "Η διεύθυνση κατασκευής είναι υποχρεωτική")
    @Valid
    private Address constructionAddress;

    @NotNull(message = "Το κόστος χρέωσης είναι υποχρεωτικό")
    @DecimalMin(value = "0.00", message = "Το κόστος χρέωσης δεν μπορεί να είναι αρνητικό")
    @Digits(integer = 10, fraction = 2, message = "Το κόστος πρέπει να έχει έως 10 ακέραια και 2 δεκαδικά ψηφία")
    private BigDecimal chargeCost;

    @NotNull(message = "Το πληρωμένο ποσό είναι υποχρεωτικό")
    @DecimalMin(value = "0.00", message = "Το πληρωμένο ποσό δεν μπορεί να είναι αρνητικό")
    @Digits(integer = 10, fraction = 2, message = "Το πληρωμένο ποσό πρέπει να έχει έως 10 ακέραια και 2 δεκαδικά ψηφία")
    private BigDecimal paidCharge;

    @Size(max = 1000, message = "Οι επιπλέον πληροφορίες δεν μπορούν να ξεπερνούν τους 1000 χαρακτήρες")
    private String moreInfo;

    public WorkRequestDto() {
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

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
}

