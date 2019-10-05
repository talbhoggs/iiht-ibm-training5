package com.ibm.ph.amperca.iihtibm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/login").permitAll().
                antMatchers("/about").permitAll().
                antMatchers("/logout").permitAll().
                antMatchers("/signup").permitAll().
                antMatchers("/login/**").permitAll().
                antMatchers("/signup/**").permitAll().
                antMatchers("/h2-console/**").permitAll().
                antMatchers("/main").hasAnyAuthority("USER","ADMIN").
                antMatchers("/admin/**").hasAnyAuthority("ADMIN").anyRequest().authenticated().
                and().formLogin()
                .loginPage("/login").defaultSuccessUrl("/main", true).failureUrl("/login?error=true").and()
                .exceptionHandling().accessDeniedPage("/403").and().logout().logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true).permitAll().and().csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/*.html", "/**/*.css", "/**/*.js", "/**/*.{png,jpg,jpeg,svg.ico}");
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

}
