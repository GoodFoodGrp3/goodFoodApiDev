package com.goodfood.api.controller;

import com.goodfood.api.entities.*;
import com.goodfood.api.exceptions.employees.EmployeeStatusException;
import com.goodfood.api.exceptions.products.ProductsNotFoundException;
import com.goodfood.api.repositories.LoginRepository;
import com.goodfood.api.request.LoginForm;
import com.goodfood.api.security.JwtTokenUtil;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.servicesImpl.JwtUserDetailsService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import xin.altitude.cms.common.util.SpringUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@RestController
@CrossOrigin
public class JwtAuthenticationController
{
    // ***************
    // CONSTANTS
    // ***************

    private static final long BLOCKED_ACCOUNT_DURATION = 30 * 60 * 1000L; // in milliseconds - 30 minutes

    private AuthenticationManager authenticationManager() {
        return SpringUtils.getBean(AuthenticationManager.class);
    }

    @Autowired
    private ErrorLogServices errorLogServices;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginForm credentials, HttpServletRequest request)
            throws Exception {

        LoginDao user =null;

        try
        {
            user = loginRepository.findByLogin(credentials.getUsername());

            if(user != null)
            {
                if(user.isIs_blocked())
                {
                    Timestamp now = new Timestamp(new DateTime().getMillis());
                    long duration = now.getTime() - user.getBlocked_date().getTime();
                    long timeLeft = 9999;

                    if (duration < BLOCKED_ACCOUNT_DURATION)
                    {
                        timeLeft = BLOCKED_ACCOUNT_DURATION - duration;
                    }

                    else
                    {
                        timeLeft = 0;
                        user.setBlocked_date(null);
                        user.setIs_blocked(false);
                        user.setCounter(3);
                    }

                    if (timeLeft > 0)
                    {
                        timeLeft = timeLeft / 1000 / 60;
                        errorLogServices.recordLog( new ErrorLog( request.getHeader( "Host" ), HttpStatus.UNAUTHORIZED,
                                "Echecs de connexion trop répétés. Réessayez dans " + timeLeft + " min." ) );
                        throw new ResponseStatusException( HttpStatus.UNAUTHORIZED,
                                "Echecs de connexion trop répétés. Réessayez dans " + timeLeft + " min." );
                    }
                }
            }

            authenticate(credentials.getUsername(), credentials.getPassword());

       /*     Set<GrantedAuthority> authorities = new HashSet<>();

            try
            {
                Status status = user.getStatus();
                authorities.add(new SimpleGrantedAuthority(status.name()));
                //user.setAuthorities(authorities);
            }

            catch (Exception e)
            {
                errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND, e.getMessage() + "Aucun status trouvé"));
                throw new ProductsNotFoundException(e.getMessage() + "Aucun status trouvé" );

                errorLogServices.recordLog( new ErrorLog( null, HttpStatus.FORBIDDEN,
                        "You have not the right authorities." ) );
                throw new EmployeeStatusException();
            }*/

            user.setCounter(3);
            // update of counter in database
            userDetailsService.updateCounter(user);

            }
            //
            catch (AuthenticationException e)
            {
                if (user != null)
                {
                    user.setCounter(user.getCounter() - 1);

                    if (user.getCounter() == 0)
                    {
                        user.setIs_blocked(true);
                        user.setBlocked_date(new Timestamp(new DateTime().getMillis()));
                    }

                    errorLogServices.recordLog(new ErrorLog(request.getHeader( "Host" ), HttpStatus.UNAUTHORIZED,
                            "Mauvais identifiants, merci de réessayer ou contacter un administrateur. il vous reste : "
                                    + user.getCounter() + " essais"));
                    throw new ResponseStatusException( HttpStatus.UNAUTHORIZED,
                            "Mauvais identifiants, merci de réessayer ou contacter un administrateur. il vous reste : "
                                    + user.getCounter() + " essais");
                }

                errorLogServices.recordLog(new ErrorLog(request.getHeader( "Host" ), HttpStatus.UNAUTHORIZED,
                        "Mauvais identifiants, merci de réessayer ou contacter un administrateur."));
                throw new ResponseStatusException( HttpStatus.UNAUTHORIZED,
                        "Mauvais identifiants, merci de réessayer ou contacter un administrateur.");
            }

            user.setCounter(3);

            final UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.getUsername());

            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception
    {
        try
        {
            authenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }

        catch (DisabledException e)
        {
            throw new Exception("USER_DISABLED", e);
        } /*catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }*/
    }
}
