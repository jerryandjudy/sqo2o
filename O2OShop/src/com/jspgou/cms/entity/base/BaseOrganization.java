package com.jspgou.cms.entity.base;

import java.io.Serializable;

import com.jspgou.cms.entity.Organization;

public abstract class BaseOrganization implements Serializable{
	private static final long serialVersionUID = 1L;
	// primary key
	private java.lang.Long id;
	private String description;
	private String name;
	private Integer priority;
	private Organization parent;
	public static String PROP_PRIORITY = "priority";
	
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Organization getParent() {
		return parent;
	}
	public void setParent(Organization parent) {
		this.parent = parent;
	}
		// constructors
		public BaseOrganization () {
			initialize();
		}
		protected void initialize () {}

}
