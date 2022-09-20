package com.swa.paypal.config;

import com.example.commonmodule.security.JwtTokenFilter;
import com.example.commonmodule.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Value("${app.jwt.token.secret-key}")
    private String secretKey;

    @Value("${app.jwt.token.expire-seconds}")
    private long validityInSeconds;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        return http.csrf().disable().httpBasic().and()
                .authorizeRequests(ar -> ar
//                       TODO, Permitting paypals endpoint, just for development
                        .antMatchers("/paypals/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(eh -> eh
                        .authenticationEntryPoint((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage()))
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider()), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    public JwtTokenProvider jwtTokenProvider() {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(secretKey, validityInSeconds);
        return jwtTokenProvider;
    }
}