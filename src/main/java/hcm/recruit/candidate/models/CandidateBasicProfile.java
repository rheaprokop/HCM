package hcm.recruit.candidate.models;

import java.sql.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table

@ManagedBean(name = "candidateBasicProfileModel", eager = true)
@RequestScoped

public class CandidateBasicProfile {

	private Integer id; 
	private String candidateId; 
	private String title;
	private String firstName;
	private String middleName;
	private String address1;
	private String address2;
	private String zipCode;
	private String stateOrRegion;
	private String country;
	private String primaryPhone;
	private String mobile;
	private String otherPhone;
	private String emailAddress;
	private String websiteAddress;
	private String jobSourceType;
	private String otherSource; 
	private Date availabilityDate;
	private String advanceNotice; 
	private String travel; 
	private String totalYearsExperience; 
	private String expectedSalary;
	private String expectedCurrencySalary;
	private String jobType;
	private String jobShift; 
	private Boolean previousEmployee;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	@Column
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	@Column
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	@Column
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	@Column
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Column
	public String getStateOrRegion() {
		return stateOrRegion;
	}
	public void setStateOrRegion(String stateOrRegion) {
		this.stateOrRegion = stateOrRegion;
	}
	@Column
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Column
	public String getPrimaryPhone() {
		return primaryPhone;
	}
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}
	@Column
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column
	public String getOtherPhone() {
		return otherPhone;
	}
	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}
	@Column
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@Column
	public String getWebsiteAddress() {
		return websiteAddress;
	}
	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
	}
	@Column
	public String getJobSourceType() {
		return jobSourceType;
	}
	public void setJobSourceType(String jobSourceType) {
		this.jobSourceType = jobSourceType;
	}
	@Column
	public String getOtherSource() {
		return otherSource;
	}
	public void setOtherSource(String otherSource) {
		this.otherSource = otherSource;
	}
	@Column
	public Date getAvailabilityDate() {
		return availabilityDate;
	}
	public void setAvailabilityDate(Date availabilityDate) {
		this.availabilityDate = availabilityDate;
	}
	@Column
	public String getAdvanceNotice() {
		return advanceNotice;
	}
	public void setAdvanceNotice(String advanceNotice) {
		this.advanceNotice = advanceNotice;
	}
	@Column
	public String getTravel() {
		return travel;
	}
	public void setTravel(String travel) {
		this.travel = travel;
	}
	@Column
	public String getTotalYearsExperience() {
		return totalYearsExperience;
	}
	public void setTotalYearsExperience(String totalYearsExperience) {
		this.totalYearsExperience = totalYearsExperience;
	}
	@Column
	public String getExpectedSalary() {
		return expectedSalary;
	}
	public void setExpectedSalary(String expectedSalary) {
		this.expectedSalary = expectedSalary;
	}
	@Column
	public String getExpectedCurrencySalary() {
		return expectedCurrencySalary;
	}
	public void setExpectedCurrencySalary(String expectedCurrencySalary) {
		this.expectedCurrencySalary = expectedCurrencySalary;
	}
	@Column
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	@Column
	public String getJobShift() {
		return jobShift;
	}
	public void setJobShift(String jobShift) {
		this.jobShift = jobShift;
	}
	@Column
	public Boolean getPreviousEmployee() {
		return previousEmployee;
	}
	public void setPreviousEmployee(Boolean previousEmployee) {
		this.previousEmployee = previousEmployee;
	}
	
	


}
