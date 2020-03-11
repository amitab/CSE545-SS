package com.example.hibernate;
// Generated Mar 11, 2020, 5:05:15 PM by Hibernate Tools 5.4.7.Final

import java.util.Date;

/**
 * LoginHistoryId generated by hbm2java
 */
public class LoginHistoryId implements java.io.Serializable {

	private int userId;
	private Date loggedIn;
	private Date loggedOut;
	private String ipAddress;
	private String deviceType;

	public LoginHistoryId() {
	}

	public LoginHistoryId(int userId, Date loggedIn, String ipAddress, String deviceType) {
		this.userId = userId;
		this.loggedIn = loggedIn;
		this.ipAddress = ipAddress;
		this.deviceType = deviceType;
	}

	public LoginHistoryId(int userId, Date loggedIn, Date loggedOut, String ipAddress, String deviceType) {
		this.userId = userId;
		this.loggedIn = loggedIn;
		this.loggedOut = loggedOut;
		this.ipAddress = ipAddress;
		this.deviceType = deviceType;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getLoggedIn() {
		return this.loggedIn;
	}

	public void setLoggedIn(Date loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Date getLoggedOut() {
		return this.loggedOut;
	}

	public void setLoggedOut(Date loggedOut) {
		this.loggedOut = loggedOut;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LoginHistoryId))
			return false;
		LoginHistoryId castOther = (LoginHistoryId) other;

		return (this.getUserId() == castOther.getUserId())
				&& ((this.getLoggedIn() == castOther.getLoggedIn()) || (this.getLoggedIn() != null
						&& castOther.getLoggedIn() != null && this.getLoggedIn().equals(castOther.getLoggedIn())))
				&& ((this.getLoggedOut() == castOther.getLoggedOut()) || (this.getLoggedOut() != null
						&& castOther.getLoggedOut() != null && this.getLoggedOut().equals(castOther.getLoggedOut())))
				&& ((this.getIpAddress() == castOther.getIpAddress()) || (this.getIpAddress() != null
						&& castOther.getIpAddress() != null && this.getIpAddress().equals(castOther.getIpAddress())))
				&& ((this.getDeviceType() == castOther.getDeviceType())
						|| (this.getDeviceType() != null && castOther.getDeviceType() != null
								&& this.getDeviceType().equals(castOther.getDeviceType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getUserId();
		result = 37 * result + (getLoggedIn() == null ? 0 : this.getLoggedIn().hashCode());
		result = 37 * result + (getLoggedOut() == null ? 0 : this.getLoggedOut().hashCode());
		result = 37 * result + (getIpAddress() == null ? 0 : this.getIpAddress().hashCode());
		result = 37 * result + (getDeviceType() == null ? 0 : this.getDeviceType().hashCode());
		return result;
	}

}
