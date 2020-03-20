package web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import database.SessionManager;
import forms.Search;
import forms.SearchForm;
import model.Account;
import model.User;


@Controller
public class Tier2PendingAccounts{
	
	
	@RequestMapping("/Tier2PendingAccountView")
    public ModelAndView retrievePendingAccounts(Model model) {
		
		Authentication x = SecurityContextHolder.getContext().getAuthentication();
//		if (x == null || !x.isAuthenticated()) {
//			System.out.println("NOT AUTHENTICATED");
//			return new ModelAndView("Login");
//		}
		Boolean isTier2=false;
		for (GrantedAuthority grantedAuthority : x.getAuthorities()) {
			  System.out.println(grantedAuthority.getAuthority());
			    if (grantedAuthority.getAuthority().equals("tier2")) {
			    	System.out.println("Tier 2 Success");
			        isTier2 = true;
			        break;
			    }
			}
		if(isTier2)
		{
		Session s = SessionManager.getSession("");
		List<Account> account=null;
		account=s.createQuery("FROM Account where status=0", Account.class)
				 .getResultList();
		
		
		SearchForm searchForm = new SearchForm();
		//ArrayList<Search> search=new ArrayList<>();
		List<Search> search = new ArrayList<Search>();
		for(Account temp : account )
		{
			Boolean status=false;
			if(temp.getStatus()==1)
				status=true;
			Search tempSearch=new Search(temp.getAccountNumber(),temp.getCurrentBalance()+"",status);
			
			if(temp.getUser().getRole().equals("customer"))
			search.add(tempSearch);
			
		}
		searchForm.setSearchs(search);
		return new ModelAndView("Tier2PendingAccounts" , "searchForm", searchForm);
	
		}
		else
			return new ModelAndView("Login");
  
        
    }
	

	@RequestMapping(value = "/Tier2AuthAcc", method = RequestMethod.POST)
    public ModelAndView authorizeAccount(@RequestParam(required = true, name="accountnumber") String accNumber,Model model) throws ParseException {
		
		System.out.println("++++++++"+accNumber);
		Authentication x = SecurityContextHolder.getContext().getAuthentication();
//		if (x == null || !x.isAuthenticated()) {
//			System.out.println("NOT AUTHENTICATED");
//			return new ModelAndView("Login");
//		}
		Boolean isTier2=false;
		for (GrantedAuthority grantedAuthority : x.getAuthorities()) {
			  System.out.println(grantedAuthority.getAuthority());
			    if (grantedAuthority.getAuthority().equals("tier2")) {
			    	System.out.println("Tier 2 Success");
			        isTier2 = true;
			        break;
			    }
			}
		if(isTier2)
		{
		Session s = SessionManager.getSession("");
		List<Account> account=null;
		System.out.println("Came here");
		account=s.createQuery("FROM Account WHERE account_number = :accountNumber", Account.class)
				.setParameter("accountNumber", accNumber).getResultList();
		//ArrayList<Search> search=new ArrayList<>()
		Transaction tx = null;
		tx = s.beginTransaction();
		for(Account temp : account )
		{
//			Boolean status=false;
//			if(temp.getStatus()==1)
//				status=true;
//			Search tempSearch=new Search(temp.getAccountNumber(),temp.getCurrentBalance()+"",status);
//			
//			if(temp.getUser().getRole().equals("customer"))
//			search.add(tempSearch);	
//			System.out.println(temp.getUser().getRole());
			
			if(temp.getStatus()==0 && temp.getUser().getRole().equals("customer"))
			{
				temp.setStatus(1);
				
				DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
				Date date = new Date();
				Date d = new SimpleDateFormat("mm-dd-yyyy").parse(dateFormat.format(date));
				temp.setApprovalDate(d);
				s.saveOrUpdate(temp);
			}
			else
			{
				if (tx.isActive())
				    tx.commit();
				s.close();
				return new ModelAndView("redirect:/Tier2PendingAccountView");
			}
			
	
		}

		
		if (tx.isActive())
		    tx.commit();
		s.close();
		
		
		
		
		
		return new ModelAndView("redirect:/Tier2PendingAccountView");
	
		}
		else
			return new ModelAndView("Login");
  
        
    }
	@RequestMapping(value = "/Tier2DecAcc", method = RequestMethod.POST)
    public ModelAndView declineAccount(@RequestParam(required = true, name="accountnumber") String accNumber,Model model) throws ParseException {
		
		System.out.println("$$$$$$$$$$$$"+accNumber);
		Authentication x = SecurityContextHolder.getContext().getAuthentication();
//		if (x == null || !x.isAuthenticated()) {
//			System.out.println("NOT AUTHENTICATED");
//			return new ModelAndView("Login");
//		}
		Boolean isTier2=false;
		for (GrantedAuthority grantedAuthority : x.getAuthorities()) {
			  System.out.println(grantedAuthority.getAuthority());
			    if (grantedAuthority.getAuthority().equals("tier2")) {
			    	System.out.println("Tier 2 Success");
			        isTier2 = true;
			        break;
			    }
			}
		if(isTier2)
		{
		Session s = SessionManager.getSession("");
		List<Account> account=null;
		System.out.println("Came here");
		account=s.createQuery("FROM Account WHERE account_number = :accountNumber", Account.class)
				.setParameter("accountNumber", accNumber).getResultList();
		//ArrayList<Search> search=new ArrayList<>()
		Transaction tx = null;
		tx = s.beginTransaction();
		for(Account temp : account )
		{
//			Boolean status=false;
//			if(temp.getStatus()==1)
//				status=true;
//			Search tempSearch=new Search(temp.getAccountNumber(),temp.getCurrentBalance()+"",status);
//			
//			if(temp.getUser().getRole().equals("customer"))
//			search.add(tempSearch);	
//			System.out.println(temp.getUser().getRole());
			if(temp.getStatus()==0 && temp.getUser().getRole().equals("customer"))
			{
				temp.setStatus(2);
				
				DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
				Date date = new Date();
				Date d = new SimpleDateFormat("mm-dd-yyyy").parse(dateFormat.format(date));
				temp.setApprovalDate(d);
				s.saveOrUpdate(temp);
			}
			else
			{
				if (tx.isActive())
				    tx.commit();
				s.close();
				return new ModelAndView("redirect:/Tier2PendingAccountView");
			}
				
			
			
		}

		
		if (tx.isActive())
		    tx.commit();
		s.close();
				
		return new ModelAndView("redirect:/Tier2PendingAccountView");
	
		}
		else
			return new ModelAndView("Login");
  
        
    }
	
	
	
	
	

}
