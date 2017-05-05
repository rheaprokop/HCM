package hcm.user.models;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table

@ManagedBean(name = "userRoleModel", eager = true)
@RequestScoped
public class UserRole implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8432414340180447723L;

	private Integer id; 
	private String rolePermissionId; 
	private String userId;
	private String roleName; 
	private String email; 
	
	private User user;

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
	public String getRolePermissionId() {
		return rolePermissionId;
	}

	public void setRolePermissionId(String rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	}
	
	@Column
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
 

}