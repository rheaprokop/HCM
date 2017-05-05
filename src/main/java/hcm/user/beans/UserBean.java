package hcm.user.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import hcm.global.utils.HibernateUtil;
import hcm.global.utils.SessionBean;
import hcm.user.beans.RoleBean;
import hcm.user.models.RolesPermission;
import hcm.user.models.User;
import hcm.user.models.UserRole;
 
@ManagedBean
@SessionScoped 
public class UserBean {

	private User userModel;
	private UserRole userRoleModel;
	private List<String> selectedRoles = new ArrayList<String>();
	public boolean responseRendered = false;
	public boolean result = false;
	private boolean rememberMe;

	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();

	// private List<User> checkedItems = new ArrayList<User>();
	// private List<Integer> checkedUser = new ArrayList<Integer>();
	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public boolean isResponseRendered() {
		return responseRendered;
	}

	public void setResponseRendered(boolean responseRendered) {
		this.responseRendered = responseRendered;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public UserRole getUserRoleModel() {
		return userRoleModel;
	}

	public void setUserRoleModel(UserRole userRoleModel) {
		this.userRoleModel = userRoleModel;
	}

	public User getUserModel() {
		return userModel;
	}

	public void setUserModel(User userModel) {
		this.userModel = userModel;
	}

	public List<String> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(List<String> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}
 
	
	public User getCurrentUser() {
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();

		if (currentUser.isAuthenticated()) {
			String mail = (String) currentUser.getSession().getAttribute("username");
			User user = getUserByEmail(mail);
			return user;
		} else {
			return null;
		}
	}

	public String createUser() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			User user = new User();
			user.setUsername(userModel.getEmail());
			user.setEmail(userModel.getEmail());
			user.setFirstName(userModel.getFirstName());
			user.setLastName(userModel.getLastName());
			user.setPhone(userModel.getPhone());
			user.setCreatedDate(new Date());
			user.setRemark(userModel.getRemark());
			user.setCreatedDate(new Date());
			user.setCreatedBy(getCurrentUser().getEmail());
			user.setIsEnabled(true);
			generatePassword(user, userModel.getPassword());
			session.beginTransaction();

			Set<UserRole> userRole = new HashSet<UserRole>();
			for (String r : selectedRoles) {
				UserRole role = new UserRole();
				role.setRolePermissionId(r);
				role.setEmail(userModel.getEmail());

				userRole.add(role);
				user.setUserRoles(userRole);
			}
			session.save(user);
			session.getTransaction().commit();
			session.close();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"User Saved",
					"New user has been created into HCM Soft. To add or update more details about the user, please go to edit page. !"));
			result = !result;

			return "view-user";
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	public void viewRecord(String userId) {
		userModel.setId(Integer.parseInt(userId));
		userModel.setFirstName(getUserById(userId).getFirstName());
		userModel.setLastName(getUserById(userId).getLastName());
		userModel.setEmail(getUserById(userId).getEmail());
		userModel.setPhone(getUserById(userId).getPhone());
		userModel.setRemark(getUserById(userId).getRemark());
		userModel.setIsEnabled(getUserById(userId).getIsEnabled());
		userModel.setLastLogin(getUserById(userId).getLastLogin());
		userModel.setCreatedBy(getUserById(userId).getCreatedBy());
		userModel.setCreatedDate(getUserById(userId).getCreatedDate());
		userModel.setModifiedBy(getUserById(userId).getModifiedBy());
		userModel.setModifiedDate(getUserById(userId).getModifiedDate());
	}

	public String viewUser(String userId) {

		viewRecord(userId);
		return "view-user";
	}

	public String editUser(String userId) {
		viewRecord(userId);
		return "edit-user";
	}

	public String editUser() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session
					.createQuery("update User set firstName = :firstName, lastName = :lastName, email = :email, "
							+ "phone = :phone, remark = :remark, modifiedBy = :modifiedBy, modifiedDate = :modifiedDate, isEnabled = :isEnabled "
							+ " where email = :email");
			query.setParameter("firstName", userModel.getFirstName());
			query.setParameter("lastName", userModel.getLastName());
			query.setParameter("email", userModel.getEmail());
			query.setParameter("phone", userModel.getPhone());
			query.setParameter("remark", userModel.getRemark());
			query.setParameter("modifiedBy", "test");
			query.setParameter("modifiedDate", new Date());
			query.setParameter("isEnabled", userModel.getIsEnabled());
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"User deleted!", "User(s) and its dependent record(s) has been updated successfully!"));
			result = !result;

			return "edit-user";
		} catch (HibernateException e) {
			result = !result;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update failed!", e.toString()));
			return "edit-user";
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	public String deleteUser(String userId) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query q1 = session.createQuery("delete from UserRole where  userId = :ID");
			q1.setParameter("ID", userId);
			q1.executeUpdate();

			Query q2 = session.createQuery("delete from User  where id = :ID");
			q2.setParameter("ID", Integer.parseInt(userId));
			q2.executeUpdate();

			session.getTransaction().commit();
			session.close();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"User deleted.", "User(s) and its dependent record(s) has been deleted successfully!"));
			result = !result;

			return "OK";
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	public String deleteAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			for (User u : getUserList()) {
				if (checked.get(u.getId())) {
					session.delete(u);
				}
			}
			checked.clear();

			session.getTransaction().commit();
			session.close();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"User deleted!", "User(s) and its dependent record(s) has been deleted successfully!"));
			result = !result;

			return "OK";
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	private void generatePassword(User user, String plainTextPassword) {
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		Object salt = rng.nextBytes();

		// Now hash the plain-text password with the random salt and multiple
		// iterations and then Base64-encode the value (requires less space than
		// Hex):
		String hashedPasswordBase64 = new Sha256Hash(plainTextPassword, salt, 1024).toBase64(); 
		user.setPassword(hashedPasswordBase64);
		user.setSalt(salt.toString());
	}

	@SuppressWarnings("deprecation")
	public static User getUserByEmail(String email) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			User user = (User) session.createQuery("from User where email=?").setString(0, email).uniqueResult();
			session.getTransaction().commit();
			return user;
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	@SuppressWarnings("deprecation")
	public static User getUserById(String id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			User user = (User) session.createQuery("from User where id=?").setString(0, id).uniqueResult();
			session.getTransaction().commit();

			session.close();
			return user;
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	public List<User> getUserList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<User> users = session.createQuery("from hcm.user.models.User").getResultList();
			session.close();
			return users;
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	public String userLogin() {
		// get the currently executing user:
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		try {
			if (!currentUser.isAuthenticated()) {
				// collect user principals and credentials in a gui specific
				// manner
				// such as username/password html form, X509 certificate,
				// OpenID, etc.
				// We'll use the username/password example here since it is the
				// most common.
				UsernamePasswordToken token = new UsernamePasswordToken(userModel.getUsername(),
						userModel.getPassword());
				// this is all you have to do to support 'remember me' (no
				// config - built in!):
				token.setRememberMe(rememberMe);

				try {
					currentUser.login(token);
					currentUser.getSession().setAttribute("username", userModel.getUsername());

					// UPDATES USER'S PROFILE
					Session session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();
					Query query = session
							.createQuery("update User set lastLogin = :lastLogin" + " where email = :email");
					query.setParameter("lastLogin", new Date());
					query.setParameter("email", userModel.getUsername());
					query.executeUpdate();
					session.getTransaction().commit();
					session.close();

					return "dashboard";
				} catch (UnknownAccountException uae) {
					responseRendered = !responseRendered;
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"There is no user with username of " + token.getPrincipal(), null));
					return "login";
				} catch (IncorrectCredentialsException ice) {
					responseRendered = !responseRendered;
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Password for account " + token.getPrincipal() + " was incorrect!", null));
					return "login";
				} catch (LockedAccountException lae) {
					responseRendered = !responseRendered;
					FacesContext.getCurrentInstance()
							.addMessage(null,
									new FacesMessage(FacesMessage.SEVERITY_ERROR,
											"The account for username " + token.getPrincipal()
													+ " is locked. Please contact your administrator to unlock it.",
											null));
					return "login";
				} catch (AuthenticationException aex) {
					responseRendered = !responseRendered;
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", aex.toString()));
					return "login";
				}
			} else {
				return "dashboard"; // already logged in
			}
		} catch (Throwable e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			throw new ExceptionInInitializerError(e);
		}

	}

	public String saveRoles() { 
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {   
			
			RoleBean roleBean = new RoleBean(); 
			
			session.beginTransaction();
			Query q1 = session.createQuery("delete from UserRole where  userId = :ID");
			q1.setParameter("ID", Integer.toString(userModel.getId())); 
			q1.executeUpdate(); 
			session.getTransaction().commit();

			for (RolesPermission r : roleBean.getRoleList()) {

				if (checked.get(r.getId())) { 
					UserRole role = new UserRole();
					role.setRolePermissionId(r.getId().toString());
					role.setEmail(getCurrentUser().getEmail());
					role.setRoleName(r.getRoleName());
					role.setUserId(Integer.toString(userModel.getId())); 
					session.save(role);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							r.getRoleName() + " has been assigned.", r.getRoleDescription()));

				}
			}
			checked.clear();
			session.getTransaction().commit();
			session.close();

			return "edit-user";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			
			return "edit-user";
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	public String changePassword(String userId) {

		return "";
	}

	public String logoutUser() {

		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();

		return "/login.xhtml?faces-redirect=true";
	}
}
