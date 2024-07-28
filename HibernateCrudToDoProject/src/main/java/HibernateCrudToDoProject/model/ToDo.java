package HibernateCrudToDoProject.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todos")
public class ToDo  {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="title")
	private String title;
	
	
	@Column(name="description")
	private String description;
	
	@Column(name="target_date")
	private LocalDate date;
	
	@Column(name="status")
	private boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}




	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	

	public ToDo(int id, String title,  String description, LocalDate date, boolean status) {
		
		this.id = id;
		this.title = title;
	
		this.description = description;
		this.date = date;
		this.status = status;
	}

	public ToDo(String title,  String description, LocalDate date, boolean status) {
		super();
		this.title = title;
	
		this.description = description;
		this.date = date;
		this.status = status;
	}

	protected ToDo() {
		
	}
	
	
	
	
	
	
	
}
