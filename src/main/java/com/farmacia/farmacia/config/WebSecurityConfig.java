package com.farmacia.farmacia.config;

import com.farmacia.farmacia.service.UsuarioService;
import com.farmacia.farmacia.utils.ERol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UsuarioService usuarioService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .userDetailsService(usuarioService)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry
                            //.requestMatchers("/index").hasAnyAuthority(ERol.ROLE_USER.name())
                            //.requestMatchers("/prueba").hasAnyAuthority(ERol.ROLE_ADMIN.name())
                            //.requestMatchers("/dashboard").hasAuthority(ERol.ROLE_ADMIN.name())
                            //.permitAll()
                            //.anyRequest()
                            //.authenticated();
                            .anyRequest().permitAll();
                }).formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .loginPage("/login")
                            .successHandler(this.successHandler())
                            .permitAll();
                }).logout(httpSecurityLogoutConfigurer -> {
                    httpSecurityLogoutConfigurer
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .logoutSuccessUrl("/login?logout")
                            .permitAll();
                }).sessionManagement(sessionManagementConfigurer ->{
                    sessionManagementConfigurer
                            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                            .invalidSessionUrl("/login")
                            .sessionFixation(sessionFixationConfigurer -> {
                                sessionFixationConfigurer.migrateSession();
                            })
                            .maximumSessions(1)
                            .expiredUrl("/login");
                }).build();
    }

    AuthenticationSuccessHandler successHandler(){
        return (request, response, authentication) -> {
            response.sendRedirect("/index");
        };
    }
}

