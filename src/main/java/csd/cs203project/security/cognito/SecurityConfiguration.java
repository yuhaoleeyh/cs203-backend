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

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.headers().cacheControl();
		
		// added this line
		http
		.cors()
			.and()
		.csrf().disable()
			.authorizeRequests()
			/** Permit all requests at these endpoints */
//			.antMatchers("**/health").permitAll()
//			.antMatchers("/cognito/all-allow").permitAll()
//
//			/** Measures */
//			.antMatchers(HttpMethod.PUT, "/measures").hasAnyRole("SUPERVISOR", "ADMIN", "PROF")
//
//			/** News Articles */
//			.antMatchers(HttpMethod.POST, "/newsArticle").hasAnyRole("ADMIN", "PROF")
//
//			/** Shops */
//			.antMatchers(HttpMethod.POST, "/shops").hasAnyRole("ADMIN", "PROF")
//			.antMatchers(HttpMethod.PUT, "/shops/*").hasAnyRole("ADMIN", "PROF")
//			.antMatchers(HttpMethod.DELETE, "/shops/*").hasAnyRole("ADMIN", "PROF")
//
//			/** Supervisor for Employees */
//			.antMatchers(HttpMethod.GET, "/employees/**").hasAnyRole("SUPERVISOR", "ADMIN", "PROF")
//			.antMatchers(HttpMethod.POST, "/employees").hasAnyRole("SUPERVISOR", "ADMIN", "PROF")
//			.antMatchers(HttpMethod.PUT, "/employees/**").hasAnyRole("SUPERVISOR", "ADMIN", "PROF")
//			.antMatchers(HttpMethod.DELETE, "/employees/**").hasAnyRole("SUPERVISOR", "ADMIN", "PROF")
//
//			/** Table Layouts */
//			.antMatchers(HttpMethod.POST, "/tablelayout").hasAnyRole("SUPERVISOR", "ADMIN", "PROF")
//
//			/** Users */
//			.antMatchers(HttpMethod.POST, "/users").hasAnyRole("SUPERVISOR", "ADMIN", "PROF")

			/** All other requests do not need to be authenticated */
			.anyRequest().permitAll()
			.and()
			.addFilterBefore(awsCognitoJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	// @Bean
    // public WebMvcConfigurer corsConfigurer() {
    //     return new WebMvcConfigurer() {
    //         @Override
    //         public void addCorsMappings(CorsRegistry registry) {
    //             registry.addMapping("/**");
    //         }
    //     };
    // }
}