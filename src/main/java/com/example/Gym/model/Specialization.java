package com.example.Gym.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * <class>Specialization</class>
 * <summary>Модель направления (специализации).</summary>
 * <description>
 * Этот класс описывает направления занятий, которые есть в клубе (например: Йога, Танцы, Бокс).
 * Эти данные используются в анкетах тренеров и клиентов.
 * </description>
 */
@Entity
@Table(name = "specializations")
public class Specialization {

    /** <summary>Уникальный код специализации в базе данных.</summary> */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialization_number")
    private Integer id;

    /** * <summary>Название направления тренировок.</summary>
     * <remarks>Здесь установлена проверка: название должно состоять только из букв.</remarks>
     */
    @NotBlank(message = "Название не может быть пустым")
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ\\s\\-]+$", message = "Название направления должно содержать только буквы")
    @Column(name = "name", nullable = false)
    private String name;

    // ГЕТТЕРЫ И СЕТТЕРЫ

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}