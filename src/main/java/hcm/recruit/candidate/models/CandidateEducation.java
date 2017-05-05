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

@ManagedBean(name = "candidateEducationModel", eager = true)
@RequestScoped
public class CandidateEducation {
	private Integer id; 
	private String candidateId; 
	private String type; // education or certification
	private String institutionName;
	private String institutionAddress;
	private String country;
	private String educationLevel;
	private String majorOrProgramStudied;
	private Date startDate;
	private Date endDate;
	private Boolean isStudying;
	private String speacialAwardsReceived;
	private String certificationName;
	

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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	@Column
	public String getInstitutionAddress() {
		return institutionAddress;
	}
	public void setInstitutionAddress(String institutionAddress) {
		this.institutionAddress = institutionAddress;
	}
	@Column
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Column
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	@Column
	public String getMajorOrProgramStudied() {
		return majorOrProgramStudied;
	}
	public void setMajorOrProgramStudied(String majorOrProgramStudied) {
		this.majorOrProgramStudied = majorOrProgramStudied;
	}
	@Column
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Column
	public Boolean getIsStudying() {
		return isStudying;
	}
	public void setIsStudying(Boolean isStudying) {
		this.isStudying = isStudying;
	}
	@Column
	public String getSpeacialAwardsReceived() {
		return speacialAwardsReceived;
	}
	public void setSpeacialAwardsReceived(String speacialAwardsReceived) {
		this.speacialAwardsReceived = speacialAwardsReceived;
	}
	@Column
	public String getCertificationName() {
		return certificationName;
	}
	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	} 
	
	
}
