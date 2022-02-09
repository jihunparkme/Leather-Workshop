package com.leather.workshop.global.config.security;

import com.leather.workshop.domain.login.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOauth2UserService customOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console 사용을 위한 disable 처리
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/img/**", "/js/**", "/vendor/**", "/h2-console/**").permitAll()
                    //ADMIN
                    .antMatchers("**/add", "**/edit").hasRole(Role.ADMIN.name())
                    .antMatchers(HttpMethod.POST, "/notice/**").hasRole(Role.ADMIN.name())
                    .antMatchers(HttpMethod.PUT, "/notice/**").hasRole(Role.ADMIN.name())
                    .antMatchers(HttpMethod.DELETE, "/notice/**").hasRole(Role.ADMIN.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOauth2UserService);

    }
}
