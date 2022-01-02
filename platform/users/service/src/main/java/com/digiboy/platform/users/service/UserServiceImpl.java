package com.digiboy.platform.users.service;

import com.digiboy.platform.users.api.UserService;
import com.digiboy.platform.users.dto.UserDTO;
import com.digiboy.platform.users.mapper.UserMapper;
import com.digiboy.platform.users.repository.UserRepository;
import com.digiboy.platform.users.to.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceBase implements UserService {

    private final Logger logger;

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public UserServiceImpl(Logger logger) {
        this.logger = logger;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::map);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = mapper.map(userDTO);

        user.setUserId(UUID.randomUUID().toString());

        final String username = user.getUsername().toLowerCase();
        user.setUsername(username);

        if (repository.existsById(user.getUsername())) {
            logger.warn("User {} already exists, it will be updated", username);
        }

        return mapper.map(repository.save(user));
    }

//    @Override
//    public boolean exists(String username) {
//        return repository.existsById(username.toLowerCase());
//    }

}
