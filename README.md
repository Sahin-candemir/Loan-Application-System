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
-- 

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

