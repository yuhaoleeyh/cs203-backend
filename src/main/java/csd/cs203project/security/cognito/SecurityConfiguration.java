package csd.cs203project.security.cognito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AwsCognitoJwtAuthFilter awsCognitoJwtAuthenticationFilter;

	
	/** 
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.headers().cacheControl();
		
		http
		.cors()
			.and()
		.csrf().disable()
			.authorizeRequests()
			/** Permit all requests at these endpoints */
			.antMatchers("**/health").permitAll()
			.antMatchers("/cognito/all-allow").permitAll()

			/** Measures */
			.antMatchers(HttpMethod.PUT, "/measures").hasAnyRole("SUPERVISOR", "ADMIN")

			/** News Articles */
			.antMatchers(HttpMethod.POST, "/newsArticle").hasAnyRole("ADMIN")

			/** Shops */
			.antMatchers(HttpMethod.POST, "/shops").hasAnyRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/shops/*").hasAnyRole("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/shops/*").hasAnyRole("ADMIN")

			/** Supervisor for Employees */
			.antMatchers(HttpMethod.GET, "/employees/**").hasAnyRole("SUPERVISOR", "ADMIN")
			.antMatchers(HttpMethod.POST, "/employees").hasAnyRole("SUPERVISOR", "ADMIN")
			.antMatchers(HttpMethod.PUT, "/employees/**").hasAnyRole("SUPERVISOR", "ADMIN")
			.antMatchers(HttpMethod.DELETE, "/employees/**").hasAnyRole("SUPERVISOR", "ADMIN")

			/** Table Layouts */
			.antMatchers(HttpMethod.POST, "/tablelayout").hasAnyRole("SUPERVISOR", "ADMIN")

			/** Users */
			.antMatchers(HttpMethod.POST, "/users").hasAnyRole("SUPERVISOR", "ADMIN")

			/** All other requests */
			.anyRequest().permitAll()
			.and()
			.addFilterBefore(awsCognitoJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
