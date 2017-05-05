package hcm.user.models;

import java.io.Serializable;

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

@ManagedBean(name = "applicationModel", eager = true)
@RequestScoped
public class Application implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer id; 
	private String name; 
	private String description; 
	private String rolePermissionId;  
	private Integer parentId; //default 0 means root.
	private String cssIcon;
	private String navigationBeanClass;
	private boolean isMultiLevelNav;
	private String pathValue; 
	private String filename;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column
	public String getRolePermissionId() {
		return rolePermissionId;
	}
	public void setRolePermissionId(String rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	} 
	
	@Column
	public String getCssIcon() {
		return cssIcon;
	}
	public void setCssIcon(String cssIcon) {
		this.cssIcon = cssIcon;
	}
	
	@Column
	public String getNavigationBeanClass() {
		return navigationBeanClass;
	}
	public void setNavigationBeanClass(String navigationBeanClass) {
		this.navigationBeanClass = navigationBeanClass;
	}
	
	@Column
	public String getPathValue() {
		return pathValue;
	}
	public void setPathValue(String pathValue) {
		this.pathValue = pathValue;
	}
	
	@Column
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public boolean isMultiLevelNav() {
		return isMultiLevelNav;
	}
	public void setMultiLevelNav(boolean isMultiLevelNav) {
		this.isMultiLevelNav = isMultiLevelNav;
	} 
	
}
