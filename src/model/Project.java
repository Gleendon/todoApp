package model;

import java.util.Date;

public class Project {

	private int id;
	private String name;
	private String description;
	private Date createAt;
	private Date updateAt;
	
	public Project(int id, String name, String description, Date createAt, Date updateAt) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public Project() {
		this.createAt = new Date();
		this.updateAt = new Date();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
}
