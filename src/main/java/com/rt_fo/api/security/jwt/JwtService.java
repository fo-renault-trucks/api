package com.rt_fo.api.security.jwt;

import com.rt_fo.api.user.entity.User;
import com.rt_fo.api.user.repository.UserRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final UserRepository userRepository;

    public JwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserFromJwt(Jwt jwt) {
        return userRepository.findByEmail(jwt.getSubject());
    }
}
