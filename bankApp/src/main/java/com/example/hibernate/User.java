package com.example.hibernate;
// Generated Mar 11, 2020, 5:05:15 PM by Hibernate Tools 5.4.7.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private int userId;
	private String username;
	private String password;
	private int status;
	private Integer incorrectAttempts;
	private Date createdDate;
	private Date modifiedDate;
	private String userType;
	private Set accountsForUserId = new HashSet(0);
	private Set accountsForApprover = new HashSet(0);
	private Set loginHistories = new HashSet(0);
	private Set requestsForRequestAssignedTo = new HashSet(0);
	private Set transactions = new HashSet(0);
	private Set requestsForRequestedBy = new HashSet(0);
	private Set appointmentsForAppointmentUserId = new HashSet(0);
	private Set appointmentsForAssignedToUserId = new HashSet(0);
	private Set userDetailses = new HashSet(0);

	public User() {
	}

	public User(int userId, String username, String password, int status) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public User(int userId, String username, String password, int status, Integer incorrectAttempts, Date createdDate,
			Date modifiedDate, String userType, Set accountsForUserId, Set accountsForApprover, Set loginHistories,
			Set requestsForRequestAssignedTo, Set transactions, Set requestsForRequestedBy,
			Set appointmentsForAppointmentUserId, Set appointmentsForAssignedToUserId, Set userDetailses) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.status = status;
		this.incorrectAttempts = incorrectAttempts;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.userType = userType;
		this.accountsForUserId = accountsForUserId;
		this.accountsForApprover = accountsForApprover;
		this.loginHistories = loginHistories;
		this.requestsForRequestAssignedTo = requestsForRequestAssignedTo;
		this.transactions = transactions;
		this.requestsForRequestedBy = requestsForRequestedBy;
		this.appointmentsForAppointmentUserId = appointmentsForAppointmentUserId;
		this.appointmentsForAssignedToUserId = appointmentsForAssignedToUserId;
		this.userDetailses = userDetailses;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getIncorrectAttempts() {
		return this.incorrectAttempts;
	}

	public void setIncorrectAttempts(Integer incorrectAttempts) {
		this.incorrectAttempts = incorrectAttempts;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Set getAccountsForUserId() {
		return this.accountsForUserId;
	}

	public void setAccountsForUserId(Set accountsForUserId) {
		this.accountsForUserId = accountsForUserId;
	}

	public Set getAccountsForApprover() {
		return this.accountsForApprover;
	}

	public void setAccountsForApprover(Set accountsForApprover) {
		this.accountsForApprover = accountsForApprover;
	}

	public Set getLoginHistories() {
		return this.loginHistories;
	}

	public void setLoginHistories(Set loginHistories) {
		this.loginHistories = loginHistories;
	}

	public Set getRequestsForRequestAssignedTo() {
		return this.requestsForRequestAssignedTo;
	}

	public void setRequestsForRequestAssignedTo(Set requestsForRequestAssignedTo) {
		this.requestsForRequestAssignedTo = requestsForRequestAssignedTo;
	}

	public Set getTransactions() {
		return this.transactions;
	}

	public void setTransactions(Set transactions) {
		this.transactions = transactions;
	}

	public Set getRequestsForRequestedBy() {
		return this.requestsForRequestedBy;
	}

	public void setRequestsForRequestedBy(Set requestsForRequestedBy) {
		this.requestsForRequestedBy = requestsForRequestedBy;
	}

	public Set getAppointmentsForAppointmentUserId() {
		return this.appointmentsForAppointmentUserId;
	}

	public void setAppointmentsForAppointmentUserId(Set appointmentsForAppointmentUserId) {
		this.appointmentsForAppointmentUserId = appointmentsForAppointmentUserId;
	}

	public Set getAppointmentsForAssignedToUserId() {
		return this.appointmentsForAssignedToUserId;
	}

	public void setAppointmentsForAssignedToUserId(Set appointmentsForAssignedToUserId) {
		this.appointmentsForAssignedToUserId = appointmentsForAssignedToUserId;
	}

	public Set getUserDetailses() {
		return this.userDetailses;
	}

	public void setUserDetailses(Set userDetailses) {
		this.userDetailses = userDetailses;
	}

}
