package com.GestionTicket.applications.MyTicket.Configuration;

import com.GestionTicket.applications.MyTicket.Services.CustimSuccessHander;
import com.GestionTicket.applications.MyTicket.Services.CustimUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ConfigSecurityConfig {

    @Autowired
    CustimSuccessHander custimSuccessHander;
    @Autowired
    CustimUserDetailService custimUserDetailService;

    @Bean
    public static PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder ();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

            http.csrf(c ->c.disable())
                    .authorizeHttpRequests(request-> request.requestMatchers("/admin-page")
                    .hasAuthority("Admin").requestMatchers("/user-page").hasAuthority("User")
                            .requestMatchers("/","/RegisterBillet","/acheter-ticket","/saveClient","/PassagerReservation","/PassagerBillet","/addPassager","/addPassagerB","/newReservation","/savePassager","/savePassagerB","/css/**","/img/**").permitAll()
                    .requestMatchers("/registration", "/css/**").permitAll()
                    .anyRequest().authenticated())
                    .formLogin(form->form.loginPage("/login").loginProcessingUrl("/login")
                            .defaultSuccessUrl("/home")
                            .successHandler(custimSuccessHander).permitAll())
                    .logout(form ->form.invalidateHttpSession(true).clearAuthentication(true)
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .logoutSuccessUrl("/login?logout").permitAll());

            return http.build();
    }
        @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(custimUserDetailService).passwordEncoder(passwordEncoder());

        }
}
