package ma.octo.assignement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	 	   auth
	        .inMemoryAuthentication()
	        //We can add password storage format, which is {noop} for plain text passwords.
	        .withUser("admin").password("{noop}1234").roles("ADMIN","USER");
	 	   auth
	        .inMemoryAuthentication()
	        .withUser("user").password("{noop}1234").roles("USER");
	 }
	    @Override
	 	protected void configure(HttpSecurity http) throws Exception {
	    	http.csrf().disable();
	    	http.headers().frameOptions().disable();
            http.formLogin();
	 	   http.authorizeRequests().antMatchers("/listOfAccounts").authenticated();
	 	  
	 	   http.authorizeRequests().antMatchers("/deposits").authenticated();

	       http.exceptionHandling().accessDeniedPage("/403");
	    }
}
