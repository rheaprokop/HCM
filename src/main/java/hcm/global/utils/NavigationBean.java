package hcm.global.utils;

import javax.faces.bean.ManagedBean;
import hcm.global.utils.SessionBean;

@ManagedBean(name = "navigationBean", eager = true)
public class NavigationBean { 

	private String arrowForWorkspace;
	private String arrowForSelfServiceTime;
	private String arrowForSelfServicePay;
	private String arrowForSelfServiceTalent;
	private String arrowForSelfServiceUpskill;
	private String arrowForRecruitmentCandidate;
	private String arrowForRecruitmentJob;
	
	
	public String getArrowForRecruitmentJob() {
		return arrowForRecruitmentJob;
	}

	public void setArrowForRecruitmentJob(String arrowForRecruitmentJob) {
		this.arrowForRecruitmentJob = arrowForRecruitmentJob;
	}

	public String getArrowForRecruitmentCandidate() {
		return arrowForRecruitmentCandidate;
	}

	public void setArrowForRecruitmentCandidate(String arrowForRecruitmentCandidate) {
		this.arrowForRecruitmentCandidate = arrowForRecruitmentCandidate;
	}

	public String getArrowForSelfServiceUpskill() {
		return arrowForSelfServiceUpskill;
	}

	public void setArrowForSelfServiceUpskill(String arrowForSelfServiceUpskill) {
		this.arrowForSelfServiceUpskill = arrowForSelfServiceUpskill;
	}

	public String getArrowForSelfServiceTalent() {
		return arrowForSelfServiceTalent;
	}

	public void setArrowForSelfServiceTalent(String arrowForSelfServiceTalent) {
		this.arrowForSelfServiceTalent = arrowForSelfServiceTalent;
	}

	public void setArrowForWorkspace(String arrowForWorkspace) {
		this.arrowForWorkspace = arrowForWorkspace;
	}

	public void setArrowForSelfServiceTime(String arrowForSelfServiceTime) {
		this.arrowForSelfServiceTime = arrowForSelfServiceTime;
	}

	public void setArrowForSelfServicePay(String arrowForSelfServicePay) {
		this.arrowForSelfServicePay = arrowForSelfServicePay;
	}

	private String arrowForMessage; 
	
	public String getArrowForWorkspace() {
		return arrowForWorkspace;
	}

	public String getArrowForSelfServiceTime() {
		return arrowForSelfServiceTime;
	}

	public String getArrowForSelfServicePay() {
		return arrowForSelfServicePay;
	}

	public String getArrowForMessage() {
		return arrowForMessage;
	} 
	
	private String getModule() {
		return SessionBean.getRequest().getRequestURI().toString();
	}

	public String getWorkspaceNav() {

		if (getModule().contains("dashboard.xhtml") || getModule().contains("calendar.xhtml")
				|| getModule().contains("inbox.xhtml") || getModule().contains("compose.xhtml")) {
			
			arrowForWorkspace = "arrow open";
			return "nav-item start active open";
		} else {
			arrowForWorkspace = "arrow";
			return "nav-item start";
		}
	} 

	public String getWorkspaceSubNav() { 
		if (getModule().contains("inbox.xhtml") || getModule().contains("compose.xhtml")) {
			arrowForWorkspace = "arrow open";
			arrowForMessage = "arrow open";
			return "nav-item  active open";
		} else {
			arrowForWorkspace = "arrow";
			arrowForMessage = "arrow";
			return "nav-item";
		}
	}
	
	public String getSelfServiceNavTime() {

		if (getModule().contains("request-for-leave.xhtml") || 
			getModule().contains("request-for-home-office.xhtml") || 
			getModule().contains("request-for-overtime.xhtml") || 
			getModule().contains("request-for-travel.xhtml")) {
			
			arrowForSelfServiceTime = "arrow open"; 
			return "nav-item start active open";
		} else {
			arrowForSelfServiceTime = "arrow"; 
			return "nav-item start";
		}
	} 
	
	public String getSelfServiceNavPay() {

		if (getModule().contains("pay-history.xhtml") || 
			getModule().contains("my-benefits.xhtml") || 
			getModule().contains("bank-information.xhtml") || 
			getModule().contains("request-advance.xhtml") ||
			getModule().contains("reimburse-request.xhtml") ||
			getModule().contains("credit-request.xhtml")	) {
			
			arrowForSelfServicePay = "arrow open"; 
			return "nav-item start active open";
		} else {
			arrowForSelfServicePay = "arrow"; 
			return "nav-item start";
		}
	}
	public String getSelfServiceNavTalent() {

		if (getModule().contains("goals-objectives.xhtml") || 
			getModule().contains("my-trainings.xhtml")) {
			
			arrowForSelfServiceTalent = "arrow open"; 
			return "nav-item start active open";
		} else {
			arrowForSelfServiceTalent = "arrow"; 
			return "nav-item start";
		}
	} 
	
	public String getSelfServiceNavUpskill() {

		if (getModule().contains("request-for-training.xhtml") || 
			getModule().contains("training-history.xhtml") || 
			getModule().contains("my-available-trainings.xhtml")) {
			
			arrowForSelfServiceUpskill = "arrow open"; 
			return "nav-item start active open";
		} else {
			arrowForSelfServiceUpskill = "arrow"; 
			return "nav-item start";
		}
	} 
	
	public String getRecruitmentCandidate() {

		if (getModule().contains("applicant-tracking.xhtml") || 
			getModule().contains("create-candidate.xhtml") || 
			getModule().contains("manage-candidates.xhtml") ||
			getModule().contains("search-candidate.xhtml") ||
			getModule().contains("candidate-profile.xhtml")) {
			
			arrowForRecruitmentCandidate = "arrow open"; 
			return "nav-item start active open";
		} else {
			arrowForRecruitmentCandidate = "arrow"; 
			return "nav-item start";
		}
	} 
	
	public String getRecruitmentJob() {

		if (getModule().contains("job-requisition.xhtml") || 
			getModule().contains("job-database.xhtml") || 
			getModule().contains("create-job.xhtml")) {
			
			arrowForRecruitmentJob = "arrow open"; 
			return "nav-item start active open";
		} else {
			arrowForRecruitmentJob = "arrow"; 
			return "nav-item start";
		}
	} 
}
