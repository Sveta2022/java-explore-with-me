package ru.practicum.main_service.category.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main_service.category.dao.CategoryStorage;
import ru.practicum.main_service.category.dto.CategoryDto;
import ru.practicum.main_service.category.mapper.CategoryMapper;
import ru.practicum.main_service.category.model.CategoryEvent;
import ru.practicum.main_service.event.dao.EventStorage;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.exception.ConflictException;
import ru.practicum.main_service.exception.NotFoundObjectException;


import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Setter
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private CategoryStorage categoryStorage;
    private EventStorage eventStorage;

    @Autowired
    public CategoryServiceImpl(CategoryStorage categoryStorage, EventStorage eventStorage) {
        this.categoryStorage = categoryStorage;
        this.eventStorage = eventStorage;
    }

    @Override
    @Transactional
    public CategoryDto create(CategoryDto categoryDto) {
        verify(categoryDto);
        CategoryEvent categoryEvent = CategoryMapper.toCategory(categoryDto);
        return CategoryMapper.toCategoryDto(categoryStorage.save(categoryEvent));
    }

    @Override
    @Transactional
    public CategoryDto update(Long catId, CategoryDto categoryDto) {
        verify(categoryDto);
        CategoryEvent category = categoryStorage.findById(catId)
                .orElseThrow(() -> new NotFoundObjectException("Категория с id " + categoryDto.getId() + " не существует"));
        category.setName(categoryDto.getName());
        return CategoryMapper.toCategoryDto(categoryStorage.save(category));
    }

    @Override
    @Transactional
    public void delete(Long catId) {
        CategoryEvent category = categoryStorage.findById(catId)
                .orElseThrow(() -> new NotFoundObjectException("Категория с id " + catId + " не существует"));
        if(eventStorage.findByCategoryId(catId).isPresent()){
            throw new ConflictException("Существуют события, связанные с категорией");
        }
        categoryStorage.delete(category);
    }

    @Override
    public CategoryDto getById(Long catId) {
        CategoryEvent category = categoryStorage.findById(catId)
                        .orElseThrow(() -> new NotFoundObjectException("Категория с id " + catId + " не существует"));
        return CategoryMapper.toCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAll(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        return categoryStorage.findAll(pageable).stream()
                .map(CategoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    private void verify(CategoryDto categoryDto) {
        String name = categoryDto.getName();
        if (categoryStorage.findByName(name) != null) {
            throw new ConflictException("Имя " + categoryDto.getName() + " уже существует");
        }
    }
}
