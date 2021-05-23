package ma.emsi.patients.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder passwordEncoder = passwordEncoder();

        auth.inMemoryAuthentication()
               .withUser("user")
               .password(passwordEncoder.encode("TEST")) // $2a$14$RKHXeB9JC5IhvuXiwkK/1eVRjy27yRAicEzXJeeRnBcjLzEUdrU/K : TEST // from MedicalOfficeManager
               .roles("USER");

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("TEST")) // $2a$14$RKHXeB9JC5IhvuXiwkK/1eVRjy27yRAicEzXJeeRnBcjLzEUdrU/K : TEST // from MedicalOfficeManager
                .roles("ADMIN","USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.httpBasic();
        http.formLogin();
        http.authorizeRequests()
                .antMatchers("/save**/**","/delete**/**", "/form**/**")
                .hasRole("ADMIN");

        http.exceptionHandling().accessDeniedPage("/notAuthorized");

        http.authorizeRequests().anyRequest().authenticated();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
