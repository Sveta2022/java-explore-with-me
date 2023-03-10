package ru.practicum.main_service.compilation.seervice;


import ru.practicum.main_service.compilation.dto.CompilationDto;
import ru.practicum.main_service.compilation.dto.NewCompilationDto;


import java.util.List;

public interface CompilationService {

    List<CompilationDto> getAllCompilations(Boolean pinned, Integer from, Integer size);

    CompilationDto create(NewCompilationDto newCompilationDto);

    void removeCompilationById(Long compilationId);

    CompilationDto pinnedOutCompilation(Long compId);

    CompilationDto getCompilationDtoById(Long id);

    CompilationDto updateCompilation(Long compId, NewCompilationDto newCompilationDto);
}
