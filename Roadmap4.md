1. Добавить gradle в проект

2. Выполнить ``gradlew init``

3. Добавить зависимости  
    + 3.1 Для тестирования добавить [Spek Framework](https://spekframework.org)
    + 3.2 Для статического анализа кода  [Detekt](https://arturbosch.github.io/detekt)
   
4. Для отчёта по покрытию кода использовать [Сodecov](https://codecov.io)
    + 4.1 в ``.travis.yml`` добавить  
    
        ```
        after_success:
            - bash <(curl -s https://codecov.io/bash)
        ```
    + 4.2 В ``README.md`` дабвить статус анализа
    
5. Для отчёта по проверки на codestyle использовать [INSPECODE](https://inspecode.rocro.com/)
    + 5.1 В ``README.md`` дабвить статус анализа
