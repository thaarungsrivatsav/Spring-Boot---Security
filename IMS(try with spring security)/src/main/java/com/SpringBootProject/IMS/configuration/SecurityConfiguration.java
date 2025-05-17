package com.SpringBootProject.IMS.configuration;


import com.SpringBootProject.IMS.configuration.jwt.JwtAuthenticationEntryPoint;
import com.SpringBootProject.IMS.configuration.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    public void configure(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder);
    }
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Override
    public void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/unsecured/**").permitAll()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/search/stock/{stockName}").permitAll()
                .antMatchers("/display/stock").permitAll()//stock display is permit all
                .antMatchers("/stock/create").permitAll()//stock create is permit all
                .antMatchers("/stock/delete/{stockId}").permitAll()//stock delete by id is permit all
                .antMatchers("/update/stock").permitAll()//stock update is also permit all
                .antMatchers("/create/user").permitAll()//new user creation is also permit all
                .antMatchers("/payment/").permitAll()//payment mode is also permit all
                .antMatchers("/secured/ping/vendor").hasAnyAuthority("VENDOR")
                .antMatchers("/secured/ping/admin").hasAnyAuthority("ADMIN")
//                .antMatchers("/stock/create").hasAnyAuthority("ADMIN")
//                .antMatchers("/stock/delete/**").hasAnyAuthority("ADMIN")
//                .antMatchers("/update/stock").hasAnyAuthority("ADMIN")
                .antMatchers("/secured/ping/both").hasAnyAuthority("VENDOR", "ADMIN")
                .anyRequest().fullyAuthenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .httpBasic();

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
