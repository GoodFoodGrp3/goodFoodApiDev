package com.goodfood.api.services;

import com.goodfood.api.entities.LoginDao;
import com.goodfood.api.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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


//   this.LoginRepository.save(UserDto user) {
//        UserDao newUser = new UserDao();
//        newUser.setUsername(user.getUsername());
//        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        return userDao.save(newUser);
//    }
}
