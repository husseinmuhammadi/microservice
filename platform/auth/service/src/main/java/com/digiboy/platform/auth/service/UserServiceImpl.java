package com.digiboy.platform.auth.service;

import com.digiboy.platform.auth.api.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, "$2a$10$S85Ael4XeaJ7ZSUUcuiF3O0hGltKYRD8xuL/WAB9nM3pM7pfpBeOS", true,
                true, true, true, new ArrayList<>());
    }
}
