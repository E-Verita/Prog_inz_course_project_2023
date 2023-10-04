package lv.venta.confs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lv.venta.services.impl.security.MyUserDetailsManagerImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // userDetailsManager - nodrošin?t lietot?jus un to lomas

    // SimpleSecurityChain - nodrošin?t piek?uvi noteiktiem endpointiem noteiktiem
    // lietot?jiem

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
	}

    @Bean
    MyUserDetailsManagerImpl userDetailsManager() {
		MyUserDetailsManagerImpl manager = new MyUserDetailsManagerImpl();
		return manager;
	}

	@Bean
	PasswordEncoder passwordEncoderSimple2() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder autenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		autenticationManagerBuilder.userDetailsService(userDetailsManager()).passwordEncoder(passwordEncoderSimple2());
		return autenticationManagerBuilder.build();
	}
	 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers("/hello").permitAll()
			.requestMatchers("/student/**").hasAnyAuthority("STUDENT")
			.requestMatchers("/professor/**").hasAnyAuthority("PROFESSOR")
			.requestMatchers("/error").permitAll().and()
			.formLogin().permitAll().and().logout().permitAll().and().exceptionHandling();
		return http.build();
	}
	
	

}
