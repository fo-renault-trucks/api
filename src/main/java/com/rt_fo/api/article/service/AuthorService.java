package com.rt_fo.api.article.service;

import com.rt_fo.api.user.entity.Role;
import com.rt_fo.api.user.entity.User;
import com.rt_fo.api.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final UserRepository userRepository;

    public AuthorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAuthors() {
        return userRepository.findAllByRoleIn(List.of(Role.ADMIN, Role.AUTHOR));
    }
}
