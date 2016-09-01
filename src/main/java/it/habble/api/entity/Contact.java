package it.habble.api.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"@id"})
public class Contact {
	private Integer id;
	private String firstName, surname;
	private String role;
	private String extraInfo;
	private Department department;
	private String directPhone, internalPhone, mobilePhone;
	private String fixedLineCost;
	private String email;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date from;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date to;
	
	public Date getFrom() {
		return this.from;
	}

	public Contact setFrom(Date from) {
		this.from = from;
		return this;
	}

	public Date getTo() {
		return this.to;
	}

	public Contact setTo(Date to) {
		this.to = to;
		return this;
	}

	public Contact() {}
	
	public Contact(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public Contact setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public Contact setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getSurname() {
		return this.surname;
	}

	public Contact setSurname(String surname) {
		this.surname = surname;
		return this;
	}

	public String getRole() {
		return this.role;
	}

	public Contact setRole(String role) {
		this.role = role;
		return this;
	}

	public String getExtraInfo() {
		return this.extraInfo;
	}

	public Contact setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
		return this;
	}

	public Department getDepartment() {
		return this.department;
	}

	public Contact setDepartment(Department department) {
		this.department = department;
		return this;
	}

	public String getDirectPhone() {
		return this.directPhone;
	}

	public Contact setDirectPhone(String directPhone) {
		this.directPhone = directPhone;
		return this;
	}

	public String getInternalPhone() {
		return this.internalPhone;
	}

	public Contact setInternalPhone(String internalPhone) {
		this.internalPhone = internalPhone;
		return this;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public Contact setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	public String getFixedLineCost() {
		return this.fixedLineCost;
	}

	public Contact setFixedLineCost(String fixedLineCost) {
		this.fixedLineCost = fixedLineCost;
		return this;
	}

	public String getEmail() {
		return this.email;
	}

	public Contact setEmail(String email) {
		this.email = email;
		return this;
	}
	
	@Override
	public Contact clone() {
		return new Contact(this.id)
					.setDepartment(this.department)
					.setDirectPhone(this.directPhone)
					.setEmail(this.email)
					.setExtraInfo(this.extraInfo)
					.setFirstName(this.firstName)
					.setFixedLineCost(this.fixedLineCost)
					.setInternalPhone(this.internalPhone)
					.setMobilePhone(this.mobilePhone)
					.setRole(this.role)
					.setSurname(this.surname)
					.setFrom(this.from)
					.setTo(this.to);
	}
}
