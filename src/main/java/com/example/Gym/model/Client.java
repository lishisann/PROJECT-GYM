package com.example.Gym.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * <class>Client</class>
 * <summary>Карточка данных клиента.</summary>
 * <description>Хранит информацию о посетителе: имя, телефон и выбранное направление тренировок.</description>
 */
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_number")
    private Integer id;

    /** <summary>ФИО клиента.</summary> <remarks>Запрещены цифры и спецсимволы.</remarks> */
    @NotBlank(message = "ФИО обязательно")
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ\\s\\-]+$", message = "Используйте только буквы")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    /** <summary>Номер телефона.</summary> <remarks>Должен состоять строго из 11 цифр без плюса.</remarks> */
    @NotBlank(message = "Телефон обязателен")
    @Pattern(regexp = "^\\d{11}$", message = "Номер должен содержать ровно 11 цифр")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "specialization_number")
    private Specialization specialization;

    // Геттеры и сеттеры
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Specialization getSpecialization() { return specialization; }
    public void setSpecialization(Specialization specialization) { this.specialization = specialization; }
}