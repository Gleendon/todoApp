package model;

import java.util.Date;

public class Task {

	private int id;
	private int idProject;
	private String name;
	private String description;
	private String notes;
	private boolean isCompleted;
	private Date deadline;
	private Date createAt;
	private Date updateAt;
	
	public Task(int id, int idProject, String name, String description, String notes, boolean isCompleted,
			Date deadline, Date createAt, Date updateAt) {
		super();
		this.id = id;
		this.idProject = idProject;
		this.name = name;
		this.description = description;
		this.notes = notes;
		this.isCompleted = isCompleted;
		this.deadline = deadline;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}
	
	public Task() {
		this.createAt = new Date();
		this.updateAt = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
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
		return "task [id=" + id + ", idProject=" + idProject + ", name=" + name + ", description=" + description
				+ ", notes=" + notes + ", isCompleted=" + isCompleted + ", deadline=" + deadline + ", createAt="
				+ createAt + ", updateAt=" + updateAt + "]";
	}
	
	
	
	
}
