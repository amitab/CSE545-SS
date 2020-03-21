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
	
		AccountServicesImpl accountServicesImpl = new AccountServicesImpl();
		
		Boolean flag=accountServicesImpl.deleteAccounts(accNumber);
		
		if(flag==null)
		{
			return new ModelAndView("Login");
		}
		else 
		{
			if(flag)
				return new ModelAndView("Tier2DeleteAccount","message","The account was successfully deleted");
			else
				return new ModelAndView("Tier2DeleteAccount","message","An active account was not found");
		}        
    } 
}
