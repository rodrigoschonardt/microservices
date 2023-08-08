package com.rodrigoschonardt.discoveryserver.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
    extends
        SecurityConfigurerAdapter
{
    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity httpSecurity ) throws Exception
    {
        httpSecurity.csrf( c -> c.disable() )
                    .authorizeHttpRequests( c -> c.anyRequest()
                                                  .authenticated() )
                    .httpBasic( Customizer.withDefaults() );

        return httpSecurity.build();
    }
}
