1. Добавить в проект Kotlin Serialization

2. Создать провайдер для сериализатора

3. В AuthenticationDao создать функцию getAllUsers
    + 3.1 Выполнение http get по адресу /ajax/user должен возвращать json список пользователей

4. В AuthenticationDao создать функцию getdUserByID(id: Int)
    + 4.1 get запрос /ajax/user?id=xxx должен возвращать json пользователя с указанным идентификатором

5. В AuthorizationDao создать функцию getAllRoles()
    + 5.1 get запрос /ajax/authority должен возвращать json список прав доступа

6. В AuthorizationDao создать функцию getRoleByID(id: Int)
    + 6.1 get запрос /ajax/authority?id=xxx должен возвращать json право пользователя с указанным идентификатором

7. В AuthorizationDao создать функцию getRolesByUserID(userId: Int)
    + 7.1 get запрос /ajax/authority?userId=xxx должен возвращать json права указанного пользователя

8. В AccountingDao создать функцию getAllActivity()
    + 8.1 get запрос /ajax/activity должен возвращать json список действий

9. В AccountingDao создать функцию  getActivityByID(id: Int)
    + 9.1 get запрос /ajax/activity?id=xxx должен возвращать json действие с указанным идентификатором

10. В AccountingDao создать функцию getActivityByAuthorityID(authorityId: Int)
    + 10.1 get запрос /ajax/activity?authorityId=xxx должен возвращать json действия с указанными правами доступа

11. В объекте User не должны сериализоваться пароль и хеш

12. В объекте Authority не должны сериализоваться User

13. В объекте Activity не должны сериализваться Authority

14. При коннекте к БД должна проходить миграция