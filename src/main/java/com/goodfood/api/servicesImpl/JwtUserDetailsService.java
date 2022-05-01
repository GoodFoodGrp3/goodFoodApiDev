package com.goodfood.api.servicesImpl;


import com.goodfood.api.entities.LoginDao;
import com.goodfood.api.repositories.LoginRepository;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService
{
    @Autowired
    LoginRepository loginRepository;

    @Autowired
    ErrorLogServices errorLogServices;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        LoginDao user =  loginRepository.findByLogin(username);

        if (user == null || username.isEmpty())
        {
            throw new UsernameNotFoundException("User not found with username + vide: " + username);
        }

        /*Set<Status> status = Collections.singleton(user.getStatus());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(); // use list if you wish
        for (Status statu : status) {
            grantedAuthorities.add(new SimpleGrantedAuthority(statu.name()));
        }*/

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), new ArrayList<>());
    }


    public void updateCounter(LoginDao user)
    {
        loginRepository.save(user);
    }
}
