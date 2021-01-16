package com.gwy.manager.security;

import com.gwy.manager.domain.constant.PassRequestPaths;
import com.gwy.manager.security.filter.JwtLoginAuthenticationFilter;
import com.gwy.manager.security.handler.CustomizeLoginFailureHandler;
import com.gwy.manager.security.handler.CustomizeLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author Tracy
 * @date 2020/11/21 23:19
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
    CustomizeLoginSuccessHandler customizeLoginSuccessHandler;

    @Autowired
    CustomizeLoginFailureHandler customizeLoginFailureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //自定义用户验证和加密方式
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterAt(jwtLoginFilterBean(), UsernamePasswordAuthenticationFilter.class);
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(PassRequestPaths.LOGIN_REQUEST)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    private JwtLoginAuthenticationFilter jwtLoginFilterBean() throws Exception {
        JwtLoginAuthenticationFilter jwtLoginAuthenticationFilter = new JwtLoginAuthenticationFilter();
        jwtLoginAuthenticationFilter.setAuthenticationManager(authenticationManager());
        jwtLoginAuthenticationFilter.setAuthenticationSuccessHandler(customizeLoginSuccessHandler);
        jwtLoginAuthenticationFilter.setAuthenticationFailureHandler(customizeLoginFailureHandler);
        return jwtLoginAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
