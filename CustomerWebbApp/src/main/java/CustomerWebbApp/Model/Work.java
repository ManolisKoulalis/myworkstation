package CustomerWebbApp.Model;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


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
		@AttributeOverride (name="name", column=@Column(name="Construction_Adrress_Name")),
		@AttributeOverride (name="postcode", column=@Column(name="Construction_Postcode"))
	})
	private Address constructionAddress;
	
	@Column(name="charge_cost")
	private double chargeCost;
	
	@Column(name="paid_amount")
	private double paidCharge;
	
	@Column(name="more_information")
	private String moreInfo;

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

	public double getChargeCost() {
		return chargeCost;
	}

	public void setChargeCost(double chargeCost) {
		this.chargeCost = chargeCost;
	}

	public double getPaidCharge() {
		return paidCharge;
	}

	public void setPaidCharge(double paidCharge) {
		this.paidCharge = paidCharge;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	public Work(int id, String workType, Address constructionAddress, double chargeCost, double paidCharge,
			String moreInfo) {
	
		this.id = id;
		this.workType = workType;
		this.constructionAddress = constructionAddress;
		this.chargeCost = chargeCost;
		this.paidCharge = paidCharge;
		this.moreInfo = moreInfo;
	}

	public Work(String workType, Address constructionAddress, double chargeCost, double paidCharge, String moreInfo) {
		
		this.workType = workType;
		this.constructionAddress = constructionAddress;
		this.chargeCost = chargeCost;
		this.paidCharge = paidCharge;
		this.moreInfo = moreInfo;
	}

	public Work() {
		
	}
	
	
	
	
	
	

}
