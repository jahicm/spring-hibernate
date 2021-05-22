package org.employee.spring.data.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "PROJECT_TABLE")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long projectNumber;

	@Column(name = "PROJECT_NAME")
	private String projectName;

	@Column(name = "PROJECT_LOCATION")
	private String projectLocation;
	
	@Version
	@Column(name = "VERSION")
	private int version;
	
	@ManyToMany
	@JoinTable(name = "employee_project",
			joinColumns = @JoinColumn(name = "PROJECTNUMBER"),
			inverseJoinColumns = @JoinColumn(name = "SSN"))
	private Set<Employee> employee = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "department_project",
			joinColumns = @JoinColumn(name = "PROJECTNUMBER"),
			inverseJoinColumns = @JoinColumn(name = "DEPTNO"))
	private Set<Department> departments = new HashSet<>();

	public Long getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(Long projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectLocation() {
		return projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	public String toString() {
		return "Project Number:" + projectNumber + " Project Name :" + projectName + " Location:" + projectLocation;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}
}
