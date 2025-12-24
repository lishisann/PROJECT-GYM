# Итоговая работа по дисциплине "Язык программирования Java"
<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/074d656d-a571-4f81-ac6d-aace5ab88aba" />
<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/6edaf0af-86c6-4213-874d-2a284991cfb5" />
<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/bdcd0eb7-9846-4ec0-aeb2-0929100ca359" />
<img width="1920" height="1200" alt="image" src="https://github.com/user-attachments/assets/393e2ebb-9391-4218-a57a-74c6a20bd3e0" />

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
│   │   ├── GymApplication.java             # Главный класс, запускающий всё приложение
│   │   ├── controller/
│   │   │   └── AppController.java          # Обрабатывает переходы по страницам и запросы пользователя
│   │   ├── model/
│   │   │   ├── Client.java                 # Описание данных клиента (имя, телефон и т.д.)
│   │   │   ├── Coach.java                  # Описание данных тренера
│   │   │   └── Specialization.java         # Описание направлений тренировок
│   │   └── repository/
│   │       ├── ClientRepository.java       # Команды БД для работы с клиентами
│   │       ├── CoachRepository.java        # Команды БД для работы с тренерами
│   │       └── SpecializationRepository.java # Команды БД для специализаций
│   └── resources/
│       ├── templates/                      # HTML-страницы (Thymeleaf)
│       │   ├── index.html                  # Главная страница (меню)
│       │   ├── clients-list.html           # Таблица всех клиентов
│       │   ├── client-form.html            # Форма создания/редактирования клиента
│       │   ├── coaches-list.html           # Список тренеров
│       │   ├── coach-form.html             # Форма добавления тренера
│       │   ├── specs-list.html             # Список специализаций
│       │   └── spec-edit-form.html         # Редактирование направлений
│       └── application.properties          # Настройки подключения к PostgreSQL
```
