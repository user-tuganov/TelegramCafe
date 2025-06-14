# Telegram Cafe Bot - API

## 📝 Описание

Бэкенд часть Telegram-бота для заказов из кафе с полным циклом:
- Просмотр меню
- Формирование заказа
- Оплата
- Отслеживание статуса

## ✨ Особенности

### Основной функционал
- 🍔 Меню с категориями и фильтрами
- 🛒 Корзина с редактированием
- 🚚 Выбор доставки/самовывоза
- 💳 Онлайн-оплата
- 📊 История заказов

### Дополнительно
- 🔍 Поиск по меню
- ⚙ Настройки профиля
- 📱 Полностью адаптивный интерфейс
- 🔔 Real-time уведомления

## 🛠 Технологический стек

**Backend:**
- Java 17
- Spring Boot 3.1.5
- Spring Data JPA
- Hibernate

**Базы данных:**
- PostgreSQL

**Инфраструктура:**
- Docker
- Gradle

## 🚀 Быстрый старт

- Для запуска локально используйте

```bash
./gradlew bootRun
```
- Для запуска в Docker используйте

```bash
  docker build -t telegram-cafe-bot .
  docker run -p 8080:8080 telegram-cafe-bot
```

ИКБО-20-22 Туганов Н.А.
