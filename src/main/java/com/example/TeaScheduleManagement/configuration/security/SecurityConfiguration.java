package com.example.TeaScheduleManagement.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails admin = User.withUsername("ankan").password(encoder.encode("123456")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .formLogin(form -> form.loginPage("/login").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/images/**","/assets/**", "/css/**", "/js/**","/login").permitAll().anyRequest().authenticated())
//                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/",true).permitAll())
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/admin").authenticated())
//                .logout(logout-> logout.logoutUrl("/sign-out").logoutSuccessUrl("/"))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
