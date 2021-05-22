package org.employee.spring.data.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "DEPENDENT_TABLE")
public class Dependent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dep_ssn;

	@Column(name = "FULL_NAME")
	private String name;

	@Column(name = "DATE_OF_BIRTH")
	private Date dob;

	@Column(name = "SEX")
	private String sex;

	@Column(name = "RELATIONSHIP")
	private String relationship;

	@Version
	@Column(name = "VERSION")
	private int version;
	
	@ManyToOne
	@JoinColumn(name = "ssn")
	private Employee employee;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String toString() {
		return "SSN:" + dep_ssn + " Name :" + name + " DOB:" + dob;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Long getDep_ssn() {
		return dep_ssn;
	}

	public void setDep_ssn(Long dep_ssn) {
		this.dep_ssn = dep_ssn;
	}
}
