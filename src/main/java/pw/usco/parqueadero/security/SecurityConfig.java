package pw.usco.parqueadero.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import pw.usco.parqueadero.service.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/home").hasAnyAuthority("USER","ADMIN");
                    auth.requestMatchers("/events/create").hasAnyAuthority("ADMIN");
                    auth.requestMatchers("/events/edit/**").hasAnyAuthority("ADMIN");
                    auth.requestMatchers("/events/delete/**").hasAnyAuthority("ADMIN");
                    auth.anyRequest().authenticated();
                })
                .formLogin(login -> {
                    login.loginPage("/login");
                    login.successHandler(successHandler());
                    login.permitAll();
                })
                .logout(logout -> {
                    logout.logoutUrl("/logout");
                    logout.logoutSuccessUrl("/login");
                })
                .exceptionHandling(exception -> {
                    exception.accessDeniedHandler(deniedHandler());
                })
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsServiceImp userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* Handlers */
    @Bean
    public AccessDeniedHandler deniedHandler() {
        return (request, response, auth) -> {
            response.sendRedirect("/403");
        };
    }

    @Bean
    public AuthenticationSuccessHandler successHandler () {
        return (request, response, auth) -> {
            response.sendRedirect("/home");
        };
    }
}
