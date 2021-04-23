package com.citec.demo.shop.service;

import com.citec.demo.shop.model.UserRepository;
import com.citec.demo.shop.model.entity.Role;
import com.citec.demo.shop.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.isNull;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        //TODO: bruteforce solution
        if (isNull(user)) throw new UsernameNotFoundException("User " + username + " not found!");
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (Objects.equals(username, "admin")) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getRole()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getRole()));
        }
        logger.debug(String.format("User with name: %s and password: %s created.", user.getUsername(), user.getPassword()));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }


}
