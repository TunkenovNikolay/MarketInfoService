# MarketInfoService
Данное приложение предназначено для работы с данными в базе данных PostgreSQL Shop. 
Данный сервис,на основании входных параметров(аргументы командной строки), 
типа операции и входного файла – извлекает необходимые данные из БД и формирует результат обработки в выходной файл. 

**ИНСТРУКЦИЯ СБОРКИ:**

Для запуска базы данных необходим Docker, в нем нужно выполнить команду docker-compose up. 
После запуска поднимается база данных postgres на порте 5432 и выполняется скрипт init.sql. Init.sql создает все необходимые таблицы и заполняет их тестовыми данными. 
Если на компьютере нет Docker, но есть бд postgres - то выполнить init.sql в ней.

Параметры postgres:

db: postgres

username: postgres

password: admin

**ИНСТРУКЦИЯ ЗАПУСКА:**

Пример запуска программы: java -jar MarketInfoService-1.0-SNAPSHOT.jar search input.json output.json

Значение аргументов:

- MarketInfoService-1.0-SNAPSHOT.jar - название программы

- search/stat : search - поиск покупателей по критериям, stat - статистика за период.

- input.json - входной файл со списком критериев.

- output.json - результирующий файл.


***ТИПЫ И ОПИСАНИЕ ОПЕРАЦИЙ:***

**Поиск покупателей по критериям (search).**

Во входном файле передаётся список критериев для поиска покупателей. 
Результат операции - списки покупателей для каждого критерия из запроса. Порядок списков такой же как в запросе, порядок покупателей в списке — произвольный.

Критерии:

1. Фамилия — поиск покупателей с этой фамилией;

2. Название товара и число раз — поиск покупателей, купивших этот товар не менее, чем указанное число раз;

3. Минимальная и максимальная стоимость всех покупок — поиск покупателей, у которых общая стоимость всех покупок за всё время попадает в интервал;

4. Число пассивных покупателей — поиск покупателей, купивших меньше всего товаров. Возвращается не более, чем указанное число покупателей.

Пример входных данных: 

{

	"criterias": [ 

	    {"lastName": "Иванов"}, //Фамилия

		  {"productName": "Минеральная вода", "minTimes": 5}, // Название товара и число раз

	    {"minExpenses": 112, "maxExpenses": 4000}, //Минимальная и максимальная стоимость всех покупок

	    {"badCustomers": 3} //Число пассивных покупателей

      ]

}

P.S. Критерии могут повторяться.

**Статистика за период (stat).**

Во входном файле передаётся интервал дат сбора статистики. Результат операции - статистика по покупателям за период из двух дат, включительно, без выходных.

Пример входных данных:

{

    "startDate": "2020-01-14", // Начальная дата

    "endDate": "2020-01-26" // Конечная дата

}


Edit By TunkenovNikolay 
https://vk.com/nik0sh