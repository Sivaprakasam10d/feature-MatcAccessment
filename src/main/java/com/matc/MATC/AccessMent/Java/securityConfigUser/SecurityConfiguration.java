//package com.matc.MATC.AccessMent.Java.securityConfigUser;
//
//
//import com.matc.MATC.AccessMent.Java.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	    @Autowired
//	    private UserService userService;
//
//	    @Autowired
//	    private JwtFilter jwtFilter;
//
////	    @Override
////	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////
////	        auth.userDetailsService(userService);
////	    }
//	    @Bean
//		public PasswordEncoder passwordEncoder() {
//			return NoOpPasswordEncoder.getInstance();
//		}
//	    @Override
//	    @Bean
//	    public AuthenticationManager authenticationManagerBean() throws Exception {
//	        return super.authenticationManagerBean();
//	    }
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		auth.inMemoryAuthentication()
//				.passwordEncoder(encoder)
//				.withUser("admin")
//				.password(encoder.encode("admin123"))
//				.roles("ADMIN");
//	}
//
//	    @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//	    	http.csrf()
//            .disable()
//            .authorizeRequests()
//            .antMatchers("/api/v1/admin")
//            .permitAll()
//            .antMatchers("/swagger-ui*/**","/MATC/**").permitAll()
//			.antMatchers("/api/v1/login").permitAll()
//					.antMatchers("api/v1/address").access("hasRole('ADMIN')")
//            .anyRequest()
//            .authenticated()
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//	    }
//}
