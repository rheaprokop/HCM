package hcm.user.beans;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap; 
import java.util.LinkedList;
import java.util.List;
import java.util.Map; 

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;

import hcm.global.utils.HibernateUtil;
import hcm.user.models.RolesPermission;
import hcm.user.models.User;
import hcm.user.models.UserRole;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
 
@RequestScoped
@ManagedBean
@SessionScoped
@ViewScoped 
public class RoleBean {

	private List<RolesPermission> roles;
	 
	public List<RolesPermission> getRoles() {
		return roles;
	}

	public void setRoles(List<RolesPermission> roles) {
		this.roles = roles;
	}
	
	private List<User> selectedUsersInRole;
	 
	public List<User> getSelectedUsersInRole() {
		return selectedUsersInRole;
	}

	public void setSelectedUsersInRole(List<User> selectedUsersInRole) {
		this.selectedUsersInRole = selectedUsersInRole;
	}

	public List<User> existingUsers;

	public List<User> getExistingUsers() {
		
		return existingUsers;
	}

	public void setExistingUsers(List<User> existingUsers) {
		this.existingUsers = existingUsers;
	}

	private RolesPermission roleModel;

	public RolesPermission getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(RolesPermission roleModel) {
		this.roleModel = roleModel;
	} 
	
	//CHECKBOXES: Map each checkboxes in the data tables to each roles
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>(); 
	public Map<Long, Boolean> getChecked() {
		return checked;
	} 
	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	} 
	  
	@PostConstruct
	public void init() {
		roles = listRoles();   
	}
	
	
	// List all roles in table: Roles
	public List<RolesPermission> getRoleList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<RolesPermission> roles = session.createQuery("from hcm.user.models.RolesPermission").getResultList();
			session.getTransaction().commit();
			session.close();
			return roles;
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}
	
	//List all users in certain role. 
	public List<UserRole> getUsers(String roleId)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<UserRole> roles = session.createQuery("from UserRole where rolePermissionId=?").setString(0, roleId).list();
			session.getTransaction().commit();
			session.close();
			return roles;
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}
	
	//DROPDOWN: populate the dropdown with list of roles
	public List<RolesPermission> listRoles()
	{
		List<RolesPermission> roles = new LinkedList<RolesPermission>();	
		
		for(RolesPermission r : getRoleList())
		{  
			 RolesPermission ro = new RolesPermission();
			 ro.setId(r.getId());
			 ro.setRoleName(r.getRoleName());
			 ro.setPermission(r.getPermission()); 
			 roles.add(ro);
			  
		}
		 
		return roles;
		
	}
	
	//LISTBOX: popullistUsersate existing users in the dropdown based on selected role. 
	//public User[] userList;
	public List<User> listUsersInRole()
	{
		List<User> users = new ArrayList<User>(); 
		
		UserBean userBean = new UserBean();
		for(User u : userBean.getUserList())
		{ 
			User usr = new User();
			usr.setId(u.getId());
			usr.setFirstName(u.getFirstName());
			usr.setLastName(u.getLastName());
			
			users.add(usr); 
		} 
		
		return users;
	} 
	
	
	public void selectRoleChangeMethod(ValueChangeEvent e)
			throws AbortProcessingException {
		 
	    existingUsers = listUsersInRole();      
	}
 
	
	
	//Counting number of members in the specific role.
	public Integer usersCount(Integer roleId)
	{
		int count = getUsers(Integer.toString(roleId)).size();
		return count;
	}
	
	
	//Specifically get UserRole entity by email address
	public static List<UserRole> getUserRolesByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<UserRole> roles = session.createQuery("from UserRole where email=?").setString(0, email).list();
			session.getTransaction().commit();
			session.close();
			return roles;
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static RolesPermission getRolePermission(String rolePermissionId)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			RolesPermission role = (RolesPermission) session.createQuery("from RolesPermission where id=?").setString(0, rolePermissionId).uniqueResult();
			session.getTransaction().commit();
			session.close();
			return role;
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}
	
	public boolean isRoleAssignedToUser(String email, String rolePermissionId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean isEnabled = false;
		try { 
			for (UserRole uRole : getUserRolesByEmail(email)) { 
				if (uRole.getRolePermissionId().equals(rolePermissionId)) {
					isEnabled = true;
					break;
				}
			}
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return isEnabled;
	}
	
	public Integer countRolesAssigned(String email)
	{ 
		return getUserRolesByEmail(email).size();
	}
	
	public Integer countRoleUnAssigned(String email)
	{
		return getRoleList().size() - countRolesAssigned(email);
	}
	
	//INSERT - create new role and inserts into table "RolesPermission"
	public String createRole()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		 try
		 {
			  RolesPermission role = new RolesPermission();
			  role.setRoleName(roleModel.getRoleName());
			  role.setRoleDescription(roleModel.getRoleDescription());
			  role.setCreatedDate(new Date());
			  role.setPermission(roleModel.getRoleName().toLowerCase().replaceAll("\\s+","")+":"+"access"); 
			  role.setIsEnabled(true);
			  session.beginTransaction(); 
			  session.save(role);
			  session.getTransaction().commit(); 
			  session.close();
			  
			  FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"New role created", "New role has been created into HCM Soft. To add or update more details about the role, please go to edit page. !")); 
			  return "manage-roles";
		 }catch(Throwable e)
		 {
			 throw new ExceptionInInitializerError(e);
		 }
		 finally {
		        if (session.isOpen()) {
		            session.close();
		        }
		 }
	}
	
	//DISPLAY - display selected role based on role ID and displays for edit. 
	public String editRole(String roleId)
	{ 
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			RolesPermission role = (RolesPermission) session.createQuery("from RolesPermission where id=?").setString(0, roleId).uniqueResult();
			session.getTransaction().commit(); 
			roleModel.setId(Integer.parseInt(roleId)); 
			roleModel.setRoleName(role.getRoleName());
			roleModel.setRoleDescription(role.getRoleDescription());
			roleModel.setIsEnabled(true);
			session.close();
			return "edit-role";
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		} 
	}
	
	//EDIT - edit and saved a selected role based on role ID to table RolesPermission. 
	public String editRole()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction(); 
			Query query = session.createQuery("update RolesPermission set roleName = :roleName, roleDescription = :roleDescription, " +
    				" isEnabled = :isEnabled " + 
					" where id = :id");
			query.setParameter("roleName", roleModel.getRoleName());
			query.setParameter("roleDescription", roleModel.getRoleDescription()); 
			query.setParameter("isEnabled", roleModel.getIsEnabled()); 
			query.setParameter("id", roleModel.getId()); 
			query.executeUpdate(); 
			session.getTransaction().commit();
			session.close();
			
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Role Updated!", "Role has been updated current role."));
			
		 
			
			return "edit-user";
		} catch (HibernateException  e) { 
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update failed!", e.toString()));
			return "edit-user";
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		} 
	}
	public String deleteRole(String roleId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try { 
		  
			if(usersCount(Integer.parseInt(roleId)) > 0)
			{
				// do not delete role because there are existing members in the role. 
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Delete Failed.", "There are currently assigned members to this role. This role cannot be deleted." + 
								" Please remove members from this role in order to delete it. "));
			}
			else
			{
				session.beginTransaction();
				//delete is possible
				Query q2 = session.createQuery("delete from RolesPermission  where id = :ID");
				q2.setParameter("ID", Integer.parseInt(roleId));
				q2.executeUpdate(); 
				
				session.getTransaction().commit();
				session.close();
				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Role deleted.", "This role has been deleted successfully!"));
			}
			 
			return "OK";
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}
	
	public String deleteAll()
	{  
		Session session = HibernateUtil.getSessionFactory().openSession(); 
			
		try { 
			session.beginTransaction();
			for (RolesPermission r : getRoleList()) {
			    if (checked.get(r.getId())) { 
			    	
			    	if(usersCount(r.getId()) > 0)
			    	{
			    		// do not delete role because there are existing members in the role. 
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										r.getRoleName() + " not deleted.", "There are currently assigned members to this role. This role cannot be deleted." + 
										" Please remove members from this role in order to delete it. "));
			    	}
			    	else
			    	{
				    	session.delete(r); 
				    	FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										"Role deleted!", "Selected roles has been deleted successfully!"));
			    	}
			    }
			}
			checked.clear(); 
			 
			session.getTransaction().commit();
			session.close(); 
			
			 
			return "OK";
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	} 
	
	//Assign members into the role pages
	public String assignMembers()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try { 
			
			return "manage-roles";
		} catch (Exception e) {
			System.out.println("May error");
			throw new ExceptionInInitializerError(e);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	  
	}
}
