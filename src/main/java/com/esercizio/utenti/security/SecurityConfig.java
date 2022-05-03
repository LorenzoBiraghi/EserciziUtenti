package com.esercizio.utenti.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //we want to check users from db
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/login/**").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/**").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/**").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/**").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/users/**").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .addFilter(new CustomAuthenticationFilter(super.authenticationManagerBean()))
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.headers().cacheControl();
    }
}
