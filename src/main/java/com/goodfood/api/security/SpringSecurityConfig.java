package com.goodfood.api.security;

import com.goodfood.api.entities.Status;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesUserDetailsService;
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

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTAuthorizationFilter jwtRequestFilter;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Value("${api.security.httpPatternMatcher.disabled:false}")
    private boolean httpPatternMatcherDisabled;
    //test
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
            http.cors().and().csrf().disable().authorizeRequests().antMatchers( HttpMethod.OPTIONS, "/**" ).permitAll();
        if ( !httpPatternMatcherDisabled ) {
            // http pattern matcher enabled
            http.authorizeRequests()
                    .antMatchers( HttpMethod.POST,
                            "/employees/register", "/customers/register",
                            "/authenticate").permitAll()

                    .antMatchers(HttpMethod.POST,
                            "/categories",
                            "/commoditys",
                            "/offices",
                            "/products",
                            "/providers").hasAnyAuthority(Status.RESTAURATEUR.name(),
                            Status.EMPLOYEE.name(),Status.ADMINISTRATEUR.name())

                    .antMatchers( HttpMethod.GET,
                            "/customers/{id}",
                            "/admin/errorLogs",
                            "/commoditys", "/commoditys/{id}",
                            "/offices", "/offices/{id}",
                            "/employees", "/employees/profile/search/{username}","/employees/{id}")
                    .hasAnyAuthority(Status.RESTAURATEUR.name()
                            ,Status.ADMINISTRATEUR.name(),Status.EMPLOYEE.name())


                    .antMatchers(HttpMethod.DELETE,
                            "/commoditys/{id}",
                            "/customers/profile/{id}",
                            "/products/{id}",
                            "/comments/{id}",
                            "/employees/profile/{id}").hasAnyAuthority(Status.RESTAURATEUR.name(),
                            Status.EMPLOYEE.name(),Status.ADMINISTRATEUR.name())

                    .antMatchers(HttpMethod.PUT,
                            "/comments/{id}",
                            "/providers","/providers/{id}",
                            "/customers/profile/{id}/password").hasAnyAuthority(Status.RESTAURATEUR.name(),
                            Status.EMPLOYEE.name(),Status.ADMINISTRATEUR.name(),Status.UTILISATEUR.name())

                    .antMatchers(HttpMethod.PUT,
                            "/customers/profile/{id}").hasAuthority(Status.UTILISATEUR.name())

                    .antMatchers(HttpMethod.PUT,
                            "/employees/{id}/status").hasAuthority(Status.ADMINISTRATEUR.name())

                    .antMatchers(HttpMethod.PUT,
                            "/employees/profile/{id}").hasAnyAuthority(Status.RESTAURATEUR.name(),
                            Status.EMPLOYEE.name(),Status.ADMINISTRATEUR.name(),Status.COMPTABLE.name(),Status.COMMUNITY.name())

                    .antMatchers(HttpMethod.PUT,
                            "/commoditys/{id}",
                            "/offices/{id}",
                            "/products/{id}",
                            "/providers/{id}",
                            "/employees/profile/{id}/password").hasAnyAuthority(Status.RESTAURATEUR.name(),
                            Status.EMPLOYEE.name(),Status.ADMINISTRATEUR.name())


              .antMatchers( HttpMethod.GET,
                    "/favicon.ico", "/v2/api-docs", "/configuration/ui",
                      "/swagger-resources/**", "/configuration/security","/swagger-ui/*", "/swagger-ui.html",
                    "/webjars/**",// swagger
                    "/categories","/categories/{id}",
                    "/comments", "/comments/{id}",
                    "/orders", "/orders/{id}",
                    "/products","/products/{id}",
                    "/customers", "/customers/profile/search/{username}").permitAll().anyRequest().authenticated();

        }

        else
        { // http pattern matcher disabled
            http.authorizeRequests().anyRequest().permitAll(); // toutes les pages/requêtes sont accessibles
        }
        // this disables session creation on Spring Security
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().headers()
            .frameOptions().disable();
        // Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


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

}
