package com.examly.springapp.config;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
        .cors(cors -> {
                    cors.configurationSource(new CorsConfigurationSource() {
                        @Override
                        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                            CorsConfiguration cfg = new CorsConfiguration();
                            cfg.setAllowedOriginPatterns(Collections.singletonList("investtrack.up.railway.app"));//"https://8081-aeecdbdbcbbfcaabecebeddbadd.premiumproject.examly.io")) 
                            cfg.setAllowedMethods(Collections.singletonList("*"));        
                            cfg.setAllowCredentials(true);
                            cfg.setAllowedHeaders(Collections.singletonList("*"));
                            cfg.setExposedHeaders(Arrays.asList("Authorization"));

                            return cfg;

                        }
                    });
                })
        


                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/login",
                                "/api/register")
                        .permitAll()
                        .requestMatchers("/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/investments").hasRole("Admin")
                        .requestMatchers( HttpMethod.POST, "/api/feedback").hasRole("User")//.permitAll()//.hasRole("User")
                        .requestMatchers( HttpMethod.GET, "/api/feedback").hasRole("Admin")
                        .requestMatchers( HttpMethod.GET, "/api/feedback/{feedbackId}").hasAnyRole("Admin", "User")
                        .requestMatchers(HttpMethod.GET, "/api/feedback/user/{userId}").hasRole("User")
                        .requestMatchers(HttpMethod.GET, "/api/investments/{investmentId}").permitAll() //changed
                        .requestMatchers(HttpMethod.GET, "/api/inquiries").hasRole("Admin")
                        .requestMatchers(HttpMethod.PUT, "/api/investments/{investmentId}").hasRole("Admin")
                        .requestMatchers(HttpMethod.POST, "/api/inquiries").hasRole("User")
                        .requestMatchers(HttpMethod.GET, "/api/investments").permitAll()  //hasAnyRole("Admin", "User")
                        .requestMatchers(HttpMethod.DELETE, "/api/investments/{investmentId}").hasRole("Admin")
                        .requestMatchers(HttpMethod.GET, "/api/inquiries/{inquiryId}").hasAnyRole("Admin", "User")
                        .requestMatchers(HttpMethod.GET, "/api/inquiries/user/{userId}").hasRole("User")
                        .requestMatchers(HttpMethod.GET, "/api/inquiries").hasRole("Admin")
                        .requestMatchers(HttpMethod.PUT,"/api/inquiries/{inquiryId}").hasRole("Admin")
                        .requestMatchers(HttpMethod.DELETE,"/api/inquiries/{inquiryId}").hasRole("Admin")
                        .requestMatchers(HttpMethod.DELETE,"/api/feedback/{feedbackId}").hasRole("User")
                        .anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();     
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

