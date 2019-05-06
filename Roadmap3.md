1. Рефакторинг
    + 1.1 Рефакторинг SHA256
        - 1.1.1 В src/ru/danil42russia/aaa создать папку utils и перенести SHA256.kt
        - 1.1.2 Переименовать файл SHA256.kt в crypto.kt 
        - 1.1.2 Переименовать функцию SHA256 в sha256
    + 1.2 Изменить функции в классе BusinessLogic что-бы функции возвращали
        - 1.2.1 authentication - 0, 1, 2, 3, 4
        - 1.2.2 authorization -  0, 5, 6
        - 1.2.3 accounting - 0, 7
    + 1.3 Рефакторинг папок
        - 1.3.1 в папке src создать main
        - 1.3.2 в main создать resources и kotlin
        - 1.3.3 в kotlin перенести ru/danil42russia/aaa и всё содержимое
        - 1.3.4 в resources перенести META-INF

2. Добавить и подключить библиотеку Log4j-api и Log4j-core

3. В resources создать файл log4j2.xml
    ```
    <?xml version="1.0" encoding="UTF-8"?>
    <Configuration>
      <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
          <PatternLayout
            pattern="%d{yyyy-MMM-dd HH:mm:ss a} [%t] %-5level %logger{36} - %msg%n" />
        </Console>
        <File name="File" fileName="./aaa.log">
          <PatternLayout
            pattern="%d{yyyy-MMM-dd HH:mm:ss a} [%t] %-5level %logger{36} - %msg%n" />
        </File>
      </Appenders>
      <Loggers>
        <Root level="debug">
          <AppenderRef ref="Console" />
          <AppenderRef ref="File" />
        </Root>
      </Loggers>
    </Configuration>
    ```

4. Добавить аннотацию @Log4j2 в service классах

5. Добавить и подключить библиотеку flywaydb

6. Папке resources создать db/migration

7. Миграции
    + 7.1. В папке migration создать файл V1__create_tables.sql который создаст таблицы 
        - 7.1.1 users
        
        | Атрибуты      | имя   | тип       |
        | :------------ | :---- |:--------- |
        | PK, auto inc  | id    | INTEGER   |
        | unique        | login | TEXT      |
        |               | pass  | TEXT      |
        |               | salt  | TEXT      |

        - 7.1.2 roles

        | Атрибуты      | имя   | тип       |
        | :------------ | :---- |:--------- |
        | PK, auto inc  | id    | INTEGER   |
        | unique        | name  | TEXT      |

        - 7.1.3 users_roles

        | Атрибуты  | имя       | тип       |
        | :-------- | :-------- |:--------- |
        | FK        | id_user   | INTEGER   |
        | FK        | name_role | TEXT      |

    + 7.2. В папке migration создать файл V2__insert_data.sql который заполняет таблицы данными
        - 7.2.1 Таблицу roles:
        
        | name      |
        | :-------- |
        | READ      |
        | WRITE     | 
        | EXECUTE   | 

        - 7.2.2 Таблицу users 
        
        | login         |                   pass           | salt                             |
        | :-----------: | :------------------------------: | :------------------------------: |
        | user@xyz.com  | 605b05f6fcc4eb9013a58287ec82ba93 | b742f2a1ad171e30b1d36af0c0226cc7 |
        |               | 5aa81b7f2a747404165caf129384d1e7 | b003b3e0c4673586cb8b07602f05ed82 |
        
        | login         |                   pass           | salt                             |
        | :-----------: | :------------------------------: | :------------------------------: |
        | user@mk.ru    | a56a0e11e5cb4f0cf61ba2c6181556ef | 807d16ef77c55e79fa210d5000609334 |
        |               | b452928dfa899e4390bb44c406f98a8b | 3a709304c8762a4f5c22e03364301369 |

8. В .gitignore добавить *.db

9. создать класс `MigrationService`
    + 9.1 Создать функцию `fun migrate()` для миграции

10. Создать класс `DBService: Connection` для выборки использовать PreparedStatement

11. В aaa создать dao

12. В dao создать
	+ 12.1 `authenticationDa`
    + 12.2 `authorizationDao`

13. Рефакторинг
	+ 13.1 Из класса `serService` перенести функцию `findUserByLogin` в `authenticationDao`
	+ 13.2  Из класса `CmdService` перенести функцию `checkRole` в `authorizationDao`
