package ru.practicum.main_service.compilation.seervice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.main_service.category.model.CategoryEvent;
import ru.practicum.main_service.compilation.dao.CompilationStorage;
import ru.practicum.main_service.compilation.model.Compilation;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.user.model.User;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CompilationServiceImplTest {

    @InjectMocks
    private CompilationServiceImpl compilationService;
    @Mock
    private CompilationStorage compilationStorage;
    private Compilation compilation;
    private Event event1;


    @BeforeEach
    void start() {
        event1 = Event.builder()
                .id(1L)
                .annotation("annotation")
                .category(CategoryEvent.builder()
                        .id(1L)
                        .name("categoryName")
                        .build())
                .eventDate(LocalDateTime.of(2023,10,10,15,20))
                .initiator(User.builder()
                        .id(1L)
                        .name("name")
                        .build())
                .paid(true)
                .title("title")
                .build();
        compilation = Compilation.builder()
                .id(1L)
                .pinned(true)
                .title("title")
                .events(Set.of(event1))
                .build();
    }

    @Test
    void pinnedOutCompilation() {
        when(compilationStorage.findById(1L)).thenReturn(Optional.of(compilation));
        when(compilationStorage.save(compilation)).thenReturn(compilation);
        compilation.setEvents(Set.of(event1));
        compilationService.pinnedOutCompilation(1L);

        verify(compilationStorage, times(1)).save(compilation);
    }
}