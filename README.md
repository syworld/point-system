# Point System
User가 Place에 대한 Review를 작성할 때마다 포인트를 부여됩니다.

Review가 작성되고 포인트가 부여되는 히스토리를 ReviewEvent 테이블에서 데이터를 관리하며, 포인트가 부여되고 Event가 생성되는 로직을 구현하였습니다.

프로젝트 소스에서 GlobalExceptionHandler를 정의하여 모든 RuntimeException을 공통 response format인 GlobalExceptionResponse로 처리하였습니다. 또한 개발 환경별 logback 설정과 test 및 API Document를 구성하였습니다.



## API

1) ReviewEvent 생성 

![image](https://user-images.githubusercontent.com/103729286/178397359-1c5c8a7f-d2d2-4657-a8df-3ac8e89054f0.png)



2) 특정 User의 현재 Point 조회

![image](https://user-images.githubusercontent.com/103729286/178397214-233f3b0f-f486-44d7-b5ef-732599eb5871.png)

![image](https://user-images.githubusercontent.com/103729286/178397540-3768e088-d1c6-48be-bb4c-9a10cc716893.png)



## MySQL

Database Schema : https://github.com/syworld/point-system/blob/main/src/main/resources/mysql/schema.sql  

ReviewEvent 테이블에 기록하며, User의 현재 포인트는 해당 테이블에서 SQL 집계 함수를 통해 데이터를 가져옵니다.

Place, Review, User, Image와 관련된 테이블은 MSA 서버에서 관리하고, 리뷰 포인트 이벤트에 대한 관리만 현재 모듈에서 관리한다고 가정하고 테이블을
설계했습니다.

각각의 API가 수행될 때, EXPLAIN으로 Database 실행 결과를 조회하면 Full Table Scan이 아닌, Index Scan이 일어남을 확인할 수 있습니다. 



## 프로젝트 세팅

- Implementation

```
Java 17
Spring Boot 2.6.6
Gradle
MyBatis
MySQL 
Docker
```

- Build & Run

```
gradle clean
gradle bootJar
java -jar build/libs/*.jar --ENV=${ENV} --MYSQL_HOST=${MYSQL_HOST} --MYSQL_DBNAME=${MYSQL_MYSQL_DBNAME} --MYSQL_USERNAME=${MYSQL_USERNAME} --MYSQL_PASSWORD=${MYSQL_PASSWORD} --MYSQL_PORT=${MYSQL_PORT}
```

- Database

```
docker run -e MYSQL_USER=${MYSQL_USER} -e MYSQL_PASSWORD=${MYSQL_PASSWORD} -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} -e MYSQL_DATABASE=${MYSQL_DBNAME} -p 3306:3306 mysql:latest
```

- Api Document

```
${SERVER_HOST}/swagger-ui/index.html
```

- Testing

```
./gradlew build -x test
SPRING_PROFILES_ACTIVE=[test] ./gradlew test
```
