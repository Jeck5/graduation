# Задание: реализация REST API по работе со счетами
## 1 Возможные действия и примеры 
### 1.1 POST /bankaccount/{accountNumber}​ - Завести новый счет. На вход команда принимает параметр номер счета - 5-ти значное число  
_curl -i -X POST http://localhost:8090/bankaccount/34004_  
HTTP/1.1 200
...
{"id":100031,"accountNumber":34004,"balance":0}

_curl -i -X POST http://localhost:8090/bankaccount/34004_  
HTTP/1.1 500
...
 {"timestamp":1537913837856,"status":500,"error":"Internal Server Error",
 "exception":"org.springframework.dao.DataIntegrityViolationException",
 "message":"could not execute statement; SQL [n/a]; constraint [null]; 
 nested exception is org.hibernate.exception.ConstraintViolationException: 
 could not execute statement","path":"/bankaccount/34004"}

_curl -i -X POST http://localhost:8090/bankaccount/3400_  
HTTP/1.1 500
...

_curl -i -X POST http://localhost:8090/bankaccount/340044_  
HTTP/1.1 500
...
 
### 1.2 PUT /bankaccount/{accountNumber}/deposit​ - Внести сумму на счёт. На вход команда принимает 2 параметра - номер счета и сумму к зачислению 
_curl -i -X PUT -H "Content-Type: application/json" http://localhost:8090/bankaccount/30105/deposit -d "105.24"_
HTTP/1.1 200
...

_curl -i -X PUT -H "Content-Type: application/json" http://localhost:8090/bankaccount/30105/deposit -d "-105.24"_
HTTP/1.1 500
...
{"timestamp":1537914928897,"status":500,"error":"Internal Server Error","exception":"javax.validation.ConstraintViolationException","message":"No message available","path":"/bankaccount/30105/deposit"}

_curl -i -X PUT -H "Content-Type: application/json" http://localhost:8090/bankaccount/30105/deposit -d "105.2434"_
HTTP/1.1 500
...
{"timestamp":1537915032113,"status":500,"error":"Internal Server Error","exception":"javax.validation.ConstraintViolationException","message":"No message available","path":"/bankaccount/30105/deposit"}

_curl -i -X PUT -H "Content-Type: application/json" http://localhost:8090/bankaccount/99999/deposit -d "105.24"
HTTP/1.1 500_
...
{"timestamp":1537915127927,"status":500,"error":"Internal Server Error","exception":"java.lang.IllegalArgumentException","message":"bankAccount must not be null","path":"/bankaccount/99999/deposit"}

### 1.3 PUT /bankaccount/{accountNumber}/withdraw​ - Снять сумму со счёта. На вход команда принимает 2 параметра - номер счета и сумму снятия 
_curl -i -X PUT -H "Content-Type: application/json" http://localhost:8090/bankaccount/30105/withdraw -d "5.24"
HTTP/1.1 200_
...

_curl -i -X PUT -H "Content-Type: application/json" http://localhost:8090/bankaccount/30105/withdraw -d "99999999.99"_
HTTP/1.1 500
...
{"timestamp":1537915503225,"status":500,"error":"Internal Server Error","exception":"com.example.bankaccount.util.exception.NotEnoughBalanceException","message":"Not enough balance","path":"/bankaccount/30105/withdraw"}

### 1.4 GET /bankaccount/{accountNumber}/balance​ - Узнать баланс. На вход команда принимает параметр номер счета - 5-ти значное число
_curl -i http://localhost:8090/bankaccount/30105/balance_
HTTP/1.1 200
... 
1215.06

_curl -i http://localhost:8090/bankaccount/99999/balance_
HTTP/1.1 500
...

{"timestamp":1537915651709,"status":500,"error":"Internal Server Error","exception":"com.example.bankaccount.util.exception.NotFoundException","message":"Not found entity with accountNumber=99999","path":"/bankaccount/99999/balance"}

_curl -i http://localhost:8090/bankaccount/qwert/balance_
HTTP/1.1 400
...
{"timestamp":1537915823786,"status":400,"error":"Bad Request","exception":"org.springframework.web.method.annotation.MethodArgumentTypeMismatchException","message":"Failed to convert value of type 'java.lang.String' to required type 'int'; nested exception is java.lang.NumberFormatException: For input string: \"qwert\"","path":"/bankaccount/qwert/balance"}

## 2 Ипользуемая БД
postgresql 9.6  
url=//localhost:5432/bankaccounts   
username=user1    
password=pass1  

## 3 Сборка и настройка
1) В Maven выполнить фазу package.  
2) Полученный в результате дейтсвия 1 jar файл запустить из командной строки.
   Для этого необходимо перейти в папку target, содержащую файл **_bankaccount-0.0.1-SNAPSHOT.jar**_ 
   и ввести java -jar bankaccount-0.0.1-SNAPSHOT.jar   
   jar включает в себя embedded tomcat 