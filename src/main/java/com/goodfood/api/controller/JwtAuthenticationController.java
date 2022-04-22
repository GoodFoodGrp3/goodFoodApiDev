package com.goodfood.api.controller;

import com.goodfood.api.entities.*;
import com.goodfood.api.security.JwtTokenUtil;
import com.goodfood.api.services.ErrorLogServices;
import com.goodfood.api.servicesImpl.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.util.SpringUtils;

import javax.servlet.http.HttpServletRequest;


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

//        UserDetails userDetails;
//        userDetails = null;
//
//        try
//        {
//            userDetails = (LoginDao) jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//
//            if (userDetails != null)
//            {
//                if (userDetails.isIs_blocked())
//                {
//                    Timestamp now = new Timestamp(new DateTime().getMillis());
//                    long duration = now.getTime() - userDetails.getBlocked_date().getTime();
//                    long timeLeft = 9999;
//
//                    if (duration < BLOCKED_ACCOUNT_DURATION)
//                    {
//                        timeLeft = BLOCKED_ACCOUNT_DURATION - duration;
//                    }
//
//                    else
//                    {
//                        timeLeft = 0;
//                        userDetails.setBlocked_date(null);
//                        userDetails.setIs_blocked(false);
//                        userDetails.setCounter(3);
//                    }
//
//                    if (timeLeft > 0)
//                    {
//                        timeLeft = timeLeft / 1000 / 60;
//                        errorLogServices.recordLog( new ErrorLog( request.getHeader( "Host" ), HttpStatus.UNAUTHORIZED,
//                                "Echecs de connexion trop répétés. Réessayez dans " + timeLeft + " min." ) );
//                        throw new ResponseStatusException( HttpStatus.UNAUTHORIZED,
//                                "Echecs de connexion trop répétés. Réessayez dans " + timeLeft + " min." );
//                    }
//                }
//            }
//
//           /* authentication = this.authentificationService.authentication( credentials.getUsername(),
//                    credentials.getPassword());*/
//
//            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//
//            userDetails.setCounter(3);
//
//            // update of counter in database
//            jwtUserDetailsService.updateCounter(userDetails);
//
//        }
//
//        catch (AuthenticationException e)
//        {
//            if (userDetails != null)
//            {
//                userDetails.setCounter(userDetails.getCounter() - 1);
//
//                if (userDetails.getCounter() == 0)
//                {
//                    userDetails.setIs_blocked(true);
//                    userDetails.setBlocked_date(new Timestamp(new DateTime().getMillis()));
//                }
//
//                errorLogServices.recordLog(new ErrorLog(request.getHeader( "Host" ), HttpStatus.UNAUTHORIZED,
//                        "Wrong credentials, please try again or contact an administrator. Left attempt : "
//                                + userDetails.getCounter()));
//                throw new ResponseStatusException( HttpStatus.UNAUTHORIZED,
//                        "Wrong credentials, please try again or contact an administrator. Left attempt : "
//                                + userDetails.getCounter());
//            }
//
//            errorLogServices.recordLog(new ErrorLog(request.getHeader( "Host" ), HttpStatus.UNAUTHORIZED,
//                    "Wrong credentials, please try again or contact an administrator."));
//            throw new ResponseStatusException( HttpStatus.UNAUTHORIZED,
//                    "Wrong credentials, please try again or contact an administrator.");
//        }
//
//        userDetails.setCounter(3);
//
//        final LoginDao loginDao = (Customers) authenticationManager.getPrincipal();
//        loginDao.setCounter(3);
//
//
//        final String token = jwtTokenUtil.generateToken(userDetails);
//        //final String token = this.authentificationService.loginCustomers(customer);
//        return new ResponseEntity<>( new JwtResponse(loginDao, token, authentication.getAuthorities()), HttpStatus.OK);

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
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
