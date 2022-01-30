package com.works.config;

import com.works.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
// application-test.properties yazdığım için
@Profile(value = "test")
@AllArgsConstructor
public class TestConfig extends WebSecurityConfigurerAdapter
{
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable();
        http.authorizeRequests().anyRequest().permitAll();
        http.authorizeRequests().and().formLogin().defaultSuccessUrl("/sessions");
        http.authorizeRequests().and().logout().logoutSuccessUrl("/login");
    }
}
