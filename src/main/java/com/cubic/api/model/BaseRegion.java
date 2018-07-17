package com.cubic.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "region_code" })
public class BaseRegion {
	private String value;
	private String label;
	transient String region_code;
	List<BaseStreet> children;
	
	public String getRegion_code() {
		return region_code;
	}
	public void setRegion_code(String region_code) {
		this.region_code = region_code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<BaseStreet> getChildren() {
		return children;
	}
	public void setChildren(List<BaseStreet> children) {
		this.children = children;
	}
	
}
