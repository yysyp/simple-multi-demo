package com.jcompetence.opa.and.inmemory.authentication.security;

import com.jcompetence.opa.and.inmemory.authentication.security.opa.OPAAuthorizationManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;


@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration {


    @Autowired
    private OPAAuthorizationManager opaAuthorizationManager;


    @Bean
    public UserDetailsService userDetailsService() {

        var userDetailsService =
                new InMemoryUserDetailsManager();

        var adminUser = User.withUsername("Bobby")
                .password(getPasswordEncoder().encode("password"))
                .authorities("all")
                .build();

        var adminUser1 = User.withUsername("Patrick")
                .password(getPasswordEncoder().encode("password"))
                .authorities("all")
                .build();

        var readUser = User.withUsername("John")
                .password(getPasswordEncoder().encode("password"))
                .authorities("read", "write")
                .build();

        var writeUser = User.withUsername("Milly")
                .password(getPasswordEncoder().encode("password"))
                .authorities("write")
                .build();


        userDetailsService.createUser(adminUser);
        userDetailsService.createUser(adminUser1);
        userDetailsService.createUser(readUser);
        userDetailsService.createUser(writeUser);


        return userDetailsService;
    }
    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers(EndpointRequest.to("health")).permitAll()
                                .requestMatchers("/v1/users").access(opaAuthorizationManager)
                                .anyRequest().authenticated()
                )
                .httpBasic()
                .and()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
