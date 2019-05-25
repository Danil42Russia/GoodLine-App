1. Создать файл ``versions.gradle`` и перенести туда версии из ``gradle.properties``

2. Создать файл ``jacoco.gradle`` и перенести туда ``task codeCoverageReport``

3. Создать файл ``.detekt.yml`` и [заполнить](https://github.com/arturbosch/detekt/blob/master/detekt-cli/src/main/resources/default-detekt-config.yml)

4. Создать файл ``detekt.gradle`` и заполнить его

5. Вызвать ``versions.gradle`` ``jacoco.gradle`` ``detekt.gradle`` в ``build.gradle``

6. Обновить ``sonar-project.properties``
    ```
    sonar.coverage.jacoco.xmlReportPaths = build/reports/jacoco.xml
    sonar.kotlin.binaries = build/classes/kotlin
    sonar.kotlin.coveragePlugin = jacoco
    sonar.kotlin.detekt.reportPaths = build/reports/detekt.xml
    sonar.sourceEncoding = UTF-8 
    sonar.scm.provider = git
    
    sonar.java.binaries = build/classes/kotlin/main'
    sonar.java.test.binaries = build/classes/kotlin/test
    ```

7. В корне проекта создать aaa-app, aaa-web

8. В aaa-app перенести src и всё содержимое

9. В aaa-app и aaa-web создать ``buid.gredle``

10. В aaa-web в ``buid.gredle`` доабавить ``gretty``
11. В aaa-web создать папки:
    ```
	aaa-web
		-src
			-main
				-kotlin/danil42russia/aaa
					-servlet
				-resources
				-webapp
	```

12. ``aaa-site`` преобразовать в web-приложение

13. В ``servlet`` создать ``MainServlet`` слушающий /echo/*, если запрос не /echo/get или /echo/post возвращать 404

14. Запрос /echo/get должен принимать параметр X, и возвращает его в виде ответа

15. Запрос /echo/post должен делать редирект на /echo/get?id=X где X поле введенное в форму

16. Создать index.html с формой, одним полем и кнопкой submit, форма отправляет post запрос на /echo/pos. Сделать ссылку на GET-сервлет с каким-нибудь параметром.

17. Настроить автоматический деплой проекта на heroku

18. Задеплоить проект на heroku

19. Создать генератор страницы для /echo/get используя GSP

