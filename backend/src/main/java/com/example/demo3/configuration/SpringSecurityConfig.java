package com.example.demo3.configuration;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
   // @Override
   // public UserDetailsService userDetailsServiceBean() throws Exception {
    //    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
      //  manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
       // return manager;
    //}

    @Override
    public void configure (HttpSecurity http) throws Exception {

        http    .httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST).permitAll()//allow CORS option calls
                .antMatchers("/user/**").permitAll()

                .anyRequest().authenticated();
        http
                .csrf().disable();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
