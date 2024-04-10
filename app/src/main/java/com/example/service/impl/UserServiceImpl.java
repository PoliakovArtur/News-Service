package com.example.service.impl;

import com.example.exception.NotFoundException;
import com.example.filter.impl.UserFilter;
import com.example.util.EntityUpdater;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.util.SortingFilteringUtils.byDateTimeRange;
import static com.example.util.SortingFilteringUtils.getPageableByFilter;
import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final EntityUpdater updater;

    @Transactional(readOnly = true)
    @Override
    public Iterable<User> findAll(UserFilter filter) {
        return repository.findAll(
                byDateTimeRange(
                        filter.getRegisterAfter(),
                        filter.getRegisterBefore(),
                        "registrationDate"),
                getPageableByFilter(filter));
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Пользователь с id {0} не найден", id)));
    }

    @Transactional
    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Transactional
    @Override
    public User updateById(Long id, User fromDto) {
        User user = findById(id);
        updater.updateUser(user, fromDto);
        return repository.save(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        User user = findById(id);
        repository.delete(user);
    }
}
