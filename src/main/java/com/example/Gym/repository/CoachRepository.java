package com.example.Gym.repository;

import com.example.Gym.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * <interface>CoachRepository</interface>
 * <summary>Инструмент для связи с таблицей тренеров в базе данных.</summary>
 * <description>
 * Интерфейс отвечает за все операции с данными тренеров:
 * сохранение новых сотрудников, их поиск или удаление из базы.
 * </description>
 */
@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer> {

    /** <summary>Поиск тренера по части ФИО.</summary> */
    List<Coach> findByFullNameContainingIgnoreCase(String name);

    /**
     * <summary>Проверка, занят ли номер телефона другим тренером.</summary>
     * <remarks>Используется при регистрации нового тренера, чтобы не допустить создания двух людей с одинаковым номером.</remarks>
     */
    boolean existsByPhone(String phone);
}