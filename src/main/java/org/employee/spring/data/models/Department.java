package org.employee.spring.data.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "DEPARTMENT_TABLE")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long deptNo;

	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;

	@Column(name = "DEPARTMENT_MANAGER")
	private String departmentManager;

	@Column(name = "MANAGER_START_DATE")
	private Date managerStartDate;

	@Column(name = "LOCATION")
	private String departmentLocation;

	@Version
	@Column(name = "VERSION")
	private int version;
	
	@OneToMany(mappedBy = "department", cascade=CascadeType.ALL,fetch = FetchType.EAGER,
			orphanRemoval=true)
	private Set<Employee> employees = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "department_project",
			joinColumns = @JoinColumn(name = "DEPTNO"),
			inverseJoinColumns = @JoinColumn(name = "PROJECTNUMBER"))
	private Set<Project> projects = new HashSet<>();
	
	public Long getDeptNo() {
		return deptNo;
	}
	
	
	
	public void setDeptNo(Long deptNo) {
		this.deptNo = deptNo;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentManager() {
		return departmentManager;
	}

	public void setDepartmentManager(String departmentManager) {
		this.departmentManager = departmentManager;
	}

	public Date getManagerStartDate() {
		return managerStartDate;
	}

	public void setManagerStartDate(Date managerStartDate) {
		this.managerStartDate = managerStartDate;
	}

	public String getDepartmentLocation() {
		return departmentLocation;
	}

	public void setDepartmentLocation(String departmentLocation) {
		this.departmentLocation = departmentLocation;
	}

	public String toString() {
		return "Department Number:"+deptNo+" Department Name :"+departmentName+" Department Location:"+departmentLocation;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}



	public Set<Project> getProjects() {
		return projects;
	}



	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	
}
