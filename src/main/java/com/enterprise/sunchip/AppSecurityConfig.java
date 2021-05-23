//package com.enterprise.sunchip;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Below is actual spring security setup. Its not setup properly
// * But with a user, user for username and password.
// * Though its very annoying to type that every time during testing
// * Keep this way until we get shit figured out
// */
//
////@Configuration
////@EnableWebSecurity
////public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
////
////    @Bean
////    @Override
////    protected UserDetailsService userDetailsService()
////    {
////        List<UserDetails> users = new ArrayList<>();
////        users.add(User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build());
////        return new InMemoryUserDetailsManager(users);
////    }
////
////
////
////}
//
//@Configuration
//public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
//        http.headers().frameOptions().disable(); // allows connection to h2
//    }
//
//}