# Point System

## MySQL

Database
Schema : https://github.com/syworld/point-system/blob/main/src/main/resources/mysql/schema.sql

ReviewEvent 테이블에 기록합니다. User의 현재 포인트는 SQL 집계 함수를 통해 데이터를 가져옵니다.

Place, Review, User, Image와 관련된 테이블은 MSA 서버에서 관리하고, 리뷰 포인트 이벤트에 대한 관리만 현재 모듈에서 관리한다고 가정하고 테이블을
설계했습니다.

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
