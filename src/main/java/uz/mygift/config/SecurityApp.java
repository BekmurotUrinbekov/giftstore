package uz.mygift.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import uz.mygift.service.UserDetService;

import static org.springframework.security.config.Customizer.withDefaults;


@EnableWebSecurity
@Component
@RequiredArgsConstructor
public class SecurityApp {

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    @Order(1)
    public SecurityFilterChain filterChainWithHTTPBasic(HttpSecurity http) throws Exception {
        http
                .securityMatcher(AntPathRequestMatcher.antMatcher( "/user/**"))
                .authorizeRequests(authorize -> {
                             authorize
                                    .requestMatchers(HttpMethod.GET, "/user/generate","/user/generate/refresh").authenticated();
                             authorize
                                     .requestMatchers(HttpMethod.POST,"/user/create").permitAll();
                        }
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(csrf->csrf.ignoringRequestMatchers("/user/**"))
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChainWithJWT(HttpSecurity http) throws Exception {
        http
                .securityMatcher(AntPathRequestMatcher.antMatcher("/**"))
                .authorizeRequests(authorize -> {
                             authorize
                                     .requestMatchers(HttpMethod.POST,"/user/create").hasAnyRole("ADMIN");
                            authorize
                                    .anyRequest().authenticated();
                        }
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(csrf->csrf.ignoringRequestMatchers("/**","/image/**"))
                .authenticationProvider(daoAuthenticationProvider)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }





}
