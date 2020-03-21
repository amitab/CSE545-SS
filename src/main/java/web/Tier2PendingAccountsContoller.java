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
public class Tier2PendingAccountsContoller{
	
	
	@RequestMapping("/Tier2PendingAccountView")
    public ModelAndView retrievePendingAccounts(Model model) {
		
		
		AccountServicesImpl accountServicesImpl = new AccountServicesImpl();
		
		SearchForm searchForm=accountServicesImpl.getAllPendingAccounts();

		if(searchForm==null)
			return new ModelAndView("Login");
		else
		return new ModelAndView("Tier2PendingAccounts" , "searchForm", searchForm);
        
    }
	

	@RequestMapping(value = "/Tier2AuthAcc", method = RequestMethod.POST)
    public ModelAndView authorizeAccount(@RequestParam(required = true, name="accountnumber") String accNumber,Model model) throws ParseException {
		
		AccountServicesImpl accountServicesImpl = new AccountServicesImpl();
		
		Boolean flag=accountServicesImpl.authorizeAccounts(accNumber);
		if(flag==null)
			return new ModelAndView("Login");
		else
			return new ModelAndView("redirect:/Tier2PendingAccountView");  
        
    }
	
	@RequestMapping(value = "/Tier2DecAcc", method = RequestMethod.POST)
    public ModelAndView declineAccount(@RequestParam(required = true, name="accountnumber") String accNumber,Model model) throws ParseException {
		
	AccountServicesImpl accountServicesImpl = new AccountServicesImpl();
		
		Boolean flag=accountServicesImpl.declineAccounts(accNumber);
		if(flag==null)
			return new ModelAndView("Login");
		else
			return new ModelAndView("redirect:/Tier2PendingAccountView");  
  
        
    }
	
	
	
	
	

}
