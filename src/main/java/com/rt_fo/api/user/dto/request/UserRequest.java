package com.rt_fo.api.user.dto.request;

import com.rt_fo.api.user.entity.Role;

public interface UserRequest {

    String firstName();

    String lastName();

    Role role();
}
