package ru.practicum.main_service.compilation.seervice;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main_service.compilation.dao.CompilationStorage;
import ru.practicum.main_service.compilation.dto.CompilationDto;
import ru.practicum.main_service.compilation.dto.NewCompilationDto;
import ru.practicum.main_service.compilation.mapper.CompilationMapper;
import ru.practicum.main_service.compilation.model.Compilation;
import ru.practicum.main_service.event.dao.EventStorage;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.exception.NotFoundObjectException;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Setter
@Transactional(readOnly = true)
public class CompilationServiceImpl implements CompilationService {

    private CompilationStorage compilationStorage;
    private EventStorage eventStorage;

    @Autowired
    public CompilationServiceImpl(CompilationStorage compilationStorage, EventStorage eventStorage) {
        this.compilationStorage = compilationStorage;
        this.eventStorage = eventStorage;
    }

    @Override
    public List<CompilationDto> getAllCompilations(Boolean pinned, Integer from, Integer size) {

        Pageable pageable = PageRequest.of(from / size, size);
        if (Objects.isNull(pinned)) {
            compilationStorage.findAll(pageable);
        }

        return compilationStorage.findAllByPinned(pinned, pageable).stream()
                .map(CompilationMapper::toCompilDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CompilationDto create(NewCompilationDto newCompilationDto) {

        Set<Event> events = new HashSet<>(eventStorage.findAllById(newCompilationDto.getEvents()));
        Compilation newCompilation = Compilation.builder()
                .events(events)
                .pinned(newCompilationDto.getPinned())
                .title(newCompilationDto.getTitle())
                .build();
        compilationStorage.save(newCompilation);

        return CompilationMapper.toCompilDto(newCompilation);
    }

    @Override
    @Transactional
    public void removeCompilationById(Long compilationId) {

        getCompilationById(compilationId);
        compilationStorage.deleteById(compilationId);
    }

    @Override
    @Transactional
    public CompilationDto pinnedOutCompilation(Long compId) {

        Compilation compilation = getCompilationById(compId);
        compilation.setPinned(false);
        compilationStorage.save(compilation);

        return CompilationMapper.toCompilDto(compilation);
    }

    @Override
    public CompilationDto getCompilationDtoById(Long id) {
        Compilation compilation = getCompilationById(id);

        return CompilationMapper.toCompilDto(compilation);
    }

    @Override
    @Transactional
    public CompilationDto updateCompilation(Long compId, NewCompilationDto newCompilationDto) {
        Compilation compilation = getCompilationById(compId);
        if (newCompilationDto.getPinned() != null) {
            compilation.setPinned(newCompilationDto.getPinned());
        }
        if (newCompilationDto.getTitle() != null) {
            compilation.setTitle(newCompilationDto.getTitle());
        }
        if (newCompilationDto.getEvents() != null) {
            compilation.setEvents(newCompilationDto.getEvents().stream()
                    .map(this::getEventbyId).collect(Collectors.toSet()));
        }
        return CompilationMapper.toCompilDto(compilationStorage.save(compilation));
    }

    private Compilation getCompilationById(Long id) {

        return compilationStorage
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundObjectException("Компиляция с id + " + id + " не найдена"));
    }

    private Event getEventbyId(Long eventId) {
        return eventStorage.findById(eventId).orElseThrow(() ->
                new NotFoundObjectException("Событие с id " + eventId + " не найдено"));
    }
}
