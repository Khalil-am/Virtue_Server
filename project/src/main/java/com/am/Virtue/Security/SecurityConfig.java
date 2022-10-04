package com.am.Virtue.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private UserDetailServiceImpl userDetailService;


    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailService).passwordEncoder(dcCripto());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //     http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/Appointments/**")
        //           .hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin()
        //         .permitAll().and().logout().permitAll();
//remove this if needed
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/account/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .requestCache()
                .requestCache(new NullRequestCache())
                .and()
                .httpBasic()
                .authenticationEntryPoint(entryPointUnauthorizedHandler);
        http.csrf().disable();
    }

    //    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory().withClient("Virtue-client").secret("Virtue-secret")
//                .authorizedGrantTypes("client_credentials").scopes("resource-server-read", "resource-server-write");
//    }
//    @Override
//    public void configure(AuthenticationManagerBuilder authenticationMgr) throws Exception {
//        authenticationMgr.inMemoryAuthentication().withUser("admin").password("{noop}admin")
//                .authorities("ROLE_ADMIN");
//    }

    @Bean
    public DcCripto dcCripto() {
        DcCripto dcCripto = new DcCripto();
        dcCripto.setDataBaseKey("KEY_01");
        dcCripto.setApplicationContext(applicationContext);
        return dcCripto;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //ignore any ws access token
        web.ignoring().antMatchers("/account/add","/account/**","/**");

    }

}