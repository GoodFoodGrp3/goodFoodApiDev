package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.entities.LoginDao;
import com.goodfood.api.repositories.LoginRepository;
import com.goodfood.api.request.UpdateUserPasswordForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    LoginRepository loginRepository;

//    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginDao user =  loginRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                new ArrayList<>());
    }

    public void updateCounter(LoginDao user) {
        loginRepository.save(user);
    }

}
