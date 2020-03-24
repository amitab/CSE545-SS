package web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Resource(name = "userDetailService")
	private UserDetailsService userDetailsService;
	
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	   
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    	 auth.userDetailsService(userDetailsService);
    }

    
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new AuthSuccess();
    }
 
    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new AuthFail();
    }
 
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
	        .antMatchers("/users/**").hasRole("USER")//USER role can access /users/**
	        .antMatchers("/admin/**").hasRole("ADMIN")
	        .antMatchers("/Tier2/**").hasAuthority("tier2") 
	        .antMatchers("/Admin/**").hasAuthority("admin")
	        .antMatchers("/login").permitAll()// anyone can access /quests/**
	        .antMatchers("/externalregister").permitAll()// anyone can access /quests/**
	        .antMatchers("/register").permitAll()// anyone can access /quests/**
	        .antMatchers("/Update").permitAll()
	        .antMatchers("/Search").permitAll()
	        .antMatchers("/ChangeValue").permitAll()
	        .antMatchers("/Appointment").permitAll()
	        .antMatchers("/AppointmentCreate").permitAll()
	        .antMatchers("/Download").permitAll()
	        .antMatchers("/forgot_password").permitAll()
	        .antMatchers("/reset_password").permitAll()
	        .antMatchers("/change_password").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
	        .antMatchers("/Tier1Dashboard").hasAuthority("tier1")
	        .antMatchers("/Tier1PendingTransactions").hasAuthority("tier1")
	        .antMatchers("/Tier1UpdatePassword").hasAuthority("tier1")
	        .antMatchers("/Tier1DepositMoney").hasAuthority("tier1")
	        .antMatchers("/Tier1WithdrawMoney").hasAuthority("tier1")
	        .antMatchers("/IssueCheque").hasAuthority("tier1")
	        .antMatchers("/AdminDashboard").hasAuthority("admin")
	        .antMatchers("/EmployeeView").hasAuthority("admin")
	        .antMatchers("/EmployeeInsert").hasAuthority("admin")
	        .antMatchers("/EmployeeUpdate").hasAuthority("admin")
	        .antMatchers("/EmployeeDelete").hasAuthority("admin")
	        .antMatchers("/SystemLogs").hasAuthority("admin")
	        .antMatchers("/homepage").hasAuthority("customer")
	        .anyRequest().authenticated()//any other request just need authentication
	        .and()
	        .formLogin()
	        .loginPage("/login")
	        .loginProcessingUrl("/process_login")
	        .successHandler(myAuthenticationSuccessHandler())
	        .failureHandler(customAuthenticationFailureHandler())
		    .and()
		    .logout()
		    .logoutUrl("/perform_logout")
		    .invalidateHttpSession(false)
		    .deleteCookies("JSESSIONID")
		    .logoutSuccessUrl("/login");
    }
     
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}