package com.example.hibernate;
// Generated Mar 13, 2020, 2:30:54 PM by Hibernate Tools 5.4.7.Final

import java.math.BigDecimal;
import java.util.Date;

/**
 * SavingsAccountId generated by hbm2java
 */
public class SavingsAccountId implements java.io.Serializable {

	private int accountId;
	private int userId;
	private String accountNumber;
	private String accountType;
	private BigDecimal currentBalance;
	private Date createdDate;
	private boolean approvalStatus;
	private BigDecimal interest;
	private Date approvalDate;
	private Integer approver;

	public SavingsAccountId() {
	}

	public SavingsAccountId(int accountId, int userId, String accountNumber, String accountType, Date createdDate,
			boolean approvalStatus) {
		this.accountId = accountId;
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.createdDate = createdDate;
		this.approvalStatus = approvalStatus;
	}

	public SavingsAccountId(int accountId, int userId, String accountNumber, String accountType,
			BigDecimal currentBalance, Date createdDate, boolean approvalStatus, BigDecimal interest, Date approvalDate,
			Integer approver) {
		this.accountId = accountId;
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.currentBalance = currentBalance;
		this.createdDate = createdDate;
		this.approvalStatus = approvalStatus;
		this.interest = interest;
		this.approvalDate = approvalDate;
		this.approver = approver;
	}

	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getCurrentBalance() {
		return this.currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(boolean approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public BigDecimal getInterest() {
		return this.interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public Date getApprovalDate() {
		return this.approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Integer getApprover() {
		return this.approver;
	}

	public void setApprover(Integer approver) {
		this.approver = approver;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SavingsAccountId))
			return false;
		SavingsAccountId castOther = (SavingsAccountId) other;

		return (this.getAccountId() == castOther.getAccountId()) && (this.getUserId() == castOther.getUserId())
				&& ((this.getAccountNumber() == castOther.getAccountNumber())
						|| (this.getAccountNumber() != null && castOther.getAccountNumber() != null
								&& this.getAccountNumber().equals(castOther.getAccountNumber())))
				&& ((this.getAccountType() == castOther.getAccountType())
						|| (this.getAccountType() != null && castOther.getAccountType() != null
								&& this.getAccountType().equals(castOther.getAccountType())))
				&& ((this.getCurrentBalance() == castOther.getCurrentBalance())
						|| (this.getCurrentBalance() != null && castOther.getCurrentBalance() != null
								&& this.getCurrentBalance().equals(castOther.getCurrentBalance())))
				&& ((this.getCreatedDate() == castOther.getCreatedDate())
						|| (this.getCreatedDate() != null && castOther.getCreatedDate() != null
								&& this.getCreatedDate().equals(castOther.getCreatedDate())))
				&& (this.isApprovalStatus() == castOther.isApprovalStatus())
				&& ((this.getInterest() == castOther.getInterest()) || (this.getInterest() != null
						&& castOther.getInterest() != null && this.getInterest().equals(castOther.getInterest())))
				&& ((this.getApprovalDate() == castOther.getApprovalDate())
						|| (this.getApprovalDate() != null && castOther.getApprovalDate() != null
								&& this.getApprovalDate().equals(castOther.getApprovalDate())))
				&& ((this.getApprover() == castOther.getApprover()) || (this.getApprover() != null
						&& castOther.getApprover() != null && this.getApprover().equals(castOther.getApprover())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getAccountId();
		result = 37 * result + this.getUserId();
		result = 37 * result + (getAccountNumber() == null ? 0 : this.getAccountNumber().hashCode());
		result = 37 * result + (getAccountType() == null ? 0 : this.getAccountType().hashCode());
		result = 37 * result + (getCurrentBalance() == null ? 0 : this.getCurrentBalance().hashCode());
		result = 37 * result + (getCreatedDate() == null ? 0 : this.getCreatedDate().hashCode());
		result = 37 * result + (this.isApprovalStatus() ? 1 : 0);
		result = 37 * result + (getInterest() == null ? 0 : this.getInterest().hashCode());
		result = 37 * result + (getApprovalDate() == null ? 0 : this.getApprovalDate().hashCode());
		result = 37 * result + (getApprover() == null ? 0 : this.getApprover().hashCode());
		return result;
	}

}
