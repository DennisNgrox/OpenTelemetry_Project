package com.example.consumer.model;

public class Response {

	private String lat;
	private String lng;
	private String name;

	
	public Response(String lat, String lng, String name) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.name = name;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
