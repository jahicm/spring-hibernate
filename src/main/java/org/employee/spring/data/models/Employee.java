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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "EMPLOYEE_TABLE")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ssn;

	@Column(name = "FULL_NAME")
	private String name;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "SALARY")
	private double salary;

	@Column(name = "SUPERVISOR_NAME")
	private String supervisor;

	@Column(name = "SEX")
	private String sex;

	@Column(name = "DATE_OF_BIRTH")
	private Date dob;

	@Column(name = "WEEKLY_HOURS_WORKED")
	private double weeklyHours;

	@Version
	@Column(name = "VERSION")
	private int version;
	
	@ManyToOne
	@JoinColumn(name = "deptNo")
	private Department department;
	
	@OneToMany(mappedBy = "employee", cascade=CascadeType.ALL,fetch = FetchType.EAGER,
			orphanRemoval=true)
	private Set<Dependent> dependents = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "employee_project",
			joinColumns = @JoinColumn(name = "SSN"),
			inverseJoinColumns = @JoinColumn(name = "PROJECTNUMBER"))
	private Set<Project> projects = new HashSet<>();
	
	public Long getSsn() {
		return ssn;
	}

	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public double getWeeklyHours() {
		return weeklyHours;
	}

	public void setWeeklyHours(double weeklyHours) {
		this.weeklyHours = weeklyHours;
	}

	public String toString() {
		return "Employee - Id: " + ssn + ", Name: " + name + "  Date of Birth :" + dob;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Dependent> getDependents() {
		return dependents;
	}

	public void setDependents(Set<Dependent> dependents) {
		this.dependents = dependents;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
}
