package by.schesnovich.CarRentApp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static by.schesnovich.CarRentApp.security.Permission.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/registration").permitAll()
                .antMatchers("/user", "/user/*").hasAnyAuthority(USER_GET.name(),
                        USER_DELETE.name(),
                        USER_UPDATE.name())
                .antMatchers(HttpMethod.GET, "/car").hasAuthority(CAR_GET.name())
                .antMatchers("/car", "/car/*").hasAnyAuthority(CAR_CREATE.name(),
                        CAR_GET.name(),
                        CAR_DELETE.name(),
                        CAR_UPDATE.name())
                .antMatchers(HttpMethod.POST, "/rent").hasAuthority(RENT_CREATE.name())
                .antMatchers(HttpMethod.PUT, "/rent/*/*").hasAuthority(RENT_FINISH.name())
                .antMatchers("/rent", "/rent/*").hasAnyAuthority(RENT_GET.name(),
                        RENT_DELETE.name(),
                        RENT_UPDATE.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/car", true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
