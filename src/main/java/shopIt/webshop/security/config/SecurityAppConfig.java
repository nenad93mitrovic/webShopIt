package shopIt.webshop.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityAppConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("securityDataSource")
	private DataSource securityDataSource;
		
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// add our users for in memory authentication
		
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		
//		auth.inMemoryAuthentication()
//			.withUser(users.username("nesa").password("test").roles("USER"))
//			.withUser(users.username("ivanka").password("test").roles("USER", "MANAGER"))
//			.withUser(users.username("zaki").password("test").roles("USER", "ADMIN"));
//	}


			// use jdbc authentication ... oh yeah!!!		
			auth.jdbcAuthentication().dataSource(securityDataSource);
	}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/webapp/showForm*").hasAnyRole("MANAGER", "ADMIN")
			.antMatchers("/webapp/save*").hasAnyRole("MANAGER", "ADMIN")
			.antMatchers("/webapp/delete").hasRole("ADMIN")
			.antMatchers("/webapp/**").hasRole("USER")
			.antMatchers("/resources/**").permitAll()
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
	}
		
}






