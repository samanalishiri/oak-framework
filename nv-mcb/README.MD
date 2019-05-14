## Prerequisites
- Java 8
- maven 3
- JEE Application Server

## Getting start

####Step-1: Checkout from SVN
    1- make directory named nv-mcb
    2- checkout https://10.12.47.23/svn/Core2

####Step-2: Install ojdbc
    1- go to infrastructure/driver directory
    2- execute mvn-ojdbc6-install.bat

####Step-3: Install application server

- tomcat


    1- download apache-tomcat-9.0.19.zip from https://tomcat.apache.org/download-90.cgi
    2- extract to /path/to
    3- add to Java IDE


##Structure

####Packaging
Project packaging description.

| package               | description|
| :---:                 | :---:      |      
|domain                 | محل قرارگیری Entity
|repository             |جهت پیاده سازی لایه دسترسی به داده در صورت نیاز به پیاده سازی
|service                |جهت پیاده سازی لایه کسب و کار در صورت نیاز به پیاده سازی
|api                    |جهت ارائه وب سرویس REST
|exception              |پیاده سازی Exception مربوط به کسب و کار
|enums                  |محل قرار گیری Enum مربوط به هر ماژول
|util                   |پیاده سازی utility مربوط به ماژول، البته شامل technical utility نمی شود
|util\transformer       |پیاده سازی Transformer
|rule                   |پیاده سازی قوانین کسب و کار Business Rule
|vo                     |محل قرارگیری Value Object
|report                 |پیاده سازی گزارشات
|batch                  |پیاده سازی عملیات Batch

####Example Module
برای مشاهده best practice میتوانید به ماژول example مراجعه فرمایید.

## Rules of Naming
استفاده از قوانین camelcase و DDD 

| subject                   | rule                              |
| :---:                     | :---:                             |   
| entity                    | concept-name + Entity             |
| value object              | concept-name + Vo                 |
| transformer(converter)    | concept-name + Transformer        |
| repository interface      | concept-name + Repository         |
| repository implementation | repository-interface-name + Impl  |
| service interface         | concept-name + Service            |
| service implementation    | service-interface-name + Impl     |
| RESTful web service       | concept-name + Rest               |
