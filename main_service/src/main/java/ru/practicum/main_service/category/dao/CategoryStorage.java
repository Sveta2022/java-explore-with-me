package ru.practicum.main_service.category.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.main_service.category.model.CategoryEvent;

public interface CategoryStorage extends JpaRepository<CategoryEvent, Long> {
    @Query(nativeQuery = true,
            value = "select * from categorys as c " +
                    "where  c.name = ?1 ")
    CategoryEvent findByName(String name);


}
