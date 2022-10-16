package com.platinouss.bookrecommend.config;

import com.platinouss.bookrecommend.service.AuthenticationProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProviderService authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin()
                .defaultSuccessUrl("/main", true);

        http.authorizeRequests()
                .mvcMatchers("user/add").permitAll()
                .mvcMatchers("book/add").hasRole("ADMIN")
                .mvcMatchers("/main").hasRole("MANAGER")
                .anyRequest().permitAll();
    }
}
