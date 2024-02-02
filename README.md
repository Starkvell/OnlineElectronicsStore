# Интернет-магазин элктроники

Этот проект предоставляет простую и мощное приложение для продажи электроники онлайн.
## Установка
Для запуска проекта:
1. Склонируйте репозиторий:
   ```
   git clone https://github.com/Starkvell/OnlineElectronicsStore.git
   ```
2. Перейдите в каталог проекта:
   ```
   cd OnlineElectronicsStore
   ```
3. Создайте БД в PostgreSQL с названием "hibernate_init_el_store"
4. Измените логин и пароль от БД в файле application.properties в корне проекта. По умолчанию заданы поля:
   ```
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   ```
5. Соберите проект с помощью Maven:
   ```
   mvnw clean package -DskipTests
   ```
6. Запустите приложние:
   ```
   java -jar target/OnlineElectronicsStore-0.0.1-SNAPSHOT.jar
   ```
Вам необходимо установить Java для запуска этого приложения таким образом.
Сервер будет доступен по адресу: http://localhost:8080/

Так же можно запустить приложение через Docker, для этого нужно установить только Docker:
  ```
  docker-compose up --build
  ```
## Swagger
Для перехода к документации API в виде Swagger UI перейдите по http://localhost:8080/swagger-ui/index.html
Для перехода к документации API в виде JSON перейдите по http://localhost:8080/v3/api-docs
