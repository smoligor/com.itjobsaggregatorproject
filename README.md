# README #
https://itjobsaggregator.herokuapp.com/jobs/all адрес задеплоенного проекта.
Залита основная структура приложения и парсер для work.ua Который заливает вакансии в mongodb. Для корректной работы нужно установка mongodb. Гайд по установке Mongodb : https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/
Приложение не запускается через мейн. Нужно Запускать через mvn spring-boot:run
Для запросов на контроллеры нужно использовать localhost:8080

Доступные ендпоинты:

localhost:8080/jobs/all - получить все вакансии.

Фичи делаем в отдельных ветках под каждую фичу!