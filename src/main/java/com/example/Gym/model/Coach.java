package com.example.Gym.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * <class>Coach</class>
 * <summary>Модель данных (анкета) тренера.</summary>
 * <description>
 * Этот класс описывает, какая информация о тренере хранится в базе данных:
 * его имя, телефон, почта и профессиональное направление.
 * </description>
 */
@Entity
@Table(name = "coaches")
public class Coach {

    /** <summary>Уникальный порядковый номер тренера в системе.</summary> */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coach_number")
    private Integer id;

    /** * <summary>ФИО сотрудника.</summary>
     * <remarks>Проверка ввода (только буквы).</remarks>
     */
    @NotBlank(message = "ФИО тренера обязательно")
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ\\s\\-]+$", message = "ФИО должно содержать только буквы")
    @Column(name = "full_name")
    private String fullName;

    /** * <summary>Связь с направлением (специализацией).</summary>
     * <remarks>Указывает, к какому разделу относится тренер: например, "Плавание" или "Йога".</remarks>
     */
    @ManyToOne
    @JoinColumn(name = "specialization_number")
    private Specialization specialization;

    /** * <summary>Контактный номер телефона.</summary>
     * <remarks>Требует строго 11 цифр подряд.</remarks>
     */
    @NotBlank(message = "Телефон обязателен")
    @Pattern(regexp = "^\\d{11}$", message = "Номер телефона должен состоять из 11 цифр")
    private String phone;

    // ГЕТТЕРЫ И СЕТТЕРЫ

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public Specialization getSpecialization() { return specialization; }
    public void setSpecialization(Specialization specialization) { this.specialization = specialization; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}