package com.gatech.payroll.EmployeePayrollSystem.security;

import com.gatech.payroll.EmployeePayrollSystem.model.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class AppSecurity {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Only for testing purposes
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .cors(cors -> cors.disable()) // Disable CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.DELETE).hasRole(UserRole.admin.name())
                        .requestMatchers(HttpMethod.POST).hasRole(UserRole.admin.name())
                        .requestMatchers(HttpMethod.PUT).hasRole(UserRole.admin.name())
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());

        // For H2 console access
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("test"))
                .roles(UserRole.admin.name())
                .build();

        UserDetails readerUser = User.builder()
                .username("reader")
                .password(passwordEncoder().encode("test"))
                .roles(UserRole.reader.name())
                .build();

        return new InMemoryUserDetailsManager(adminUser, readerUser);
    }
}
