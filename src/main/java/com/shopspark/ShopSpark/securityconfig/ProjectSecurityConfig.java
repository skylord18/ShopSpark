package com.shopspark.ShopSpark.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultsecurityfilterchain(HttpSecurity http)throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/product/add").hasRole("ADMIN")
                .requestMatchers("/listing/add").hasRole("SELLER")
                .requestMatchers("/feature/add").hasRole("ADMIN")
                .requestMatchers("*").permitAll()
                .anyRequest().permitAll()
                .and().formLogin()
                .and().logout().permitAll().invalidateHttpSession(true).permitAll()
                .and().httpBasic();
        return http.build();
    }
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin").password("admin").roles("ADMIN").build();
//        UserDetails seller = User.withDefaultPasswordEncoder()
//                .username("seller").password("seller").roles("SELLER").build();
//        UserDetails buyer = User.withDefaultPasswordEncoder()
//                .username("buyer").password("buyer").roles("USER").build();
//        return new InMemoryUserDetailsManager(admin, seller, buyer);
//    }
}
