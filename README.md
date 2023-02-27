# PatikaDev-Definex-Final-Case
Patika dev DefineX Java Spring Practicum Final Case

## Kullanılan Teknolojiler
* Java
* Maven
* Spring Boot (Version : 3.0.2)
* Postqresql
* Dependencies
==> Spring Web
-- Spring Boot DevTools
-- Lombok
-- Spring Data JPA
-- Validation

## Run
- Project > Run As> Maven Build  ``spring-boot:run``

## Formula Table
* Uygulama ayağa kaldırılmadan önce aşağıdaki tablo veritabanına kaydedilmelidir.
* Tablonun amacı kod içerisinde çok fazla if else olmaması ve 
kredi başvuruları değerlendirilirken bir değişiklik yapılmak istendiğinde kod 
içerisinde değişiklik yapmaya gerek kalmadan direkt tablo üzerinde bir değişiklik yaparak
gerçekleştirilmesi.
* Uygualama 500 kredi skoruna sahip müşteriler için kredi başvuruları reddediliyor.
Bu engel 300 kredi skoruna çekilmek  istendiğinde direkt tablodan yani tek bir noktadan 
kod içerisinde bir değişiklik yapmaya gerek kalmadan güncellenebiliyor.
* Formula tablsona kredi scoru ve aylık maaş bilgileri ile istek atılır.
credit_ result ve donen amount değerine göre ;
* amount kısmından bir değer dödüyse  ``amount+(deposit*rateDeposit)`` işlemi gerçekliştirilir.
* null döndüyse ``monthlySalary*(creditLimitMultiplier*rateCreditLimitMultiplier) + deposit*rateDeposit`` işlemi uygulanır

id  | amount | credit_result | max_credit_score | min_credit_score | max_monthly_salary | min_monthly_salary | rate_credit_limit_multiplier | rate_deposit
---------|----------|--------------|----------|--------------|--------------|-------------|------------|-----------
1     | 0.00     | false | 499 | 0   | 1000000.00 | 0.00    | 0   | 0
2     | 10000.00 | true | 999  | 500 | 5000.00    | 0.00    | 0   | 0.1
3     | 20000.00 | true | 999  | 500 | 10000.00   | 5001.00 | 0   | 0.2
4     | null     | true | 999  | 500 | 1000000.00 | 10001.00 | 0.5 | 0.25
5     | null     | true | 1900 | 1000 | 1000000.00 | 0.00   | 1   | 0.5

### Customer Controller

Method   |   Path   |   Description|Request Parameters
---------|----------|--------------|----------
POST     |/api/customer | Bir müşteriyi kaydeder| CustomerDto(Json Body)
DELETE   |/api/customer/{id}|Müşteri bilgilerini siler|integer (id)
PUT      |/api/customer/{id}| Müşteri bilgilerini günceller|integer (id) <p/> UpdateCustomerDto(Json body)

### Transaction Controller

Method   |   Path   |   Description|Request Parameters
---------|----------|--------------|----------
POST     |/api/transaction | Gelen Müşteri bilgileri ile kredi başvurusu gerçekleştirilir |LoanApplicationRequest(Json Body)
POST     |/api/transaction/tracking | Müşteri kimlik numarası ve doğum tarihi bilgisi ile kredi başvurusunu sorgular | LoanApplicationTranckingRequest(Json Body)

### credit-score-service
* Bir müşteri veri tabanına kaydedildiğinde random bir kredi skoru üretilir(0-1900) Rest Template kullanılarak credit-score-servise ine 
istek atılır ve müşteriye ait kredi skoru kaydedilir.
* Bir müşteri kredi başvurusu yaptığında credit-score-service müşterinin kimlik numarası bilgisi ile istek atılır ve müşteri kredi skoru alınır.
