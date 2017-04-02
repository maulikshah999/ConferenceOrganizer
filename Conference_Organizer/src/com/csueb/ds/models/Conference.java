package com.csueb.ds.models;

import java.io.Serializable;

public class Conference implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String conf_ID = "", conf_name = "", conf_details = "", conf_title = "", conf_location = "", conf_time = "",
			conf_date = "", guest_speaker = "";

	public String getConf_ID() {
		return conf_ID;
	}

	public void setConf_ID(String conf_ID) {
		this.conf_ID = conf_ID;
	}

	public String getConf_name() {
		return conf_name;
	}

	public void setConf_name(String conf_name) {
		this.conf_name = conf_name;
	}

	public String getConf_details() {
		return conf_details;
	}

	public void setConf_details(String conf_details) {
		this.conf_details = conf_details;
	}

	public String getConf_title() {
		return conf_title;
	}

	public void setConf_title(String conf_title) {
		this.conf_title = conf_title;
	}

	public String getConf_location() {
		return conf_location;
	}

	public void setConf_location(String conf_location) {
		this.conf_location = conf_location;
	}

	public String getConf_time() {
		return conf_time;
	}

	public void setConf_time(String conf_time) {
		this.conf_time = conf_time;
	}

	public String getConf_date() {
		return conf_date;
	}

	public void setConf_date(String conf_date) {
		this.conf_date = conf_date;
	}

	public String getGuest_speaker() {
		return guest_speaker;
	}

	public void setGuest_speaker(String guest_speaker) {
		this.guest_speaker = guest_speaker;
	}

}
