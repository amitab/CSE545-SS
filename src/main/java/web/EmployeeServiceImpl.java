package web;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import database.SessionManager;
import forms.Search;
import forms.SearchForm;
import model.Account;
import model.User;
import model.UserDetail;
import forms.EmployeeSearch;
import forms.EmployeeSearchForm;

public class EmployeeServiceImpl {
	
	public EmployeeSearchForm getEmployees(String username) {	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentSessionUser = null;
		if(auth!=null || auth.isAuthenticated()) {
			for (GrantedAuthority grantedAuthority : auth.getAuthorities()) {
				if (grantedAuthority.getAuthority().equals("admin")) {
					currentSessionUser = grantedAuthority.getAuthority();
				}
			}
			if(currentSessionUser==null) {
				return null;
			}
		}
		
		
		Session s = SessionManager.getSession("");
		List<User> user=null;
		
		user=s.createQuery("FROM User WHERE username = :username", User.class)
				.setParameter("username", username).getResultList();	
		EmployeeSearchForm employeeSearchForm = new EmployeeSearchForm();
		List<EmployeeSearch> employeeSearch = new ArrayList<EmployeeSearch>();
		for(User temp : user )
		{
			if((temp.getRole().equals("tier2")||temp.getRole().equals("tier1")) && temp.getStatus()!=3)
			{
			System.out.println("Get Here");
			UserDetail ud = new UserDetail();
			ud = s.createQuery("FROM UserDetail WHERE user_id = :uid", UserDetail.class)
					.setParameter("uid",temp.getId()).getSingleResult();
			System.out.println(ud.getCity());
			EmployeeSearch tempSearch=new EmployeeSearch(temp.getUsername(),ud.getEmail(),ud.getFirstName(),ud.getLastName(),ud.getMiddleName(),ud.getPhone());	
			employeeSearch.add(tempSearch);	
			}
		}	
		employeeSearchForm.setEmployeeSearchs(employeeSearch);
		return employeeSearchForm;		
		
	}
	
	public Boolean updateEmployees(String userName,String email,String firstName,String lastName,String middleName,String phoneNumber) {	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentSessionUser = null;
		if(auth!=null || auth.isAuthenticated()) {
			for (GrantedAuthority grantedAuthority : auth.getAuthorities()) {
				if (grantedAuthority.getAuthority().equals("admin")) {
					currentSessionUser = grantedAuthority.getAuthority();
				}
			}
			if(currentSessionUser==null) {
				return null;
			}
		}
		
		
		 Session s = SessionManager.getSession("");
		 List<User> user=null;
		 user=s.createQuery("FROM User WHERE username = :username", User.class)
					.setParameter("username", userName).getResultList();
		 
		Transaction tx = null;
		tx = s.beginTransaction();
		 if(user.size()==0)
			 return false;
		for(User temp : user )
		{
			if((temp.getRole().equals("tier2")||temp.getRole().equals("tier1")) && temp.getStatus()!=3)
			{
			System.out.println("Get Here");
			UserDetail ud = new UserDetail();
			ud = s.createQuery("FROM UserDetail WHERE user_id = :uid", UserDetail.class)
			.setParameter("uid", temp.getId()).getSingleResult();
			ud.setEmail(email);
			ud.setFirstName(firstName);
			ud.setLastName(lastName);
			ud.setMiddleName(middleName);
			ud.setPhone(phoneNumber);
			s.saveOrUpdate(ud);
			}
			else
				return false;
		}
		if (tx.isActive())
		    tx.commit();
		s.close();
		return true;
   
		
	}
	
	public Boolean deleteEmployees(String userName,String firstName,String lastName) {	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentSessionUser = null;
		if(auth!=null || auth.isAuthenticated()) {
			for (GrantedAuthority grantedAuthority : auth.getAuthorities()) {
				if (grantedAuthority.getAuthority().equals("admin")) {
					currentSessionUser = grantedAuthority.getAuthority();
				}
			}
			if(currentSessionUser==null) {
				return null;
			}
		}
		
		
		 Session s = SessionManager.getSession("");
		 List<User> user=null;
		 user=s.createQuery("FROM User WHERE username = :username", User.class)
					.setParameter("username", userName).getResultList();
		 
		Transaction tx = null;
		tx = s.beginTransaction();
		 if(user.size()==0)
			 return false;
		for(User temp : user )
		{
			if((temp.getRole().equals("tier2")||temp.getRole().equals("tier1")) && temp.getStatus()!=3)
			{
			System.out.println("Get Here");
			UserDetail ud = new UserDetail();
			ud = s.createQuery("FROM UserDetail WHERE user_id = :uid", UserDetail.class)
			.setParameter("uid", temp.getId()).getSingleResult();
			if(ud.getLastName().equals(lastName) && ud.getFirstName().equals(firstName))
			{
				temp.setStatus(3);
				s.saveOrUpdate(temp);
			}
			else
				 return false;
			}
			else
				return false;
		}
		if (tx.isActive())
		    tx.commit();
		s.close();
		return true;
   
		
	}

}
