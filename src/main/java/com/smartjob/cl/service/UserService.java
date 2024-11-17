package com.smartjob.cl.service;

import com.smartjob.cl.entity.User;

public interface UserService extends CrudService<User, Long> {

    User findByEmailService(String email);

}
