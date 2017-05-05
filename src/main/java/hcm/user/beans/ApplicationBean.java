package hcm.user.beans;
 
import java.util.List;

import javax.faces.bean.ManagedBean; 
import javax.faces.bean.SessionScoped; 

import org.hibernate.Session;
import org.hibernate.query.Query;

import hcm.global.utils.HibernateUtil; 
import hcm.user.models.RolesPermission;
 

@ManagedBean
@SessionScoped 
public class ApplicationBean {

	//List all application based on roles for currently logged in user
	

	public List<RolesPermission> getApplicationList(){
		
		UserBean userBean = new UserBean(); 
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			
			session.beginTransaction();  
			
			String applicationSql = " Select rolePerm.roleName as roleName, rolePerm.id as roleId FROM RolesPermission as rolePerm " + 
			"INNER JOIN UserRole as userRole ON rolePerm.id = userRole.rolePermissionId " + 
			"WHERE userRole.userId = :userId AND rolePerm.menuLocation like '%sidebar%' ";
			Query query = session.createQuery(applicationSql);
			query.setParameter("userId", Integer.toString(userBean.getCurrentUser().getId()));
			//query.setParameter("location", "%"+location+"%");
			List<RolesPermission> apps  = query.getResultList();  
//			
			session.getTransaction().commit();
			session.close();
			return apps;
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}
	
//	public List<Application> applicationRoot(int rolePermissionId){
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		
//		try { 
//			session.beginTransaction();  
//			
//			String applicationSql = " FROM Application where parentId=0 and rolePermissionId=:rolePermissionId";
//			Query query = session.createQuery(applicationSql); 
//			query.setParameter("rolePermissionId", Integer.toString(rolePermissionId));
//			List<Application> apps  = query.getResultList();  
////			
//			session.getTransaction().commit();
//			session.close();
//			return apps;
//		} catch (Throwable e) {
//			throw new ExceptionInInitializerError(e);
//
//		} finally {
//			if (session.isOpen()) {
//				session.close();
//			}
//		}
//	}
//	
//	public List<Application> applicationSubMenu(int parentId){
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		
//		try { 
//			session.beginTransaction();  
//			
//			String applicationSql = " FROM Application where parentId=:parentId";
//			Query query = session.createQuery(applicationSql); 
//			query.setParameter("parentId", Integer.toString(parentId));
//			List<Application> apps  = query.getResultList();  
////			
//			session.getTransaction().commit();
//			session.close();
//			return apps;
//		} catch (Throwable e) {
//			throw new ExceptionInInitializerError(e);
//
//		} finally {
//			if (session.isOpen()) {
//				session.close();
//			}
//		}
//	}
}
