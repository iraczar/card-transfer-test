# Автотесты для функции перевода денег

[![Java CI with Gradle](https://github.com/iraczar/card-transfer-test/actions/workflows/gradle.yml/badge.svg)](https://github.com/ТвойНик/card-transfer-test/actions/workflows/gradle.yml)

## Описание
Проект с автотестами для проверки функции перевода денег между картами в интернет-банке.

## Технологии
- Java 11
- Gradle
- Selenide
- JUnit 5

## Запуск тестов

### Локально
1. Запустить приложение:
   java -jar ./artifacts/app-ibank-build-for-testers.jar
2. Запустить тесты:
   ./gradlew test
### В CI
Тесты запускаются автоматически при каждом push в GitHub Actions.

## Известные баги
См. [Issues](https://github.com/iraczar/card-transfer-test/issues)