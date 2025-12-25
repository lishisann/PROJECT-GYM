# Итоговая работа по дисциплине "Язык программирования Java"
<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/074d656d-a571-4f81-ac6d-aace5ab88aba" />
<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/1726632c-b60e-4bd5-907c-23b2c737e97d" />
<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/7ca62bf6-f345-44ae-a9a3-f4b191d95b09" />
<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/83043cb4-dcf4-48f7-85c0-6446a29c31c9" />
<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/8a4d0657-f3b4-4621-b667-656e850133a8" />
<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/3b1e595c-7f69-4509-932e-d49f02b1c4d8" />

Проект представляет собой Spring Boot Web MVC приложение для управления фитнес-центром. Оно позволяет вести учет клиентов, тренеров и их специализаций, используя базу данных PostgreSQL и 3 связанные таблицы.
## Функциональность
Управление клиентами: просмотр списка, регистрация новых посетителей, редактирование данных и удаление.

Управление персоналом: ведение базы тренеров с привязкой к их профессиональным направлениям.

Справочник специализаций: создание и редактирование категорий (например, «Йога», «Плавание»), которые назначаются тренерам.

Поиск и проверка: поиск клиентов по ФИО и автоматическая проверка уникальности номера телефона.

Связи между данными: реализация связи «Многие-к-Одному» (много тренеров на одну специализацию).
## Структура проекта
```
src/
├── main/
│   ├── java/com/example/Gym/
│   │   ├── GymApplication.java             # Главный класс
│   │   ├── controller/
│   │   │   └── AppController.java          # Контроллер по страницам и запросам пользователя
│   │   ├── model/
│   │   │   ├── Client.java                 # Класс с сущностью клиента
│   │   │   ├── Coach.java                  # Класс с сущностью тренера
│   │   │   └── Specialization.java         # Класс с сущностью специализации
│   │   └── repository/
│   │       ├── ClientRepository.java       # Репозиторий для работы с БД (клиенты)
│   │       ├── CoachRepository.java        # Репозиторий для работы с БД (тренеры)
│   │       └── SpecializationRepository.java # Репозиторий для работы с БД (специализации)
│   └── resources/
│       ├── templates/                    
│       │   ├── index.html                  # Главная страница (меню)
│       │   ├── clients-list.html           # Таблица всех клиентов
│       │   ├── client-form.html            # Форма создания/редактирования клиента
│       │   ├── coaches-list.html           # Список тренеров
│       │   ├── coach-form.html             # Форма добавления тренера
│       │   ├── specs-list.html             # Список специализаций
│       │   └── spec-edit-form.html         # Редактирование направлений
│       └── application.properties          # Конфигурация
```
