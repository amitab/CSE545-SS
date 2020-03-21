package web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
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
public class Tier2SearchAccountsController{
	
	
	@RequestMapping(value = "/Tier2Search", method = RequestMethod.POST)
    public ModelAndView tier2Page(@RequestParam(required = true, name="accountnumber") String accNumber, Model model) {

		AccountServicesImpl accountServicesImpl = new AccountServicesImpl();
		
		SearchForm searchForm=accountServicesImpl.getAccounts(accNumber);
		if(searchForm==null)
			return new ModelAndView("Login");
		else
		return new ModelAndView("Tier2SearchAccount" , "searchForm", searchForm);
  
        
    }
	

	
	
	
	
	

}
