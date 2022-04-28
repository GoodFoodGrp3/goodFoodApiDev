package com.goodfood.api.servicesImpl;

import com.goodfood.api.entities.ErrorLog;
import com.goodfood.api.entities.LoginDao;
import com.goodfood.api.entities.Status;
import com.goodfood.api.exceptions.products.ProductsNotFoundException;
import com.goodfood.api.repositories.LoginRepository;
import com.goodfood.api.request.UpdateUserPasswordForm;
import com.goodfood.api.services.ErrorLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    ErrorLogServices errorLogServices;
//    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginDao user =  loginRepository.findByLogin(username);

        if (user == null || username.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username + vide: " + username);
        }

        /*Set<Status> status = Collections.singleton(user.getStatus());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(); // use list if you wish
        for (Status statu : status) {
            grantedAuthorities.add(new SimpleGrantedAuthority(statu.name()));
        }*/

        Set<GrantedAuthority> authorities = new HashSet<>();
        try
        {
            Status status1 = user.getStatus();
            authorities.add(new SimpleGrantedAuthority(status1.name()));
        }

        catch (Exception e)
        {
            errorLogServices.recordLog( new ErrorLog( null, HttpStatus.NOT_FOUND, e.getMessage() + "Aucun status trouvé"));
            throw new ProductsNotFoundException(e.getMessage() + "Aucun status trouvé" );
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), new ArrayList<>());
    }

    public void updateCounter(LoginDao user) {
        loginRepository.save(user);
    }

}
