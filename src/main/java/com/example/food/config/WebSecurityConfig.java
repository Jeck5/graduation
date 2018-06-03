package com.example.food.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {//TODO uncomment
//        http.authorizeRequests()
//                .antMatchers("/rest/profile/**")
//                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/rest/admin/**")
//                .access("hasRole('ROLE_ADMIN')")
//                .anyRequest().authenticated();
//        http.httpBasic().and().csrf().disable();
    }

}