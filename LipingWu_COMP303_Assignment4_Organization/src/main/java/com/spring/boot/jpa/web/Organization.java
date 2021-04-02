/*COMP303_Assignment4_Organization
 *Liping Wu 300958061
 4-6-2020
 * */
package com.spring.boot.jpa.web;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table (name="organization")
public class Organization {
	@Id
	@Column(name="orgid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orgId;
	
	
	@Size(min=2, max=60)	
	@Column(name="orgname")
	private String orgName;	
	
	
	@Size(min=2, max=120)	
	@Column(name="address")
	private String address;
	
	@Column(name="postal")
	//@NotBlank (message ="Put correct PostCode format A1A 2B2" )
	@Pattern( regexp ="^[A-Za-z]\\d[A-Za-z][ -]?\\d[A-Za-z]\\d$", message ="Put correct PostCode format A1A 2B2 or A1A-2B2")
	private String postalCode;	
	
	@Column(name="phone")
	@NotNull
	//@Pattern(regexp = "(\\d){3,3}\\d{3,3}\\d{4,4}", message = "The phone number must match 1112221234 format")
	@Digits(integer=10, fraction=0, message = "The phone number must match 1112221234 format")
	private String phoneNo;
	
	
	@Email(message="Please input correct email format")
	private String email;		

	@NotBlank
	private String website;
	
	public int getOrgId() {
		return orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public String getAddress() {
		return address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public String getWebsite() {
		return website;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public Organization(int orgId, String orgName, String address, String postalCode, String phoneNo,
			String email, String website) {
		super();
		this.orgId = orgId;
		this.orgName = orgName;
		this.address = address;
		this.postalCode = postalCode;
		this.phoneNo = phoneNo;
		this.email = email;
		this.website = website;
	}
	
	public Organization() {
		super();
	}
	
	@Override
	public String toString() {
		return "OrganizationController [orgId=" + orgId + ", orgName=" + orgName + ", address=" + address
				+ ", postalCode=" + postalCode + ", phoneNo=" + phoneNo + ", email=" + email + ", website=" + website
				+ "]";
	}
	
}
