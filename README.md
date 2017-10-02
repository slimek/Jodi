Jodi
====

這是一個 Java 網路應用程式的習作，使用 Java 8 及 Eclipse 進行開發，部署環境為 Tomcat 7。


建置
----

請在 `src/main/resources` 目錄中新增一個名為 `secret.properties` 的檔案，內容為 MySQL DB 的連線資訊：
```text
db.host=<host>
db.user=<user>
db.password=<password>
```


程式庫
------

以下程式庫也都是練習的對象：

- [Spring Framework](https://projects.spring.io/spring-framework/)：網路應用框架。
- [Log4j 1.2](https://logging.apache.org/log4j/1.2/)：歷程記錄。
- [SLF4J](https://www.slf4j.org/)：歷程記錄抽象介面。
