package hcm.user.models;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table

@ManagedBean(name = "roleModel", eager = true)
@RequestScoped
public class RolesPermission implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2070357513686679164L; 
	
	private Integer id; 
	private String permission; 
	private String roleName; 
	private String roleDescription;
	private Boolean isEnabled;
	private String menuLocation;
	private Date createdDate; 
	  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Column
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column
	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Column
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Column
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getMenuLocation() {
		return menuLocation;
	}

	public void setMenuLocation(String menuLocation) {
		this.menuLocation = menuLocation;
	}

}
