1. Добавить 
    + 1.1 guice-persist
    + 1.2 hibernate

2. Добавить аннотации для доменных классов из javax.persistence.*

3. Создать миграцию для добавление колонки version

4. Добавить поле verson в User, Activity, Authority и аннотацию @Version

5. Для полей id добавить @GeneratedValue

6. Создать dao-классы UserDao, AuthorityDao, ActivityDao

7. В UserDao перенести функции: getAllUsers(), getUserByID(id: Int) из AuthenticationDao

8. В AuthorityDao перенести функции: getAllAuthority(), getAuthorityByID(id: Int), getAuthorityByUserID(userId: Int) из AuthorizationDao

9. В ActivityDao перенести функции: getAllActivity(), getActivityByID(id: Int), getActivityByAuthorityID(authorityId: Int) из AccountingDao

10. В gradle добавить h2

11. Поменять коннекты под h2

12. Переписать миграции под h2

13. Создать META-INF в ней создать persistence.xml с
    ```
    <?xml version="1.0" encoding="UTF-8" ?>
    <persistence xmlns="http://java.sun.com/xml/ns/persistence"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/persistence
        http://java.sun.com/xml/ns/persistence/persistence_1_0.xsd" version="1.0">
    
        <persistence-unit name="myFirstJpaUnit" transaction-type="RESOURCE_LOCAL">
            <provider>org.hibernate.ejb.HibernatePersistence</provider>
     
            <class>ru.danil42russia.aaa.dao.UseaDao</class>
            <class>ru.danil42russia.aaa.dao.AuthorityDao</class>
            <class>ru.danil42russia.aaa.dao.ActivityDao</class>
    
            <properties>
                <property name="javax.persistence.jdbc.driver" value="org.h2.Driver" />
                <property name="javax.persistence.jdbc.url" value="jdbc:h2:aaa:db;" />
                <property name="javax.persistence.jdbc.user" value="sa" />
                <property name="javax.persistence.jdbc.password" value="" />
                <property name="hibernate.show_sql" value="true" />
                <property name="hibernate.hbm2ddl.auto" value="update" />
            </properties>
        </persistence-unit>
    
    </persistence>
    ```
    
14. В ModuleStages добавить
    + 14.1 install(new JpaPersistModule("myFirstJpaUnit")
    + 14.2 filter("/*").through(PersistFilter.class);

15. В UserDao, AuthorityDao, ActivityDao поменять connection на EntityManager через Inject

16. В Heroku добавить PostgreSQL

17. Перевести H2 в режим совместимости с PostgreSQL 

18. В gradle добавить hibernate-c3p0

19. В  persistence.xml добавить
    ```
        <property name="hibernate.c3p0.min_size">5</property>
        <property name="hibernate.c3p0.max_size">20</property>
        <property name="hibernate.c3p0.acquire_increment">5</property>
        <property name="hibernate.c3p0.timeout">1800</property>
    ```


