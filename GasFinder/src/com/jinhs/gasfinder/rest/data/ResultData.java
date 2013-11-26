package com.jinhs.gasfinder.rest.data;

import java.util.List;

public class ResultData {
	private Geometry geometry;
	private String icon;
	private String id;
	private String name;
	private OpenHour opening_hours;
	private String reference;
	private List<String> types;
	public Geometry getGeometry() {
		return geometry;
	}
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public OpenHour getOpening_hours() {
		return opening_hours;
	}
	public void setOpening_hours(OpenHour opening_hours) {
		this.opening_hours = opening_hours;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public String getVicinity() {
		return vicinity;
	}
	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}
	private String vicinity;
}
