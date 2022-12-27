package com.spring.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

import static org.springframework.security.config.http.MatcherType.ant;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService() throws Exception{
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(
                User.withUsername("anakin")
                        .password(encoder().encode("anakin201"))
                        //.roles("ADMIN")
                        .authorities("ACCESS_BASIC1","ROLE_ADMIN")
                        .build()
        );

        manager.createUser(
                User.withUsername("user")
                        .password(encoder().encode("user201"))
                        .roles("USER")
                        .build()
        );

        manager.createUser(
                User.withUsername("manager")
                        .password(encoder().encode("manager201"))
                        .roles("MANGER")
                        .authorities("ACCESS_BASIC2")
                        .build()
        );

        return manager;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authCustomizer -> authCustomizer
                                .requestMatchers("/api/main").permitAll()
                                .requestMatchers("/api/profile").authenticated()
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/manage").hasAnyRole("ADMIN","MANGER")
                                .requestMatchers("/api/basic/mybasic").hasAuthority("ACCESS_BASIC1")
                                .requestMatchers("/api/basic/allbasic").hasAuthority("ACCESS_BASIC2")
                                //.anyRequest().permitAll()

                        //.anyRequest().permitAll()

                        //.requestMatchers("/api/main").permitAll()
                        //.requestMatchers("/api/profile").authenticated()
                )
                .build();

    }


    @Bean
    public static PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }




}

    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/