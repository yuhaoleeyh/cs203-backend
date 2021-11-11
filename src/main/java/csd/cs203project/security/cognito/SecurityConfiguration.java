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
			.antMatchers("**/health").permitAll()
			.antMatchers("/cognito/all-allow").permitAll()

			/** Measures */
			.antMatchers(HttpMethod.PUT, "/measures").hasAnyRole("ADMIN", "PROF")

			/** News Articles */
			.antMatchers(HttpMethod.POST, "/newsArticle").hasAnyRole("ADMIN", "PROF")
			
			/** Shops */
			.antMatchers(HttpMethod.POST, "/shops").hasAnyRole("ADMIN", "PROF")
			.antMatchers(HttpMethod.PUT, "/shops/*").hasAnyRole("ADMIN", "PROF")
			.antMatchers(HttpMethod.DELETE, "/shops/*").hasAnyRole("ADMIN", "PROF")

			/** Table Layouts */
			.antMatchers(HttpMethod.POST, "/tablelayout").hasAnyRole("EMPLOYEE", "SUPERVISOR", "ADMIN", "PROF")

			/** Users */
			.antMatchers(HttpMethod.GET, "/users/supervisor/*").hasAnyRole("SUPERVISOR", "ADMIN", "PROF")
			.antMatchers(HttpMethod.GET, "/users/administrator/*").hasAnyRole("ADMIN", "PROF")
			.antMatchers(HttpMethod.POST, "/users").hasAnyRole("SUPERVISOR", "ADMIN", "PROF")
			.antMatchers(HttpMethod.PUT, "/users/*").hasAnyRole("SUPERVISOR", "ADMIN", "PROF")
			.antMatchers(HttpMethod.DELETE, "/users/*").hasAnyRole("SUPERVISOR", "ADMIN", "PROF")

			/** Remaining requests */
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