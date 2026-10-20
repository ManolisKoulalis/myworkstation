package com.customers.customerapp.model;

import java.math.BigDecimal;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


	@Entity
	@Table(name="works")
	public class Work {
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		
		@Column(name="type_of_construction")
		private String workType;
		
		
		@Embedded
		@AttributeOverrides({
			@AttributeOverride (name="name", column=@Column(name="Construction_Address_Name")),
			@AttributeOverride (name="postcode", column=@Column(name="Construction_Postcode"))
		})
		private Address constructionAddress;
		
		/*
		* Για χρηματικά ποσά προτιμούμε BigDecimal αντί για double, 
		επειδή αποφεύγουμε σφάλματα αναπαράστασης δεκαδικών αριθμών.
		*ορίζει ότι η στήλη θα υποστηρίζει έως 12 ψηφία συνολικά, από τα οποία τα 2 είναι δεκαδικά.
		*/
		@Column(name="charge_cost",precision = 12, scale = 2)
		private BigDecimal chargeCost;
		
		@Column(name="paid_amount",precision = 12, scale = 2)
		private BigDecimal paidCharge;
		
		@Column(name="more_information")
		private String moreInfo;

		
		//getters and setters
		public int getId() {
			return id;
		}

		public void setId(int id) {
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

		public String getMoreInfo() {
			return moreInfo;
		}

		public void setMoreInfo(String moreInfo) {
			this.moreInfo = moreInfo;
		}

		
		//Constructors
		public Work(int id, String workType, Address constructionAddress, BigDecimal chargeCost, BigDecimal paidCharge,
				String moreInfo) {
		
			this.id = id;
			this.workType = workType;
			this.constructionAddress = constructionAddress;
			this.chargeCost = chargeCost;
			this.paidCharge = paidCharge;
			this.moreInfo = moreInfo;
		}

		public Work(String workType, Address constructionAddress, BigDecimal chargeCost, BigDecimal paidCharge, String moreInfo) {
			
			this.workType = workType;
			this.constructionAddress = constructionAddress;
			this.chargeCost = chargeCost;
			this.paidCharge = paidCharge;
			this.moreInfo = moreInfo;
		}

		public Work() {
			
		}
		
		@Transient
		public BigDecimal getRemainingCharge() {
		   
			return chargeCost.subtract(paidCharge);
		}
		
		
	}
		


