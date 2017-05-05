package hcm.global.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class SessionBean {
 
	 public static HttpSession getSession() {
	        return (HttpSession)
	          FacesContext.
	          getCurrentInstance().
	          getExternalContext().
	          getSession(true);
	      }
	       
	      public static HttpServletRequest getRequest() {
	       return (HttpServletRequest) FacesContext.
	          getCurrentInstance().
	          getExternalContext().getRequest();
	      }
	      
	      public static HttpServletResponse  getResponse()
	      {
	    	  return(HttpServletResponse) FacesContext.
	    	          getCurrentInstance().
	    	          getExternalContext().getResponse();
	      }
	 
	      public static String getUserName()
	      {
	        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	        return  session.getAttribute("username").toString();
	      }
	       
	      public static String getUserId()
	      {
	        HttpSession session = getSession();
	        if ( session != null )
	            return (String) session.getAttribute("userid");
	        else
	            return null;
	      }
}