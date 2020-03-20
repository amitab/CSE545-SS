package web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import database.SessionManager;
import model.Account;

@Controller
public class Tier2DeleteAccountsController {
	@RequestMapping(value = "/Tier2DelAcc", method = RequestMethod.POST)
    public ModelAndView deleteAccount(@RequestParam(required = true, name="accountnumber") String accNumber,Model model) throws ParseException {
		
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
		account=s.createQuery("FROM Account WHERE account_number = :accountNumber AND status=1", Account.class)
				.setParameter("accountNumber", accNumber).getResultList();
		//ArrayList<Search> search=new ArrayList<>()
		System.out.println(account.size()+"SIZEEEEE");
		if(account.size()==0)
		{
			return new ModelAndView("Tier2DeleteAccount","message","An active account not found");
		}
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
			
			if(temp.getUser().getRole().equals("customer"))
			{
				temp.setStatus(3);
				s.saveOrUpdate(temp);
			}
			
	
		}

		
		if (tx.isActive())
		    tx.commit();
		s.close();
		
		
		
		
		return new ModelAndView("Tier2DeleteAccount","message","The account was successfully deleted");
	
		}
		else
			return new ModelAndView("Login");
  
        
    } 
}
