package com.example.Gym.repository;

import com.example.Gym.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * <interface>SpecializationRepository</interface>
 * <summary>Инструмент для связи с таблицей направлений в базе данных.</summary>
 * <description>
 * Интерфейс позволяет программе управлять списком доступных видов тренировок (направлений):
 * искать их по названию, проверять на наличие дубликатов и сохранять изменения.
 * </description>
 */
@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {

    /** <summary>Поиск направления по части его названия.</summary> */
    List<Specialization> findByNameContainingIgnoreCase(String name);

    /**
     * <summary>Проверка, существует ли уже направление с таким названием.</summary>
     * <remarks>Используется для того, чтобы не добавить в список два одинаковых направления (например, "Йога" дважды).</remarks>
     */
    boolean existsByName(String name);
}