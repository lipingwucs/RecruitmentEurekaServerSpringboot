/*COMP303_Assignment4_Category
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name="category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jobcatid")
	private int jobCatId;
	
	@NotBlank(message="category code can't be blank")
	//@Size(min=2, max=30)
	@Column(name = "catcode")
	private String catCode;
	
	@NotBlank (message="category name can't be blank")
	//@Size(min=2, max=30)	
	@Column(name = "catname")
	private String catName;
	
	//@NotBlank (message="category description can't be blank")
	@Size(min=10, max=120)
	@Column(name = "catdesc")
	private String catDesc;
	
	public int getJobCatId() {
		return jobCatId;
	}
	public String getCatCode() {
		return catCode;
	}
	public String getCatName() {
		return catName;
	}
	public String getCatDesc() {
		return catDesc;
	}
	public void setJobCatId(int jobCatId) {
		this.jobCatId = jobCatId;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}
	
	public Category(int jobCatId, String catCode, String catName, String catDesc) {
		super();
		this.jobCatId = jobCatId;
		this.catCode = catCode;
		this.catName = catName;
		this.catDesc = catDesc;
	}
	
	public Category() {
		super();
	}
	
	@Override
	public String toString() {
		return "Category [jobCatId=" + jobCatId + ", catCode=" + catCode + ", catName=" + catName + ", catDesc="
				+ catDesc + "]";
	}
	
	
	
	
	

}
