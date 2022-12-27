package ru.practicum.main_service.user.dao;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.main_service.user.model.User;

import java.util.List;


public interface UserStorage extends JpaRepository<User,Long> {
    List<User> findAllByIdIn(List<Long> ids, Pageable pageable);

}
