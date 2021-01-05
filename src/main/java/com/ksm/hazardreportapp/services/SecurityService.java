/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.services;

import com.ksm.hazardreportapp.providers.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author kelvi
 */
@Configuration
@EnableWebSecurity
public class SecurityService extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/demo/**").permitAll()
                .antMatchers("/serviceWorker.js").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/manifest.json").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/icons/**").permitAll()
                .antMatchers("/forgotpassword/**").permitAll()
                .antMatchers("/uploads/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/fladmin/**").hasAuthority("FW")
                .antMatchers("/admin/user/**").hasAuthority("HSE")
                .antMatchers("/admin/floor/**").hasAuthority("HSE")
                .antMatchers("/admin/recap/**").hasAuthority("HSE")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/admin", true)
                .failureUrl("/auth/login?error=login-error")
                .and()
                .httpBasic()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");
    }
}
