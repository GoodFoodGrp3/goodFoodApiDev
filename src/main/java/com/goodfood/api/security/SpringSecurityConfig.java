package com.goodfood.api.security;

import com.goodfood.api.services.CustomersService;
import com.goodfood.api.services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
    // ###########################################################################
    // WebSecurity boolean toggle for HTTP Pattern Matcher enablement.
    // To be modified in application.properties file :
    // - for security enabled (default or empty/null/commented):
    // api.security.httpPatternMatcher.disabled=false
    // - for security disabled :
    // api.security.httpPatternMatcher.disabled=true
    // ###########################################################################

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Value("${api.security.httpPatternMatcher.disabled:true}")
    private boolean httpPatternMatcherDisabled;

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private CustomersService customersService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.cors().and().csrf().disable().authorizeRequests().antMatchers( HttpMethod.OPTIONS, "/**" ).permitAll();
        if ( !httpPatternMatcherDisabled ) { // http pattern matcher enabled
            http.authorizeRequests()
                    .antMatchers( HttpMethod.POST,
                            "/employees/login", "/employees/register","customers/login","customers/register")
                    .permitAll()
                    .antMatchers( HttpMethod.GET, "/favicon.ico", "/v2/api-docs", "/configuration/ui", // swagger
                            "/swagger-resources/**", "/configuration/security","/swagger-ui/*", "/swagger-ui.html", "/webjars/**", // swagger
                            "/comments",
                            "/products") // get all products filtered by name
                    .permitAll()
                    .anyRequest().authenticated();
        }

        else
        { // http pattern matcher disabled
            http.authorizeRequests().anyRequest().permitAll(); // toutes les pages/requêtes sont accessibles
        }

        http.authorizeRequests().and().addFilter( new JWTAuthorizationFilter( authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().headers()
                .frameOptions().disable();

        // mise en place https
        // http
        // .requiresChannel()
        // .anyRequest()
        // .requiresSecure();
        http.headers().contentTypeOptions();
        http.headers().xssProtection();
        http.headers().cacheControl();
        http.headers().httpStrictTransportSecurity();
        http.headers().frameOptions();
        http.headers().addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "script-src 'self'" ));
    }


    @Override
    public void configure( AuthenticationManagerBuilder auth ) throws Exception
    {
        auth.userDetailsService( s -> (UserDetails) this.customersService.getCustomerByUserName( s ))
                .passwordEncoder(this.bCryptPasswordEncoder());

        auth.userDetailsService( s -> (UserDetails) this.employeesService.getEmployeesByFirstName( s ))
                .passwordEncoder(this.bCryptPasswordEncoder());
    }


}
