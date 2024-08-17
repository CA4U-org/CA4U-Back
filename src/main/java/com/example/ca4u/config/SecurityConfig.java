package com.example.ca4u.config;

import com.example.ca4u.config.auth.dto.CustomOAuth2UserService;
import com.example.ca4u.domain.member.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService; //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당하는 클래스

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrfConfig) ->
                        csrfConfig.disable())
                .headers((headerConfig) ->
                        headerConfig.frameOptions(frameOptionsConfig ->
                                frameOptionsConfig.disable())
                )
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/","/css/**", "/images/**","/js/**", "/h2-console/**").permitAll()
                                .requestMatchers("/api/v1/**").hasRole(Role.MEMBER.name())
                                .anyRequest().authenticated()
                )
                .logout((logoutConfig) ->
                        logoutConfig.logoutSuccessUrl("/"))

                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}
