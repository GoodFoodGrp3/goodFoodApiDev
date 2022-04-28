package com.goodfood.api.controller;

import com.goodfood.api.entities.*;
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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import xin.altitude.cms.common.util.SpringUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;


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
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletRequest request)
            throws Exception {

       /* LoginDao loginDao;
        loginDao = null;
        //LoginDao loginDao = null;
        try
        {
            loginDao = (UserDetails) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

            //loginDao.setLogin(loginDao1.getUsername());
            if (loginDao != null)
            {
                if (loginDao.isIs_blocked())
                {
                    Timestamp now = new Timestamp(new DateTime().getMillis());
                    long duration = now.getTime() - loginDao.getBlocked_date().getTime();
                    long timeLeft = 9999;

                    if (duration < BLOCKED_ACCOUNT_DURATION)
                    {
                        timeLeft = BLOCKED_ACCOUNT_DURATION - duration;
                    }

                    else
                    {
                        timeLeft = 0;
                        loginDao.setBlocked_date(null);
                        loginDao.setIs_blocked(false);
                        loginDao.setCounter(3);
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

            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            loginDao.setCounter(3);

            // update of counter in database
            userDetailsService.updateCounter(loginDao);

        }

        catch (AuthenticationException e)
        {
            if (loginDao != null)
            {
                loginDao.setCounter(loginDao.getCounter() - 1);

                if (loginDao.getCounter() == 0)
                {
                    loginDao.setIs_blocked(true);
                    loginDao.setBlocked_date(new Timestamp(new DateTime().getMillis()));
                }

                errorLogServices.recordLog(new ErrorLog(request.getHeader( "Host" ), HttpStatus.UNAUTHORIZED,
                        "Wrong credentials, please try again or contact an administrator. Left attempt : "
                                + loginDao.getCounter()));
                throw new ResponseStatusException( HttpStatus.UNAUTHORIZED,
                        "Wrong credentials, please try again or contact an administrator. Left attempt : "
                                + loginDao.getCounter());
            }

            errorLogServices.recordLog(new ErrorLog(request.getHeader( "Host" ), HttpStatus.UNAUTHORIZED,
                    "Wrong credentials, please try again or contact an administrator."));
            throw new ResponseStatusException( HttpStatus.UNAUTHORIZED,
                    "Wrong credentials, please try again or contact an administrator.");
        }

        loginDao.setCounter(3);

        //authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final LoginDao userDetails = (LoginDao) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        userDetails.setCounter(3);
*/

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));

        //return new ResponseEntity<>( new JwtResponse(loginDao, token, authentication.getAuthorities()), HttpStatus.OK);

    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }



}
