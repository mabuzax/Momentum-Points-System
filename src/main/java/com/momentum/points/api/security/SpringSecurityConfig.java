package com.momentum.points.api.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER");

    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/momentum/api/v1/product/list").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/momentum/api/v1/product/id/**").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/momentum/api/v1/product/name/**").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/momentum/api/v1/customer/list").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/momentum/api/v1/customer/id/**").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/momentum/api/v1/customer/name/**").hasRole("USER")
            .antMatchers(HttpMethod.POST, "/momentum/api/v1/purchase/**").hasRole("USER")            
            .and()
            .csrf().disable()
            .formLogin().disable();
    }

}