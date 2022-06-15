package com.digiboy.platform.auth.service;

import com.digiboy.platform.auth.api.UserService;
import com.digiboy.platform.auth.client.UsersApiClient;
import com.digiboy.platform.auth.generated.v1.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersApiClient usersApiClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo response = usersApiClient.findUserByEmail(username).getBody();

        return new User(response.getEmail(),
                response.getPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>());
    }
}
