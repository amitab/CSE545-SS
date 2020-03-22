package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import database.SessionManager;
import forms.EmployeeSearchForm;
import forms.SearchForm;
import model.User;
import model.UserDetail;


@Controller
public class AdminController {
	
	@RequestMapping("/Admin/Dashboard")
    public String hello(final HttpServletRequest request, Model model) {
	    HttpSession session = request.getSession(false);
	    
	    if (session != null) {
		    Object msg = session.getAttribute("msg");
	        model.addAttribute("message", session.getAttribute("msg"));
	        if (msg != null)
	        	session.removeAttribute("msg");
	    }
        return "AdminDashboard";
    }

	@RequestMapping("/Admin/SearchEmployee")
    public String employeeView(final HttpServletRequest request, Model model) {
		return "AdminEmployeeSearch";
    }
	
	@RequestMapping("/Admin/CreateEmployee")
    public String employeeInsert(final HttpServletRequest request, Model model) {
		return "AdminEmployeeInsert";
    }
	
	@RequestMapping("/Admin/UpdateEmployee")
    public String employeeUpdate(final HttpServletRequest request, Model model) {
		return "AdminEmployeeUpdate";
    }

	@RequestMapping("/Admin/DeleteEmployee")
    public String employeeDelete(final HttpServletRequest request, Model model) {
		return "AdminEmployeeDelete";
    }
	
	@RequestMapping("/Admin/SystemLogs")
    public String systemLogs(final HttpServletRequest request, Model model) {
		return "SystemLogs";
    }
	
	@RequestMapping(value = "/Admin/Search", method = RequestMethod.POST)
    public ModelAndView adminSearchPage(@RequestParam(required = true, name="username") String username, Model model) {
		EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
		
		EmployeeSearchForm employeeSearchForm=employeeServiceImpl.getEmployees(username);
		if(employeeSearchForm==null)
			return new ModelAndView("Login");
		else
			if(employeeSearchForm.getEmployeeSearchs().size()==0)
				return new ModelAndView("AdminEmployeeSearch" , "message", "An username not found");
			else
				return new ModelAndView("AdminEmployeeSearch" , "employeeSearchForm", employeeSearchForm);  
    }
	
	@RequestMapping(value = "/Admin/UpdateSearch", method = RequestMethod.POST)
    public String adminUpdateSearchPage(@RequestParam(required = true, name="username") String username, Model model) {
		EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
		
		EmployeeSearchForm employeeSearchForm=employeeServiceImpl.getEmployees(username);
		if(employeeSearchForm==null)
			return "Login";
		else
			if(employeeSearchForm.getEmployeeSearchs().size()==0)
			{
				model.addAttribute("message", "An username not found");
				return "AdminEmployeeUpdate";
			}		
			else
				{
				System.out.println("CAME HERE!!!!!!");
				System.out.println(employeeSearchForm.employeeSearchs.get(0).getEmail());
				model.addAttribute("userName", username);
				model.addAttribute("email",employeeSearchForm.employeeSearchs.get(0).getEmail());
				model.addAttribute("firstName",employeeSearchForm.employeeSearchs.get(0).getFirstName());
				model.addAttribute("lastName",employeeSearchForm.employeeSearchs.get(0).getLastName());
				model.addAttribute("middleName",employeeSearchForm.employeeSearchs.get(0).getMiddleName());
				model.addAttribute("phoneNumber",employeeSearchForm.employeeSearchs.get(0).getPhoneNumber());
				return "AdminEmployeeUpdate";
				}
    }
	@RequestMapping(value = "/Admin/UpdateValues", method = RequestMethod.POST)
    public ModelAndView changeValue(
    		@RequestParam(required = true, name="userName") String userName,
    		@RequestParam(required = true, name="email") String email,
    		@RequestParam(required = true, name="firstName") String firstName,
    		@RequestParam(required = true, name="lastName") String lastName,
    		@RequestParam(required = true, name="middleName") String middleName,
    		@RequestParam(required = true, name="phoneNumber") String phoneNumber,
    		final HttpServletRequest request, Model model)  {
			
		EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
		Boolean flag=employeeServiceImpl.updateEmployees(userName, email, firstName, lastName, middleName, phoneNumber);
		
		if(flag==null)
			return new ModelAndView("Login");
		else
			if(flag)
				return new ModelAndView("AdminEmployeeUpdate" , "message", "The Info username was updated");
			else
				return new ModelAndView("AdminEmployeeUpdate" , "message", "An username not found");
    }
	
	@RequestMapping(value = "/Admin/DelEmployee", method = RequestMethod.POST)
    public ModelAndView deleteEmployee(
    		@RequestParam(required = true, name="firstName") String firstName,
    		@RequestParam(required = true, name="lastName") String lastName,
    		@RequestParam(required = true, name="userName") String userName,
    		final HttpServletRequest request, Model model)  {
			
		EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
		Boolean flag=employeeServiceImpl.deleteEmployees(userName,firstName, lastName);
		if(flag==null)
			return new ModelAndView("Login");
		else
			if(flag)
				return new ModelAndView("AdminEmployeeDelete" , "message", "The username was deleted");
			else
				return new ModelAndView("AdminEmployeeDelete" , "message", "An Employee account was not found");
    }
	


	
	
	
	
}