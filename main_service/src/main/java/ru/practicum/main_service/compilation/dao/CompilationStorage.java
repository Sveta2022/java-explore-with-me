package ru.practicum.main_service.compilation.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.main_service.compilation.model.Compilation;
import ru.practicum.main_service.user.model.User;

import java.util.Arrays;
import java.util.List;

public interface CompilationStorage extends JpaRepository<Compilation, Long> {

    List<Compilation> findAllByPinned(Boolean pinned, Pageable pageable);
}
