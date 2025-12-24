package com.example.Gym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

/**
 * <class>GymApplication</class>
 * <summary>Главный выключатель программы.</summary>
 * <description>Этот класс запускает весь проект Spring Boot и выводит полезную информацию в консоль при старте.</description>
 */
@SpringBootApplication
public class GymApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymApplication.class, args);
    }

    /** * <summary>Автоматический вывод ссылки в консоль.</summary>
     * <remarks>Как только программа полностью загрузится, в терминале появится кликабельная ссылка.</remarks>
     */
    @EventListener(ApplicationReadyEvent.class)
    public void printConsoleLink() {
        System.out.println("ПРИЛОЖЕНИЕ GYM УСПЕШНО ЗАПУЩЕНО");
        System.out.println("ССЫЛКА ДЛЯ РАБОТЫ: http://localhost:8080");
    }
}