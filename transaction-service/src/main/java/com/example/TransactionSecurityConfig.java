package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class TransactionSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    TransactionService transactionService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(transactionService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/transact/**").hasAuthority("usr")
                .and().formLogin();
    }

    @Bean
    PasswordEncoder getPE() {

        return new BCryptPasswordEncoder();
    }

    //how the authentication happens b/w different services
    // in scenario you want txn api's - transact should only be called by authenticator user not be open (not anybod can useit)
    //you written security config  that will allow only the who has authority like users, even not admin ( only users can call transact api(perform authentication))
    //in spring security there was one user service db authentication
    //In spring security, there was one user service which has one load user by username method which basically find the user
    //details from data base, performs the checking whether the password, username and will authenticate the user based on that
    //db authentication

}