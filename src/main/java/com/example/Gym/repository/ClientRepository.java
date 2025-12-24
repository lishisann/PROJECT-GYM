package com.example.Gym.repository;

import com.example.Gym.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * <interface>ClientRepository</interface>
 * <summary>Инструмент для работы с таблицей клиентов в базе данных.</summary>
 * <description>
 * Интерфейс позволяет программе "общаться" с базой данных.
 * Содержит готовые команды для сохранения, удаления и поиска клиентов.
 * </description>
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    /** <summary>Поиск клиента по части ФИО.</summary> */
    List<Client> findByFullNameContainingIgnoreCase(String name);

    /**
     * <summary>Проверка, занят ли номер телефона другим клиентом.</summary>
     * <remarks>Используется при регистрации нового клиента, чтобы не допустить создания двух людей с одинаковым номером.</remarks>
     */
    boolean existsByPhone(String phone);
}