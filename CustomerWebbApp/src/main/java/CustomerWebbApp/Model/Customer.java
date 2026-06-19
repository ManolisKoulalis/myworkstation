package CustomerWebbApp.Model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="customers")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
	private String name;

	@Column(name="surname")
	private String surname;
	
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="birthdate")
	private LocalDate birthdate;
	
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride (name="name", column=@Column(name="Work_Adrress_Name")),
		@AttributeOverride (name="postcode", column=@Column(name="Work_Postcode"))
	})
	private Address workAddress;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride (name="name", column=@Column(name="Home_Adrress_Name")),
		@AttributeOverride (name="postcode", column=@Column(name="Home_Postcode"))
	})
	private Address homeAddress;
	
	@Column(name="more_information")
	private String moreInfo;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@OneToMany( cascade = CascadeType.ALL,
    orphanRemoval = true)
	@JoinTable(name="Customer_Work", joinColumns=@JoinColumn(name="Customer_Id"),
				inverseJoinColumns=@JoinColumn(name="Work_Id")	)
	private List<Work> worklist= new ArrayList<Work>(); //Αρχικοποιειται κατευθειαν στο πεδιο του ετσι ωστε αν ξεχασω σε καποιον κατασκευαστη να το περασω να μην πεταξει nullpointerexception αν παω να κανω το addWork
	
	public void addWork(Work awork) {
		worklist.add(awork);
	}
	

	public Customer(int id, String name, String surname, String gender, LocalDate birthdate, Address workAddress,
			Address homeAddress, String moreInfo, String username, String password) {
		
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.workAddress = workAddress;
		this.homeAddress = homeAddress;
		this.moreInfo = moreInfo;
		this.username = username;
		this.password = password;
		
	}

	public Customer(String name, String surname, String gender, LocalDate birthdate, Address workAddress,
			Address homeAddress, String moreInfo, String username, String password) {
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.workAddress = workAddress;
		this.homeAddress = homeAddress;
		this.moreInfo = moreInfo;
		this.username = username;
		this.password = password;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Address getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(Address workAddress) {
		this.workAddress = workAddress;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer() {
	
	}

	public List<Work> getWorklist() {
		return worklist;
	}

	public void setWorklist(List<Work> worklist) {
		this.worklist = worklist;
	}
	
	

	
}
