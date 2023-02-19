package ru.practicum.main_service.category.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.main_service.category.dao.CategoryStorage;
import ru.practicum.main_service.category.dto.CategoryDto;
import ru.practicum.main_service.category.model.CategoryEvent;
import ru.practicum.main_service.exception.ConflictException;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;
    @Mock
    private CategoryStorage categoryStorage;
    private CategoryDto categoryDto1;
    private CategoryEvent categoryEvent1;

    @BeforeEach
    void start() {
        categoryDto1 = CategoryDto.builder()
                .id(1L)
                .name("categoryDtoName1")
                .build();

        categoryEvent1 = CategoryEvent.builder()
                .id(1L)
                .name("categoryEventName1")
                .build();
    }

    @Test
    void create() {
        when(categoryStorage.findByName(categoryDto1.getName())).thenReturn(null);
        when(categoryStorage.save(any())).thenReturn(categoryEvent1);

        categoryService.create(categoryDto1);

        verify(categoryStorage, times(1)).findByName(categoryDto1.getName());
    }

    @Test
    void create_with_exists_name() {
        when(categoryStorage.findByName(categoryDto1.getName())).thenReturn(categoryEvent1);
        ConflictException conflictException = assertThrows(ConflictException.class,

                () -> categoryService.create(categoryDto1));

        assertEquals("Имя " + categoryDto1.getName() + " уже существует", conflictException.getMessage());
    }
}