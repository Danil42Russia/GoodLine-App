## Часть 1

1. Создать проект aaa **[2 минуты]**

2. Создать скрипты: **[15 минут]**
    + 2.1 BUILD.sh **[5 минут]**
    + 2.2 RUN.sh **[5 минут]**
    + 2.3 TEST.sh **[5 минут]**
    
3. Настроить Travis CI для автоматического запуска тестов. **[10 минут]**

4. 4 пункт
    + 4.1 Создать класс User(login: String, pass: String, salt: String) **[1 минута]**
    + 4.2 Создать коллекцию List<User> **[1 минута]**
    + 4.3 Заполнить коллекцию данными: **[1 минута]**
    
    | login         |                   pass           | salt                             |
    | :-----------: | :------------------------------: | :------------------------------: |
    | user@xyz.com  | 605b05f6fcc4eb9013a58287ec82ba93 | b742f2a1ad171e30b1d36af0c0226cc7 |
    |               | 5aa81b7f2a747404165caf129384d1e7 | b003b3e0c4673586cb8b07602f05ed82 |
    
    | login         |                   pass           | salt                             |
    | :-----------: | :------------------------------: | :------------------------------: |
    | user@mk.ru    | a56a0e11e5cb4f0cf61ba2c6181556ef | 807d16ef77c55e79fa210d5000609334 |
    |               | b452928dfa899e4390bb44c406f98a8b | 3a709304c8762a4f5c22e03364301369 |

5. Создать класс Cmd(login: String, pass: String, help: Boolean) **[1 минута]**

6. Создать enum класс, для exit кодов **[2 минуты]**

    | enum              | exit-код  |
    | :---------------- | :-------: |
    | SUCCESS           | 0         |
    | HELP              | 1         |
    | BADLOGINFORMAT    | 2         |
    | BADLOGIN          | 3         |
    | BADPASSWORD       | 4         |

7. 7 пункт
    + 7.1 Создать класс CmdServise **[1 минута]**
    + 7.2 Создать функцию: fun parse(arg: Array<String>): Cmd <> для проверки данных **[1 час]**

    | Параметр  | Параметры     |
    | :-------- | :------------ |
    | -login    | логин (email) |
    | -pass     | пароль        |
    | -h        | Отсутствуют   |
    
    + 7.3 Создать функцию: fun help(): Unit <> Для вывода справки **[5 минут]**

8. Создать класс UserServise [1 минута] **[30 минут]**
    + 8.1 Создать функцию: fun checkLogin(login: String): Boolean <> Для проверки логина регулярным выражением **[20 минут]**
    + 8.2 Создать функцию: fun findUserByLogin(login: String, user: List<User>): User? <> поиск пользователя **[2 минуты]**
    + 8.3 Создать функцию: fun encrypt(pass: String, salt: String): String <> который будет генерировать хэш-пароль [ hash(hash(pass)+salt) ], где
        * hash - использовать алгоритм хэширования: SHA-256
        * salt - соль **[5 минут]**
    + 8.4 Создать функцию: fun validatePass(user: User, pass: String): Boolean **[2 минуты]**

9. Создать класс LoginServise ***[2 минуты]*** **[4 минуты]**
    + 9.1 fun authentication(cmd: Cmd, user: List<User>): Int **[2 минуты]**
10. Предусмотреть что параметры в класс Cmdмогут приниматься в любом порядке **[5 минуты]**

## Часть 2

1. Добавить новые тесты **[5 минут]**

2. Создать enum класс Roles и заполнить данными: READ, WRITE, EXECUTE **[2 минуты]**

3. Добавить ExitCodes **[2 минуты]**

    | enum              | exit-код  |
    | :---------------- | :-------: |
    | BADROLE           | 5         |
    | NOTPERMISSION     | 6         |
    | INCORRECTACTIVITY | 7         |
    
4. Добавить и подключить библиотеку Commons CLI **[5 минут]**

5. Изменить класс CmdService **[15 минут]**
    + 5.1 Изменить fun parse под commons-cli **[10 минут]**
	+ 5.2 Изменить fun help под commons-cli используя функцию HelpFormatter **[5 минут]**

6. В первичный конструктор класса Cmd добавить: (val res: String, val role: String, val ds: LocalData, val de: LocalData, val vol: Int) **[1 минута]**

7. В функцию parse добавить новые флаги ***[4 минуты]*** **[5 минуты]**

    | Параметр  | Параметры                 |
    | :-------- | :------------------------ |
    | -res      | Строка с уровнями         |
    | -role     | Роль                      |
    | -ds       | Строка формата ГГГГ-ММ-ДД |
    | -de       | Строка формата ГГГГ-ММ-ДД |
    | -vol      | Целое число               |
    
    + 7.1 Для парсинга даты добавить строгий флаг **[1 минут]**

8. Изменить класс CmdService **[4 минуты]**
	+ 8.1 Создать функцию fun checkRole(role: String, roles: Roles): Boolean **[2 минуты]**
	+ 8.2 Создать функцию fun checkNode(res: String): Boolean **[2 минуты]**
	
9. Изменить класс BusinessLogic **[6 минут]
	+ 9.1 Создать функцию fun authorization(login: String, pass: String, List<User>): ExitCode **[1 минут]**
	+ 9.2 Создать функцию fun accounting(login: String, pass: String, role: String, List<User>): ExitCode **[1 минут]**
	+ 9.3 Прорефакторить функцию authentication в authorization и accounting **[3 минуты]**
	+ 9.4 Изменить функцию authentication(help: Boolean, login: String): ExitCode **[1 минута]**