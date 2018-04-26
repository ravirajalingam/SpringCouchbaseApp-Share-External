package org.aexp.springbootcouchbase.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.StaticResourceRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
                .authorizeRequests()
                    .requestMatchers(EndpointRequest.toAnyEndpoint())
                        .permitAll()
               .antMatchers("/**")
                .permitAll();
       //.and().
        //requiresChannel().anyRequest().requiresSecure();

       http.csrf().disable();
    }

}
