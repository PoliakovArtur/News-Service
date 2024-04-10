package com.example.service;

import com.example.filter.impl.UserFilter;
import com.example.model.User;
import org.springframework.data.domain.Page;

public interface UserService {

    Iterable<User> findAll(UserFilter pageable);

    User findById(Long id);

    User save(User user);

    User updateById(Long id, User fromDto);

    void deleteById(Long id);
}
