package com.rt_fo.api.user.repository;

import com.rt_fo.api.user.entity.Role;
import com.rt_fo.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findAllByRoleIn(Collection<Role> roles);
}
